package dq.lelaohui.com.lelaohuipad.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;


/**
 * 购物车显示
 */
public class MyListPopWindowAdapter extends BaseAdapter {
    private Context context;
    private List<ShoppingCarListBean> dataList;
    private LayoutInflater inflater;


    public MyListPopWindowAdapter(Context context, List<ShoppingCarListBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.lv_popwindow_item, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) view.findViewById(R.id.pro_name);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            holder.tv_num = (TextView) view.findViewById(R.id.tv_num);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_name.setText(dataList.get(i).getProName());


        return view;
    }

    private class ViewHolder {
        TextView tv_name, tv_price, tv_num;
        ImageView im_reduce, im_add;
    }
}
