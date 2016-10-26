package com.lelaohui.limagelibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ThinkPad on 2016/10/25.
 */
public class ImageUtil {
    private static ImageUtil ourInstance = null;
    private Context mContext;

    private ImageUtil() {

    }

    public static ImageUtil getInstance() {
        if (ourInstance == null) {
            synchronized (ImageUtil.class) {
                if (ourInstance == null) {
                    ourInstance = new ImageUtil();
                }
            }
        }
        return ourInstance;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * ALPHA_8：每个像素占用1byte内存
     * ARGB_4444:每个像素占用2byte内存
     * ARGB_8888:每个像素占用4byte内存
     * RGB_565:每个像素占用2byte内存
     *
     * @param url
     * @param imageView
     */
    public void loadImage(String url, ImageView imageView) {
        if (mContext == null) {
            throw new RuntimeException("mContext is null");
        }
        Picasso.with(mContext).load(url).config(Bitmap.Config.RGB_565)
                .into(imageView);
    }

}
