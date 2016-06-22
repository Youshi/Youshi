package com.example.hmqqg.hm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.hmqqg.hm.R;


public class DateTimePickerDialog extends AlertDialog 
	implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
	/** 日期选择器. */
	private DatePicker datePicker;
	
	/** 时间选择器. */
	private TimePicker timePicker;
	
	/** 日历. */
	private Calendar calendar;
	

	
	/**
	 *精度到分的格式监听接口
	 * @see
	 */
	public static interface DateTimeDialogListener {
		/**
		 * 设置时间时回调.
		 * @param date 时间
		 */
		public void onDateSet(Date date);
	}

	/** 监听器. */
	private DateTimeDialogListener dateTimeDialogListener;

	/**
	 *构造一个精确到年月日时分的对话框
	 * @param context 上下文
	 * @param dateTimeDialogListener 监听器
	 * @param date 时间
	 */
	public DateTimePickerDialog(Context context,
			DateTimeDialogListener dateTimeDialogListener, Date date,boolean is24Hour) {
		super(context);
		this.dateTimeDialogListener = dateTimeDialogListener;

		calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		setButton(BUTTON_POSITIVE,
				getContext().getText(R.string.messagebox_ok), this);
		setButton(BUTTON_NEGATIVE,
				getContext().getText(R.string.messagebox_cancel), this);
		setIcon(0);
        
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.date_time_dialog, null);
		setView(view);
		
		datePicker = (DatePicker)view.findViewById(R.id.dateTimeDialogDatePicker);
		datePicker.init(calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH), 
				calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker = (TimePicker)view.findViewById(R.id.dateTimeDialogTimePicker);
		timePicker.setIs24HourView(is24Hour);		
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
		
		timePicker.setOnTimeChangedListener(this);
		
		updateTitle();
	}

//	@Override
//	protected void onStart() {
////		hideButtonView(timePicker);
//		super.onStart();
//	}
	
//	/**
//	 * 隐藏"上午，下午"按钮
//	 * @param view
//	 */
//	private void hideButtonView(View view){
//		List<View> buttons = findViewByClassName(view, Button.class.getName());
//		if(buttons!=null){
//			for (View item : buttons) {
//				item.setVisibility(View.GONE);
//			}
//		}
//	}
//	/**
//	 * 寻找指定classname的视图组件
//	 * @param view
//	 * @param className
//	 * @return
//	 */
//	private List<View> findViewByClassName(View view, final String className){
//		if(view == null || className == null)
//			return null;
//		List<View> views = new ArrayList<View>();
//		if(view.getClass().getName().equals(className)){
//			views.add(view);
//		}else if(view instanceof ViewGroup) {
//			ViewGroup viewGroup  = (ViewGroup) view;
//			for (int i = 0; i < viewGroup.getChildCount(); i++) {
//				views.addAll(findViewByClassName(viewGroup.getChildAt(i),className));
//			}
//		}
//		return views;
//	}
	/**
	 * 获取时间.
	 * @return 时间
	 */
	public Date getDate() {
		return calendar.getTime();
	}
	/**
	 * 设置时间.
	 * @param date 时间
	 */
	public void setDate(Date date) {
		calendar.setTime(date);
		updateTitle();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == BUTTON_POSITIVE) {
			dateTimeDialogListener.onDateSet(calendar.getTime());
		}
	}
	/**
	 * 更新标题.
	 */
	private void updateTitle() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm",Locale.getDefault());
		this.setTitle(simpleDateFormat.format(calendar.getTime()));
	}
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		calendar.set(year, monthOfYear, dayOfMonth);
		updateTitle();
		
	}
	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
//		updateHourEditText(view, hourOfDay);
//		hideButtonView(view);
		updateTitle();
	}
//	/**
//	 * 更新小时编辑框文本 使其以0-23显示
//	 * @param view
//	 * @param hourOfDay
//	 */
//	private void updateHourEditText(TimePicker view, int hourOfDay) {
//		List<View> views  = findViewByClassName(view, NumberPicker.class.getName());
//		if(Build.VERSION.SDK_INT<11){
//			updateHourInOldVersion(views, hourOfDay);
//		}else {
//			updateHourInNewVersion(views, hourOfDay);
//		}
//	}
//	/**
//	 * 在api大于11情况下更新小时
//	 * @param views
//	 * @param hourOfDay
//	 */
//	private void updateHourInNewVersion(List<View> views, int hourOfDay) {
//		if(views == null)
//			return;
//		for (View view : views) {
//			NumberPicker item = (NumberPicker) view;
//			if(item.getMaxValue()==12 || item.getMaxValue()==23){
//				item.setMaxValue(23);
//				item.setMinValue(0);
////				item.setMaxValue(23);
//				//如果是时NumberPick则寻找其子视图中的edittext并设置其实际值
//				setHourEditTextView(item, hourOfDay);
//			}
//		}
//	}
//	/**
//	 * 设置小时编辑框内容使其以0-23显示
//	 * @param item
//	 * @param hourOfDay
//	 */
//	private void setHourEditTextView(NumberPicker item, int hourOfDay) {
//		if(item == null){
//			return;
//		}
//		List<View> views = findViewByClassName(item, EditText.class.getName());
//		if(views == null){
//			return;
//		}
//		for (View view : views) {
//			EditText hourEditText = (EditText)view;
//			hourEditText.setText(String.valueOf(hourOfDay));
//		}
//	}
//	private void updateHourInOldVersion(List<View> views, int hourOfDay) {
//		
//	}
}
