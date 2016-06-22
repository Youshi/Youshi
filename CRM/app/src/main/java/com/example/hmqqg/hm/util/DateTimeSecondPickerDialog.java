
package com.example.hmqqg.hm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.hmqqg.hm.R;

public class DateTimeSecondPickerDialog extends AlertDialog
				implements DialogInterface.OnClickListener,
				DatePicker.OnDateChangedListener,
				TimePicker.OnTimeChangedListener{

	/**日期选择器. */
	private DatePicker datePicker;
	
	/** 时间选择器. */
	private TimePicker timePicker;
	
	/** 日历对象. */
	private Calendar calendar;
	
	/** 增加秒的按钮. */
	private Button secondAddButton;
	
	/** 减少秒的按钮. */
	private Button secondMinusButton;
	
	/** 显示秒的编辑框. */
	private EditText secondEditText;
	
	/** 用户选择的秒. */
	private int second;
	
	/** 监听器 . */
	private DateTimeSecondDialogListener listener;
	/**
	 *精度到秒的格式监听接口
	 * @see
	 */
	public static interface DateTimeSecondDialogListener {
		
		/**
		 *时间改变时回调.
		 *
		 * @param date 时间
		 */
		public void onDateSet(Date date);
	}
	
	/** 常量 (作用于增加秒监听器). */
	private static final int SECOND_MAX = 60;
	
	/**
	 *构造一个精确到秒的日期选择器
	 * @param context 上下文
	 * @param listener 监听器
	 * @param date 时间
	 */
	public DateTimeSecondPickerDialog(Context context,
			DateTimeSecondDialogListener listener, Date date){
		super(context);
		this.listener = listener;
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		second = calendar.get(Calendar.SECOND);
		setButton(BUTTON_POSITIVE,
				getContext().getText(R.string.messagebox_ok), this);
		setButton(BUTTON_NEGATIVE,
				getContext().getText(R.string.messagebox_cancel), this);
		setIcon(0);
		
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.date_time_second_dialog, null);
		setView(view);
		
		datePicker = (DatePicker)view.findViewById(R.id.dateTimeSecondDialogDatePicker);
		datePicker.init(calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH), 
				calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker = (TimePicker)view.findViewById(R.id.dateTimeSecondDialogTimePicker);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
		timePicker.setOnTimeChangedListener(this);
		
		updateTitle();
		
		secondAddButton = (Button)view.findViewById(R.id.secondAddButton);
		secondMinusButton = (Button)view.findViewById(R.id.secondMinusButton);
		secondEditText = (EditText)view.findViewById(R.id.secondEditText);
		
		secondAddButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				second = (second+1)%DateTimeSecondPickerDialog.SECOND_MAX;
				calendar.set(Calendar.SECOND, second);
				secondEditText.setText(String.valueOf(second));
				updateTitle();
			}
		});
		secondMinusButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				second = second-1<0 ? 59 : second-1;
				calendar.set(Calendar.SECOND, second);
				secondEditText.setText(String.valueOf(second));
				updateTitle();
			}
		});
		secondEditText.setText(String.valueOf(second));
	}
	
	/** 格式化对话框标题. */
	private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
	
	/**
	 * 更新对话框标题.
	 */
	private void updateTitle() {
		this.setTitle(FORMAT.format(calendar.getTime()));
	}
	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		updateTitle();
	}
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		calendar.set(year, monthOfYear, dayOfMonth);
		updateTitle();
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == BUTTON_POSITIVE) {
			listener.onDateSet(calendar.getTime());
		}
	}
	/**
	 * 获取日期
	 * @return 日期
	 */
	public Date getDate(){
		return calendar.getTime();
	}
	/**
	 * 设置日期
	 * @param
	 */
	public void setDate(Date date){
		calendar.setTime(date);
		updateTitle();
	}
}
