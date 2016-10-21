package com.lelaohui.ipcarmearlibrary.util;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;

public class SensorCustomListView extends ListView{
	public SensorCustomListView(Context context) {
		super(context);
	}

	public SensorCustomListView(Context context, android.util.AttributeSet attrs) {
		super(context, attrs);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
