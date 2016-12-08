package dq.lelaohui.com.lelaohuipad.fragement.shop.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.SoftReference;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.FootCateBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.FootCateBeanDao;

/**
 * Created by ThinkPad on 2016/11/28.
 */

public class MyFoodTypeRecyleViewAdapter extends CursorAdapter{
    private SoftReference<FootCateBeanDao> softReference = null;
    private LayoutInflater layoutInflater = null;
    private String TAG = "MyFoodTypeRecyleViewAdapter";
    private int index;
    private Cursor tempCursor;
    public MyFoodTypeRecyleViewAdapter(Context context, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        index=c==null?-1:c.getColumnIndex("CATE_ID");
        tempCursor=c;
    }

    public void setDao(FootCateBeanDao dao) {
        softReference = new SoftReference<FootCateBeanDao>(dao);
    }

    public MyFoodTypeRecyleViewAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getItemId(int position) {
        if ( mCursor.moveToPosition(position)) {
            return mCursor.getLong(index);
        } else {
            return 0;
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View item = layoutInflater.inflate(R.layout.foot_cate_list_item, parent,false);
        return item;
    }

    @Override
    public Object getItem(int position) {
       try {
           Cursor cursor= (Cursor) super.getItem(position);
           FootCateBeanDao beanDao = softReference.get();
           FootCateBean fc = beanDao.readEntity(cursor, 0);

           return fc;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        FootCateBeanDao beanDao = softReference.get();
        if (beanDao != null) {
            FootCateBean fc = beanDao.readEntity(cursor, 0);
            if (view == null) {
                return;
            }
            AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.cateName);
            if (!TextUtils.isEmpty(fc.getCateName())) {
                textView.setText(fc.getCateName());
            }
        }
    }
}
