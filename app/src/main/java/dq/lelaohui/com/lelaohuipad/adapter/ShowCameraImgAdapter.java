package dq.lelaohui.com.lelaohuipad.adapter;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.ShowCamInfo;

public class ShowCameraImgAdapter extends BaseAdapter {
	List<ShowCamInfo> bitmaplv = new ArrayList<ShowCamInfo>();
	boolean showOnline;
	public List<ShowCamInfo> getBitmaplv() {
		return bitmaplv;
	}

	public void setBitmaplv(List<ShowCamInfo> bitmaplv) {
		this.bitmaplv = bitmaplv;
	}
	@Override
	public int getCount() {
		if (bitmaplv == null || bitmaplv.size() == 0) {
			return 0;
		}
		return bitmaplv.size();
	}

	private LayoutInflater lif = null;

	public ShowCameraImgAdapter(Context context, List<ShowCamInfo> bitmaplv, boolean showOnline) {
		lif = LayoutInflater.from(context);
		this.bitmaplv=bitmaplv;
		this.showOnline=showOnline;
	}

	@Override
	public Object getItem(int position) {
		return bitmaplv.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = lif.inflate(R.layout.show_camear, null);
		ImageView iv = (ImageView) convertView.findViewById(R.id.imageshow);
		iv.setImageBitmap(bitmaplv.get(position).getBitmap());
		AppCompatTextView deviceNametv = (AppCompatTextView) convertView.findViewById(R.id.deviceNametv);
		deviceNametv.setText(bitmaplv.get(position).getDeviceName());
		ImageView play = (ImageView) convertView.findViewById(R.id.play);
		ImageView online = (ImageView) convertView.findViewById(R.id.online);
		if (showOnline) {
			online.setVisibility(View.GONE);
			play.setVisibility(View.VISIBLE);
		}else{
			online.setVisibility(View.VISIBLE);
			play.setVisibility(View.GONE);
		}
		return convertView;	
	}

}
