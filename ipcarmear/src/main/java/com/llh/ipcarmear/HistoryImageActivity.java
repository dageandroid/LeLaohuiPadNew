package com.llh.ipcarmear;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.llh.ipcarmear.R;
import com.llh.ipcarmear.util.LLHUtils;

/**
 * 获取探望记录图片
 */
public class HistoryImageActivity extends Activity implements OnClickListener {
    private AppCompatImageButton close;
    private int num = 0;
    private ArrayList<ImageView> array = null;
    private ViewPager viewpager;
    private TextView num_text;
    private ImageView[] mImageView;
    private ViewGroup mViewGroup ;
    private static final String TAG="HistoryImageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_image);
        num_text = (TextView) findViewById(R.id.num_text);
        close = (AppCompatImageButton) findViewById(R.id.break_img_btn);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        close.setOnClickListener(this);
        num = LLHUtils.getBtmapCount(3);
        viewpager.setVisibility(View.GONE);
        mViewGroup = (ViewGroup) findViewById(R.id.point_layout);
        mViewGroup.setVisibility(View.VISIBLE);
        array = new ArrayList<ImageView>();
        getImageHistory();
    }

    private void getImageHistory() {
        if (num <= 0) {
            num = LLHUtils.getBtmapCount(2);
            Log.i(TAG,"图片数量=="+num);
        }
        if (num <= 0) {
            Toast.makeText(this, "没有探望记录", Toast.LENGTH_SHORT).show();
        } else {
            num_text.setVisibility(View.GONE);
            viewpager.setCurrentItem(0);
            viewpager.setVisibility(View.VISIBLE);
            mViewGroup.setVisibility(View.VISIBLE);
            LLHUtils.readAllBitmap();
            array.clear();
            for (int i = 0; i < LLHUtils.arr.size(); i++) {
                ImageView image = new ImageView(HistoryImageActivity.this);
                image.setImageBitmap(LLHUtils.arr.get(i));
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                        LLHUtils.arr.get(i).getWidth(), LLHUtils.arr.get(i)
                        .getHeight());
                image.setLayoutParams(llp);
                image.setScaleType(ScaleType.CENTER_CROP);
                image.setBackgroundColor(Color.BLUE);
                array.add(image);
            }
            mImageView = new ImageView[array.size()];

            for (int i = 0; i < array.size(); i++) {
                ImageView mImageView2 = new ImageView(this);
                mImageView2.setLayoutParams(new LayoutParams(40, 40));
                mImageView2.setPadding(20, 0, 20, 0);
                mImageView[i] = mImageView2;
                if (i == 0) {
                    mImageView[i]
                            .setBackgroundResource(R.drawable.llh_indicator_dot_selected);
                } else {
                    mImageView[i]
                            .setBackgroundResource(R.drawable.llh_indicator_dot_normal);
                }
                mViewGroup.addView(mImageView[i]);
            }

            PagerAdapter mPagerAdapter = new PagerAdapter() {
                @Override
                public boolean isViewFromObject(View arg0, Object arg1) {
                    return arg0 == arg1;
                }

                @Override
                public int getCount() {
                    return array.size();
                }

                @Override
                public void destroyItem(View container, int position,
                                        Object object) {
                    ((ViewPager) container).removeView(array.get(position));
                }

                @Override
                public Object instantiateItem(View container, int position) {
                    ((ViewPager) container).addView(array.get(position));
                    return array.get(position);
                }
            };
            viewpager.setAdapter(mPagerAdapter);
            viewpager.setOnPageChangeListener(new OnPageChangeListener() {

                @Override
                public void onPageSelected(int arg0) {
                    for (int i = 0; i < mImageView.length; i++) {
                        System.out.println("mImageView.length=="+mImageView.length);
                        mImageView[arg0]
                                .setBackgroundResource(R.drawable.llh_indicator_dot_selected);
                        if (arg0 != i) {
                            mImageView[i]
                                    .setBackgroundResource(R.drawable.llh_indicator_dot_normal);
                        }
                    }
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            });
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.break_img_btn) {
            finish();
            return;
        }
    }
}
