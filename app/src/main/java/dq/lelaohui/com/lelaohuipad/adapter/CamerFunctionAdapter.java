package dq.lelaohui.com.lelaohuipad.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import dq.lelaohui.com.lelaohuipad.R;

/**
 * Created by ZTF on 2016/12/27.
 * 摄像头功能
 */
public class CamerFunctionAdapter extends BaseAdapter {
    private String[] cammerData={"拍照","监听","对讲","设置WIFI","关闭报警","历史图片"};

    int[] cammerBg = { R.drawable.cammer_selector, R.drawable.monitor_selector,
            R.drawable.talkback_selector, R.drawable.selector_settingwifi,
            R.drawable.llh_alarm_alarm, R.drawable.llh_history_image };
    private LayoutInflater lif=null;
    public CamerFunctionAdapter(Context context){
        lif=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (cammerData==null&&cammerData.length==0){
            return 0;
        }
        return cammerData.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=lif.inflate(R.layout.llh_server_menu_item,null);
        AppCompatImageView image_menu = (AppCompatImageView) view.findViewById(R.id.image_menu);
        AppCompatTextView type_menu_txt = (AppCompatTextView)view. findViewById(R.id.type_menu_txt);
        image_menu.setBackgroundResource(cammerBg[i]);
        type_menu_txt.setText(cammerData[i]);
        return view;
    }
}
