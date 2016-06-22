package com.example.hmqqg.hm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TimePicker;

public enum DateTimePrecision {

	/** 精确到年月日时分的时间对话框. */
	YEAR_MONTH_DAY_HOUR_MINUTE {
		
		/* (non-Javadoc)
		 * @see com.haiyisoft.mobile.android.view.DateTimePrecision#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			return formater.format(date);
		}
		// 
		@Override
		public void createDateDialog(final DateEdit dateEdit) {
			DateTimePickerDialog pickerDialog = new DateTimePickerDialog(dateEdit.getContext(), 
					new DateTimePickerDialog.DateTimeDialogListener() {
						@Override
						public void onDateSet(Date date) {
							dateEdit.setDate(date);
							dateEdit.enabledTouch();
						}
					}, 
					dateEdit.getDate(),true);			
			pickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					dateEdit.enabledTouch();
					
				}
			});
			pickerDialog.show();

		}
	},
	/** 精确到年月日时分的时间对话框. */
	YEAR_MONTH_DAY_HOUR_MINUTE_24 {
		
		/* (non-Javadoc)
		 * @see com.haiyisoft.mobile.android.view.DateTimePrecision#format(java.util.Date)
		 */
		@Override
		public String format(Date date) {
			DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
			return formater.format(date);
		}
		// 
		@Override
		public void createDateDialog(final DateEdit dateEdit) {
			DateTimePickerDialog pickerDialog = new DateTimePickerDialog(dateEdit.getContext(), new DateTimePickerDialog.DateTimeDialogListener() {
						@Override
						public void onDateSet(Date date) {
							dateEdit.setDate(date);
							dateEdit.enabledTouch();
						}
					},dateEdit.getDate(),true);
			pickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					dateEdit.enabledTouch();
					
				}
			});
			pickerDialog.show();

		}
	},
	
	/** 精确到年月日的时间对话框. */
	YEAR_MONTH_DAY {
		Calendar calendar = Calendar.getInstance();

		@Override
		public String format(Date date) {
			DateFormat formater = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
			return formater.format(date);
		}

		@Override
		public void createDateDialog(final DateEdit dateEdit) {
			calendar.setTime(dateEdit.getDate());
			DatePickerDialog pickerDialog = new DatePickerDialog(
					dateEdit.getContext(), new OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							calendar.set(Calendar.YEAR, year);
							calendar.set(Calendar.MONTH, monthOfYear);
							calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
							dateEdit.setDate(calendar.getTime());
							dateEdit.enabledTouch();
						}
					}, 
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			pickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					dateEdit.enabledTouch();
					
				}
			});
			pickerDialog.show();
		}

	},
	
	/** 精确到年月的时间对话框. */
	YEAR_MONTH {
		Calendar calendar = Calendar.getInstance();

		@Override
		public String format(Date date) {
			DateFormat formater = new SimpleDateFormat("yyyy年MM月",Locale.getDefault());
			return formater.format(date);
		}

		@Override
		public void createDateDialog(final DateEdit dateEdit) {
			calendar.setTime(dateEdit.getDate());
			YearMonthDialog dialog = new YearMonthDialog(dateEdit.getContext(), 
					new OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, monthOfYear);
					dateEdit.setDate(calendar.getTime());
					dateEdit.enabledTouch();
				}
			},
			calendar.get(Calendar.YEAR),
			calendar.get(Calendar.MONTH), 1);
			dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					dateEdit.enabledTouch();
					
				}
			});
			dialog.show();
		}
	},
	
	/** 精确到时分的对话框. */
	HOUR_MINUTE {
		Calendar calendar = Calendar.getInstance();

		@Override
		public String format(Date date) {
			DateFormat formater = new SimpleDateFormat("HH:mm",Locale.getDefault());
			return formater.format(date);
		}

		@Override
		public void createDateDialog(final DateEdit dateEdit) {
			calendar.setTime(dateEdit.getDate());
			TimePickerDialog pickerDialog = new TimePickerDialog(
					dateEdit.getContext(), new OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
							calendar.set(Calendar.MINUTE, minute);
							dateEdit.setDate(calendar.getTime());
							dateEdit.enabledTouch();
						}
					}, 
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE), true);
			pickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					dateEdit.enabledTouch();
					
				}
			});
			pickerDialog.show();
		}

	},
	
	/** 精确到年月日时分秒的时间对话框. */
	YEAR_MONTH_DAY_HOUR_MINUTE_SECOND{

		@Override
		public String format(Date date) {
			DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
			return formater.format(date);
		}

		@Override
		public void createDateDialog(final DateEdit dateEdit) {
			DateTimeSecondPickerDialog pickerDialog = new DateTimeSecondPickerDialog(dateEdit.getContext(),
					new DateTimeSecondPickerDialog.DateTimeSecondDialogListener() {
						
						@Override
						public void onDateSet(Date date) {
							dateEdit.setDate(date);
							dateEdit.enabledTouch();
						}
					},
					dateEdit.getDate());
			pickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					dateEdit.enabledTouch();
				}
			});
			pickerDialog.show();
		}
		
	};
	
	/**
	 *赋予时间格式
	 * @param date 时间
	 * @return 格式化后时间的字符串
	 */
	public abstract String format(Date date); 

	/**
	 * 创建对话框.
	 *
	 * @param dateEdit 时间文本编辑框
	 */
	public abstract void createDateDialog(DateEdit dateEdit);

}
