package dq.lelaohui.com.lelaohuipad.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

import dq.lelaohui.com.lelaohuipad.R;

/**
 * TODO: document your custom view class.
 */
public class FootCateListItem extends LinearLayout implements Checkable {

    public FootCateListItem(Context context) {
        super(context);
        init(null, 0);
    }

    public FootCateListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FootCateListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FootCateListItem, defStyle, 0);

//        mExampleString = a.getString(
//                R.styleable.FootCateListItem_exampleString);
//        mExampleColor = a.getColor(
//                R.styleable.FootCateListItem_exampleColor,
//                mExampleColor);
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        mExampleDimension = a.getDimension(
//                R.styleable.FootCateListItem_exampleDimension,
//                mExampleDimension);
//
//        if (a.hasValue(R.styleable.FootCateListItem_exampleDrawable)) {
//            mExampleDrawable = a.getDrawable(
//                    R.styleable.FootCateListItem_exampleDrawable);
//            mExampleDrawable.setCallback(this);
//        }

        a.recycle();
    }
    private boolean isChcek;

    @Override
    public void setChecked(boolean b) {
        isChcek=b;
        if(b){
            setBackgroundColor(Color.BLUE);
        }else{
            setBackgroundColor(Color.CYAN);
        }
    }

    @Override
    public boolean isChecked() {
        return  isChcek;
    }

    @Override
    public void toggle() {

    }
}
