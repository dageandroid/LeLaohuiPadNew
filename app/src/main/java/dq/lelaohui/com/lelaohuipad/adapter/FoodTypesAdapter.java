package dq.lelaohui.com.lelaohuipad.adapter;

/**
 * Created by ZTF on 2016/11/21.
 */


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import dq.lelaohui.com.lelaohuipad.R;

public class FoodTypesAdapter extends BaseAdapter {

    private List<String> array = new ArrayList<String>();
    private LayoutInflater inflater;
    Context context;

    public FoodTypesAdapter(Context context) {
        this.context=context;
        inflater = LayoutInflater.from(context);
    }
    public FoodTypesAdapter(Context context, List<String> array) {
        this.context=context;
        inflater = LayoutInflater.from(context);
    }
    public void setData(List<String> data) {
        array.clear();
        array.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.food_type_list_item,
                    null);
        }
        TextView type = (TextView) convertView.findViewById(R.id.food_type);
        type.setText(array.get(position));
        return convertView;
    }

}