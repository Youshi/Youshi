package com.example.hmqqg.hm.util;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class YearMonthDialog extends DatePickerDialog {

	/** 日历组件类名. */
	private static final String CALENDAR_VIEW_CLASS_NAME = "android.widget.CalendarView";

	private static final String TAG = YearMonthDialog.class.getSimpleName();
	/**
	 * 构造一个年月对话框.
	 * @param context 上下文对象
	 * @param callBack 回调对象
	 * @param year 年
	 * @param monthOfYear 月
	 * @param dayOfMonth 日
	 */
	public YearMonthDialog(Context context, OnDateSetListener callBack,
			int year, int monthOfYear, int dayOfMonth) {
		super(context, callBack, year, monthOfYear, dayOfMonth);
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(getDateTitle(title.toString()));
	}

	/**
	 * 从"Saturday,June 1,2013"格式的字符串或"2013-6-3周三"格式的 字符串中截取年月字符串.
	 * @param title 时间日期字符串
	 * @return 年月字符串
	 */
	private String getDateTitle(String title){
		if(title.lastIndexOf("-") != -1){
			return title.substring(0, title.lastIndexOf("-"));
		}else if(title.lastIndexOf(",") != -1){
			return getTitle(title,",");
		}else {
			return title;
		}
	}

	/**
	 * 截取"Saturday,June 1,2013"格式的字符串中的年月.
	 * @param title the title
	 * @param separator 分隔符
	 * @return 年月字符串
	 */
	private String getTitle(String title,String separator){
		StringBuilder result = new StringBuilder();
		//获取月日 字符串 （eg:"June 1"）
		String monthDay = title.substring(title.indexOf(separator)+1, title.lastIndexOf(separator)).toString().trim();
		if(monthDay.indexOf(" ") != -1){
			//追加月字符串
			result.append(monthDay.substring(0, monthDay.indexOf(" ")));
		}else {
			result.append(monthDay);
		}
		//追加年字符串
		result.append(title.substring(title.lastIndexOf(separator)+1));
		return result.toString();
	}
	@Override
	protected void onStart() {
		hideDayNumberPicker();
		hideCalendarView();
		super.onStart();
	}
	/**
	 * 隐藏年月日组件右边的日历组件(该组件会在android3.x中DatePickerDialog中出现)
	 */
	private void hideCalendarView() {
		View view = getWindow().getDecorView();
		hideView(findViewByClassName(view, CALENDAR_VIEW_CLASS_NAME));
	}

	/**
	 * 隐藏日组件.
	 */
	@SuppressLint("NewApi")
	private void hideDayNumberPicker() {
		View view = getWindow().getDecorView();
		try {
			hideNumberPickerDay(findViewByClassName(view, NumberPicker.class.getName()));
		} catch (IllegalArgumentException e) {
			Log.e(TAG, e.getMessage());
		} catch (NoSuchMethodException e) {
			Log.e(TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.e(TAG, e.getMessage());
		} catch (InvocationTargetException e) {
			Log.e(TAG, e.getMessage());
		}
	}

	/**
	 * 从view视图中寻找类名为className的视图对象.
	 * @param view the view
	 * @param className the class name
	 * @return the list
	 */
	private List<View> findViewByClassName(View view, final String className){
		List<View> arrays = new ArrayList<View>();
		if(view.getClass().getName().equals(className)){
			arrays.add(view);
		}else if(view instanceof ViewGroup) {
			ViewGroup viewGroup  = (ViewGroup) view;
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				arrays.addAll(findViewByClassName(viewGroup.getChildAt(i), className));
			}
		}
		return arrays;
	}

	/**
	 * 隐藏views中所有视图对象并释放其对应空间.
	 * @param views the views
	 */
	private void hideView(List<View> views){
		if(views != null){
			for (View view : views) {
				view.setVisibility(View.GONE);
			}
		}
	}
	/**
	 * 隐藏arrays中的日NumberPicker组件.
	 * @param arrays the arrays
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private void hideNumberPickerDay(List<View> arrays) throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		if(Build.VERSION.SDK_INT <11){
			hideNumberPickerInOldVersion(arrays);
		}else {
			hideNumberInNewVersion(arrays);
		}
	}

	/**
	 * 隐藏参数中日组件视图对象并释放其对应的空间(该方法在api小于11情况下有效)
	 * 通过java反射机制调用其受保护的getBeginRange() getEndRange() 来判断传入的参数数组中元素是否为日组件对象
	 * 注: 此两个方法在大于等于api11情况下改为公共的方法对应方法为public getMinValue(), public getMaxValue().
	 * @param arrays
	 * @throws NoSuchMethodException the no such method exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private void hideNumberPickerInOldVersion(List<? extends View> arrays) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(arrays == null)
			return;
		for (View item : arrays) {
			Method getBeginRangeMethod = item.getClass().getDeclaredMethod("getBeginRange", new  Class[ 0 ]);
			Method getEndRangeMethod = item.getClass().getDeclaredMethod("getEndRange", new  Class[ 0 ]);
			getBeginRangeMethod.setAccessible(true);
			getEndRangeMethod.setAccessible(true);
			Integer min = (Integer)getBeginRangeMethod.invoke(item, new  Object[]{});
			Integer max = (Integer)getEndRangeMethod.invoke(item, new  Object[]{});
			//如果是日组件则隐藏它并释放其对应的空间
			if(min==1 && max>27 && max<32){
				item.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 在api>=11情况下其对应的方法公开由以前的protected getBeginRange() 变为 public getMinValue();
	 * protected getEndRange() 变为 public getMaxValue();
	 * @param arrays 视图容器对象
	 */
	private void hideNumberInNewVersion(List<? extends View> arrays){
		if(arrays == null)
			return;
		for (View item : arrays) {
			NumberPicker numberPicker = (NumberPicker)item;
			int maxValue = numberPicker.getMaxValue();
			int minValue = numberPicker.getMinValue();
			//如果是日组件则隐藏它
			if(minValue==1 && maxValue>27 && maxValue<32){
				item.setVisibility(View.GONE);
			}
		}
	}
}
