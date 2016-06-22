package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.common.Constant;

import java.util.List;

/**
 * 位置选择
 */
public class LocationActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private double latitude;
    private double longitude;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;

    MapView mMapView;
    BaiduMap mBaiduMap;

    boolean isFirstLoc = true; // 是否首次定位
    private PoiSearch mPoiSearch;
    private ListView listView;
    private ImageView back;
    private ImageView search;
    private EditText keyWord;
    private ProgressDialog mypDialog;//进度条对话框
    private int position;//选择的条目
    private int lastPosition;//上次选择的条目
    private TextView comfirm;
    private List<PoiInfo> allPoi;

    private int loadMode = Constant.REFRESH;//数据加载类型

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        SharedPreferences sp = getSharedPreferences("location", MODE_PRIVATE);
        latitude = Double.parseDouble(sp.getString("latitude", "" + 39.90923));
        longitude = Double.parseDouble(sp.getString("longitude", "" + 116.397428));
    // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);

        listView = (ListView) findViewById(R.id.listview);
        back = ((ImageView) findViewById(R.id.back));
        search = ((ImageView) findViewById(R.id.iv_search));
        keyWord = ((EditText) findViewById(R.id.et_keyword));
        comfirm = ((TextView) findViewById(R.id.comfirm));

        comfirm.setOnClickListener(this);
        search.setOnClickListener(this);
        back.setOnClickListener(this);
        listView.setOnItemClickListener(this);

        initDialog();
        mypDialog.show();

        mCurrentMode = LocationMode.NORMAL;
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showScaleControl(false);
        mMapView.showZoomControls(false);
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        mBaiduMap = mMapView.getMap();

        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_geo);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));
        MapStatus mapStatus = new MapStatus.Builder().zoom(19).build();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000);
        option.setIsNeedLocationPoiList(true);
        mLocClient.setLocOption(option);
        mLocClient.start();

        //搜索周边地标
        poiSearch();
    }

    private void initDialog() {
        //实例化一个进度条对话框
        mypDialog = new ProgressDialog(LocationActivity.this);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);         //设置进度条风格
        mypDialog.setTitle("搜索附近的公司、写字楼");                                          //设置进度条标题
        mypDialog.setMessage("正在搜索中,请稍后...");                        //设置提示信息
        mypDialog.setIndeterminate(true);                                                          //设置是否精度显示进度
        mypDialog.setCancelable(false);                                                            //设置是否触屏取消
        mypDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {                //设置按钮
                mypDialog.dismiss();
//                        mPoiSearch.destroy();
            }
        });
    }

    private void poiSearch() {
        //        第一步，创建POI检索实例
        mPoiSearch = PoiSearch.newInstance();
//        第二步，创建POI检索监听者；
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult result) {
                //获取POI检索结果
                allPoi = result.getAllPoi();

                if (allPoi != null) {

                    listView.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return allPoi.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return allPoi.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return position;
                        }

                        @Override
                        public View getView(final int position, View convertView, ViewGroup parent) {
                            final ViewHolder viewHolder;
                            if (convertView == null) {
                                viewHolder = new ViewHolder();
                                convertView = LayoutInflater.from(LocationActivity.this).inflate(R.layout.item_location_set, null);
                                viewHolder.poiName = (TextView) convertView.findViewById(R.id.poi_name);
                                viewHolder.poiAddress = (TextView) convertView.findViewById(R.id.poi_address);
                                viewHolder.complete = (ImageView) convertView.findViewById(R.id.complete);
                                convertView.setTag(viewHolder);
                            } else {
                                viewHolder = (ViewHolder) convertView.getTag();
                            }
                            if (LocationActivity.this.position == position) {
                                viewHolder.complete.setVisibility(View.VISIBLE);
                            } else {
                                viewHolder.complete.setVisibility(View.GONE);
                            }
                            PoiInfo poiInfo = allPoi.get(position);
                            viewHolder.poiName.setText(poiInfo.name);
                            viewHolder.poiAddress.setText(poiInfo.address);
                            return convertView;
                        }
                    });
                }
                mypDialog.dismiss();
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult result) {
                //获取Place详情页检索结果
                result.getAddress();
            }
        };
        //        第三步，设置POI检索监听者；
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);


//                第四步，发起检索请求；
        PoiNearbySearchOption poiNearbySearchOption = new PoiNearbySearchOption();

        mPoiSearch.searchNearby(poiNearbySearchOption.
                location(new LatLng(latitude, longitude))
                .pageCapacity(10)
                .radius(1000)
                .sortType(PoiSortType.distance_from_near_to_far)
                .keyword("公司"));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        lastPosition = this.position;
        this.position = position;
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comfirm:
                PoiInfo poiInfo = (PoiInfo) allPoi.get(position);
                Intent intent = new Intent();
                intent.putExtra("name", poiInfo.name);
                intent.putExtra("address", poiInfo.address);
                setResult(Constant.RESULT_LOCATION, intent);
                finish();
                break;
            case R.id.back:
                onBackPressed();
                break;
            case R.id.iv_search:
                InputMethodManager inputManager = (InputMethodManager) LocationActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(keyWord.getWindowToken(), 0);
                if (keyWord.getText() == null || "".equals(keyWord.getText().toString())) {
                    Toast.makeText(LocationActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                    return;
                }

                mypDialog.show();
                String strKeyWord = keyWord.getText().toString();
                //发起检索请求；
                PoiNearbySearchOption poiNearbySearchOption = new PoiNearbySearchOption();
                mPoiSearch.searchNearby(poiNearbySearchOption.
                        location(new LatLng(latitude, longitude))
                        .pageCapacity(10)
                        .radius(1000)
                        .sortType(PoiSortType.distance_from_near_to_far)
                        .keyword("" + strKeyWord));

                break;
        }
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {

            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }

            //显示周边poi
            final List<Poi> poiList = location.getPoiList();


        }

    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        mPoiSearch.destroy();
        // 取消监听 SDK 广播
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }


    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            TextView text = (TextView) findViewById(R.id.text_Info);
            text.setTextColor(Color.RED);
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                text.setText("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s
                    .equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                text.setText("key 验证成功! 功能可以正常使用");
                text.setTextColor(Color.YELLOW);
            } else if (s
                    .equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                text.setText("网络出错");
            }
        }
    }

    private SDKReceiver mReceiver;

    public static class ViewHolder {
        ImageView complete;
        TextView poiName;
        TextView poiAddress;
    }

}
