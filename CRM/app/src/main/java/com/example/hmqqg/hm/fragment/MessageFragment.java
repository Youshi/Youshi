package com.example.hmqqg.hm.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.example.hmqqg.hm.DemoHelper;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.PersonEntity;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
import com.example.hmqqg.hm.ui.CustomLook_Activity;
import com.example.hmqqg.hm.ui.Customer_Activity;
import com.example.hmqqg.hm.ui.LoginActivity;
import com.example.hmqqg.hm.ui.Seas_Customers;
import com.example.hmqqg.hm.ui.Underling_Customer;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import easeui.db.DemoDBManager;
/**
 * 管理
 */
public class MessageFragment extends BaseRequestFragment implements View.OnClickListener{
    private TextView title_top_bar;
    private TextView username;//姓名
    private TextView bumen;//部门
    private TextView zhiwu;//职务
    private ImageView portrait; //头像
    private LinearLayout linearlayout2;//关单客户
    private LinearLayout linearlayout3;//客户资料
    private LinearLayout linearlayout4;//意向客户
    private LinearLayout linearlayout5;//意向客户

    private LinearLayout logout;//退出登录

    private boolean progressShow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_information, container, false);
        initView(view);
        gethttp();
        return view;
    }

    private void initView(View view) {
        title_top_bar = (TextView) view.findViewById(R.id.title_top_bar);
        username = (TextView) view.findViewById(R.id.username);
        bumen = (TextView) view.findViewById(R.id.bumen);
        zhiwu = (TextView) view.findViewById(R.id.zhiwu);
        portrait = (ImageView) view.findViewById(R.id.portrait);
        linearlayout2 = (LinearLayout) view.findViewById(R.id.linearlayout2);
        linearlayout3 = (LinearLayout) view.findViewById(R.id.linearlayout3);
        linearlayout4 = (LinearLayout) view.findViewById(R.id.linearlayout4);
        linearlayout5 = (LinearLayout) view.findViewById(R.id.linearlayout5);
        logout = (LinearLayout) view.findViewById(R.id.logout);//退出登录
        title_top_bar.setText("系统管理");
        linearlayout2.setOnClickListener(this);
        linearlayout3.setOnClickListener(this);
        linearlayout4.setOnClickListener(this);
        linearlayout5.setOnClickListener(this);
        logout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent ;

        switch (v.getId()){
            case R.id.linearlayout2://关单客户
                intent = new Intent(getActivity(), Customer_Activity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout3://客户资料
                intent = new Intent(getActivity(), Seas_Customers.class);
                intent.putExtra("isShow","Show");
                startActivity(intent);
                break;
            case R.id.linearlayout4://意向客户
                intent = new Intent(getActivity(), CustomLook_Activity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout5://下属客户
                intent = new Intent(getActivity(), Underling_Customer.class);
                startActivity(intent);
                break;
            case R.id.logout://退出登录
                logout();
                break;

        }
    }

    private void logout() {
        AlertDialog alertdialog = new AlertDialog.Builder(getActivity()).setTitle("退出登录")
                .setMessage("是否退出当前账号").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DemoHelper.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                DemoHelper demohelper = DemoHelper.getInstance();
                                demohelper.setContactList(null);
                                demohelper.setRobotList(null);
                                demohelper.getUserProfileManager().reset();
                                DemoDBManager.getInstance().closeDB();
                                getActivity().finish();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onError(int i, String s) {
                                Toast.makeText(getActivity(),getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });//此方法为同步方法,里面的参数true表示退出登录时解绑GCM或者小米推送的token

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        }).create();
        alertdialog.show();//显示对话框
    }

    public void gethttp() {
        RequestParams params = new RequestParams(getResources().getString(R.string.http_service));
        params.addBodyParameter("action","details");

        params.addBodyParameter("userid", MyApplication.getInstance().getUserInfo().getUserId());
        x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new PersonEntity()));
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        PersonEntity pe = (PersonEntity) object ;
        username.setText(pe.getDetailInfo().get(0).getUserName() + "");
        bumen.setText(pe.getDetailInfo().get(0).getDeptName()+"");
        zhiwu.setText(pe.getDetailInfo().get(0).getDuty() + "");
        ImageLoader.getInstance().loadImage(getResources().getString(R.string.http_image)+pe.getDetailInfo().get(0).getHeadimg(), new SimpleImageLoadingListener()
                {
                    public void onLoadingComplete(String imageUri, android.view.View view, android.graphics.Bitmap loadedImage) {
                        loadedImage=toRoundBitmap(loadedImage);
                        portrait.setImageBitmap(loadedImage);
                    };
                    public void onLoadingFailed(String imageUri, android.view.View view, com.nostra13.universalimageloader.core.assist.FailReason failReason) {
                    };
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }
                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                    }
                }
        );
    }
    public Bitmap toRoundBitmap(Bitmap bitmap){
        int width=150;
        int height=150;
        int r=0;
        //取最短边做边长
        if(width<height){
            r=width;
        }else{
            r=height;
        }
        //构建一个bitmap
        Bitmap backgroundBm=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas=new Canvas(backgroundBm);
        Paint p=new Paint();
        //设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect=new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, p);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }
}
