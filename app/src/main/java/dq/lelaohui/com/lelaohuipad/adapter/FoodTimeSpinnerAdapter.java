package dq.lelaohui.com.lelaohuipad.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import dq.lelaohui.com.lelaohuipad.R;

/**
 * Created by ZTF on 2016/11/18.
 */

public class FoodTimeSpinnerAdapter extends ArrayAdapter<String> {
        private Context mContext;
        private String[] mStringArray;

        public FoodTimeSpinnerAdapter(Context context, String[] stringArray) {
            super(context, R.layout.spinner_layout, stringArray);
            mContext = context;
            mStringArray = stringArray;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // 修改Spinner展开后的字体颜色
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.spinner_layout, parent,
                        false);
            }

            // //此处text1是Spinner默认的用来显示文字的TextView
            TextView tv = (TextView) convertView.findViewById(R.id.spinner_tv);
            tv.setText(mStringArray[position]);
            return convertView;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 修改Spinner选择后结果的字体颜色
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.food_time_sp_item,
                        parent, false);
            }

            // 此处text1是Spinner默认的用来显示文字的TextView
            TextView tv = (TextView) convertView.findViewById(R.id.spinner_tv);
            tv.setText(mStringArray[position]);
            return convertView;
        }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
}
