package dq.lelaohui.com.lelaohuipad.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
	private boolean mChecked;
	public CheckableLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setChecked(boolean checked) {
		mChecked = checked;
//		setBackgroundDrawable(checked ? new ColorDrawable(0xff0660a0) : null);
//		setBackgroundDrawable(checked ? new ColorDrawable(0xffffff00) : null);
		setBackgroundDrawable(checked ? new ColorDrawable(0xff00a0ff) : null);
	}

	@Override
	public boolean isChecked() {
		return mChecked;
	}

	@Override
	public void toggle() {
		setChecked(!mChecked);
	}

}
