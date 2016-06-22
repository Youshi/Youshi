package com.example.hmqqg.hm.util;

import java.util.Date;




import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.hmqqg.hm.R;


public class DateEdit extends EditText implements OnTouchListener {
	
	/**
	 * 时间改变接口.
	 * @see
	 */
	public interface DateChangeListener {
		
		/**
		 * Change.
		 *
		 * @param dateEdit 时间编辑框
		 * @param date 时间
		 */
		public void change(DateEdit dateEdit, final Date date);
	}
	
	/** 当前日期对象. */
	private Date date; 
	
	/** 格式化日期 枚举. */
	private DateTimePrecision datePrecision; 
	
	/** The touch flag. */
	private boolean touchFlag;
	
	/** The date change listener. */
	private DateChangeListener dateChangeListener;
	
	/** The validate date change listener flag. */
	private boolean validateDateChangeListenerFlag = true;//默认有效

	/**
	 * Instantiates a new date edit.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 * @param defStyle the def style
	 */
	public DateEdit(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOnTouchListener(this);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.DateTimeRangeView);
	
		int timeformat = a.getInt(R.styleable.DateTimeRangeView_timeformat, 0);
		for (DateTimePrecision v : DateTimePrecision.values()) {
			if (v.ordinal() == timeformat) {
				datePrecision = v;
			}
		}
		
		setDate(new Date());
		
		touchFlag = false;
		
	}
	
	/* (non-Javadoc)
	 * @see android.widget.EditText#getDefaultEditable()
	 */
	@Override
	protected boolean getDefaultEditable() {
		return false;
	}

	/**
	 *构造一个新的时间编辑框
	 * @param context 上下文
	 * @param attrs 属性集合
	 */
	public DateEdit(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.editTextStyle);
	}
	

	/**
	 * Instantiates a new date edit.
	 * @param context 上下文
	 */
	public DateEdit(Context context) {
		this(context, null);
	}
   
	/**
	 * Gets the date change listener.
	 *
	 * @return the date change listener
	 */
	public DateChangeListener getDateChangeListener() {
		return dateChangeListener;
	}

	/**
	 * Sets the date change listener.
	 *
	 * @param dateChangeListener the new date change listener
	 */
	public void setDateChangeListener(DateChangeListener dateChangeListener) {
		this.dateChangeListener = dateChangeListener;
	}

	/**
	 * Gets the date precision.
	 *
	 * @return the date precision
	 */
	public DateTimePrecision getDatePrecision() {
		return datePrecision;
	}

	/**
	 * Sets the date precision.
	 *
	 * @param datePrecision the new date precision
	 */
	public void setDatePrecision(DateTimePrecision datePrecision) {
		this.datePrecision = datePrecision;
	}
		
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
		String dateString = datePrecision.format(date);
		setText(dateString);
		if (getDateChangeListener() != null && validateDateChangeListenerFlag) {
			getDateChangeListener().change(this, this.date);
		}
	}
	
	/**
	 * Checks if is validate date change listener flag.
	 *
	 * @return true, if is validate date change listener flag
	 */
	public boolean isValidateDateChangeListenerFlag() {
		return validateDateChangeListenerFlag;
	}

	/**
	 * Sets the validate date change listener flag.
	 *
	 * @param validateDateChangeListenerFlag the new validate date change listener flag
	 */
	public void setValidateDateChangeListenerFlag(
			boolean validateDateChangeListenerFlag) {
		this.validateDateChangeListenerFlag = validateDateChangeListenerFlag;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * 触摸时触发事件.
	 *
	 * @param v 事件源对象
	 * @param event 触发事件封装类对象
	 * @return 如果消费touch事件操作返回true，否则返回false
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (touchFlag) {
			return false;
		}
		
		touchFlag = true;
		hideKeyboard(v);

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			datePrecision.createDateDialog(this);
		}
		return false;
	}
	
	/**
	 * Enabled touch.
	 */
	public void enabledTouch() {
		touchFlag = false;
	}

	/**
	 * 隐藏屏幕键盘.
	 *
	 * @param v 视图对象
	 */
	private void hideKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);		
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
}
