package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.ServerPersonInfoAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonData;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/30.
 * 服务员信息
 */

public class ServerPersonInfoActivity extends LeLaoHuiBaseActivity implements View.OnClickListener {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private ListView server_person_lv;
    private SysVar var = null;
    private ServerSubscribeControler serverSubscribeControler = null;
    private long serviceItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var = SysVar.getInstance();
        serverSubscribeControler = (ServerSubscribeControler) getControler();
        initView();
        serverSubscribeControler.doQueryServerPersonInfo(var.getOrgId(), var.getOrgType(), serviceItemId);
    }

    @Override
    public IControler getControler() {
        return ServerSubscribeControler.getControler();
    }

    private ServerPersonInfoAdapter adapter = null;
    private  List<ServerPersonData> data=new ArrayList<>();
    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.GET_SERVER_INFOS_RESPONSE)) {
                data = bundle.getParcelableArrayList("serverPerson");
                Log.i("ServerPersonInfoAdapter", "getCount:33== "+data.size());
                if (data != null ) {
                    Log.i("ServerPersonInfoAdapter", "getCount:44== "+data.size());
                    adapter=new ServerPersonInfoAdapter(getApplicationContext(),data);
                    server_person_lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_server_person_info;
    }

    public void initView() {
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("选择服务员");
        left_btn = (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(this);
        reight_tv = (AppCompatTextView) findViewById(R.id.reight_tv);
        server_person_lv = (ListView) findViewById(R.id.server_person_lv);
        server_person_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (ListView.CHOICE_MODE_SINGLE==server_person_lv.getChoiceMode()){
                    ServerPersonData serverPersonData=data.get(i);
                    Intent intent=new Intent();
                    intent.putExtra("serverPerson",serverPersonData);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void usable() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_btn:
                onBackPressed();
                break;
        }
    }


}
