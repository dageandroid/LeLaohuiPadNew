package dq.lelaohui.com.lelaohuipad.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonData;

/**
 * Created by ZTF on 2016/12/2.
 */


public class ServerPersonInfoAdapter extends BaseAdapter {
    public List<ServerPersonData> getData() {
        return data;
    }

    private LayoutInflater li;

    public void setData(List<ServerPersonData> data) {
        this.data = data;
    }

    private List<ServerPersonData> data;
    private Context context;
    public ServerPersonInfoAdapter(Context context, List<ServerPersonData> data) {
        this.context = context;
        this.data = data;
        this.li = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (data == null && data.size() == 0) {
            return 0;
        }
        Log.i("ServerPersonInfoAdapter", "getCount:== "+data.size());
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View rootView, ViewGroup viewGroup) {
        ViewHolder h=null;

        if (rootView == null) {
            rootView = li.inflate(R.layout.server_person_info_item, null);
            h=new ViewHolder();
            h.server_Name = (TextView) rootView.findViewById(R.id.server_Name);
            h.server_address = (TextView) rootView.findViewById(R.id.server_address);
            h.server_num = (TextView) rootView.findViewById(R.id.server_num);
            rootView.setTag(h);
        }else{
            h= (ViewHolder) rootView.getTag();
        }
        h.server_Name.setText(data.get(i).getUserName());
        if (data.get(i).getSex().equals("1")){
            h.server_address.setText("男");
        }else{
            h.server_address.setText("女");
        }
        h.server_num.setText("工作："+data.get(i).getExperience()+"年");
        return rootView;
    }

    public  class ViewHolder {
        public TextView server_Name;
        public TextView server_address;
        public TextView server_num;
    }
}