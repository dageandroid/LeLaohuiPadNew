package dq.lelaohui.com.lelaohuipad.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;

import dq.lelaohui.com.lelaohuipad.R;

/**
 * Created by ZTF on 2016/11/3.
 *
 */

public class MyPoPuWindow extends ListPopupWindow {
    private View mMenuView;
    public MyPoPuWindow(Context context) {
        super(context);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 设置弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        // 设置弹出窗体的高
        int height = context.getResources().getDisplayMetrics().heightPixels;
        this.setHeight(height * 2 / 4);
        // 设置弹出窗体动画效果
        this.setAnimationStyle(R.style.PopuWindow_AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }
    public MyPoPuWindow(Context context,View view) {
        super(context);
        mMenuView=view;
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 设置弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        // 设置弹出窗体的高
        int height = context.getResources().getDisplayMetrics().heightPixels;
        this.setHeight(height * 2 / 4);
        // 设置弹出窗体动画效果
        this.setAnimationStyle(R.style.PopuWindow_AnimBottom);
        // ListPopupWindow的锚,弹出框的位置是相对当前View的位置
        this.setAnchorView(view);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }
}
