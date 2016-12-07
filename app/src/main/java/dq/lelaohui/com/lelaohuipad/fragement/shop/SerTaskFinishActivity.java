package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseFragment;
import dq.lelaohui.com.lelaohuipad.bean.SerTaskFinishData;
import dq.lelaohui.com.lelaohuipad.controler.MyServerTaskControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.Constants;
import dq.lelaohui.com.lelaohuipad.util.StringSubUtils;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.lelaohuipad.util.TimeUtils;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/12/2.
 * 已经完成的服务任务查询
 */

public class SerTaskFinishActivity extends LeLaoHuiBaseFragment implements View.OnClickListener {
    private MyServerTaskControler serverSubscribeControler = null;
    private ViewStub view_sub_time;
    private RecyclerView rv_task_finish;
    private AppCompatTextView start_time_tv;
    private AppCompatTextView stop_time_tv;
    private AppCompatButton quert_data_bt;
    private SysVar var = null;
    private String customerId, customerName;
    private static final String TAG = "SerTaskFinishActivity";
    private static final String TRANS_STATUS_STR="6";//已经完成的任务
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var = SysVar.getInstance();
        serverSubscribeControler = (MyServerTaskControler) getControler();
        if (this.getActivity().getIntent() != null) {
            customerId = this.getActivity().getIntent().getStringExtra("customerId");
            customerName = this.getActivity().getIntent().getStringExtra("customerName");
        }
        customerId = var.getUserId();
        customerName=var.getUserName();
    }
    @Override
    public IControler getControler() {
        return MyServerTaskControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
        if (bundle!=null){
            String action=bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE)){
                List<SerTaskFinishData> serTaskFinishDataList= (List<SerTaskFinishData>) bundle.get("serTaskFinish");
            if (serTaskFinishDataList!=null){
                SerTaskFinishAdapter adapter=new SerTaskFinishAdapter(this.getContext(),serTaskFinishDataList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rv_task_finish.setLayoutManager(linearLayoutManager);
                rv_task_finish.setAdapter(adapter);
            }
            }
        }
    }

    @Override
    protected View initView(View view) {
        rv_task_finish = (RecyclerView) view. findViewById(R.id.rv_task_finish);
        view_sub_time = (ViewStub) view. findViewById(R.id.view_sub_time);
        view_sub_time.inflate();
        start_time_tv = (AppCompatTextView) view. findViewById(R.id.start_time_tv);
        start_time_tv.setOnClickListener(this);
        stop_time_tv = (AppCompatTextView) view. findViewById(R.id.stop_time_tv);
        stop_time_tv.setOnClickListener(this);
        quert_data_bt = (AppCompatButton) view.findViewById(R.id.quert_data_bt);
        quert_data_bt.setText("查询");
        quert_data_bt.setOnClickListener(this);
        return view;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_server_task_finish;
    }
    @Override
    protected void doBusses() {

    }
    @Override
    public void usable() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quert_data_bt:
                String startTime=start_time_tv.getText().toString();
                String stopTime=stop_time_tv.getText().toString();
                serverSubscribeControler.doStockDetailByUser(customerId,startTime,stopTime,TRANS_STATUS_STR);
                break;
        }
    }

    class SerTaskFinishAdapter extends RecyclerView.Adapter<SerTaskFinishAdapter.ViewHolder> {
        private LayoutInflater layoutInflater = null;
        private List<SerTaskFinishData> serTaskFinishDataList=null;
        private static final String TAG="SerTaskFinishAdapter";
        public SerTaskFinishAdapter(Context context,List<SerTaskFinishData> serTaskFinishDataList) {
            layoutInflater = LayoutInflater.from(context);
            this.serTaskFinishDataList=serTaskFinishDataList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =  layoutInflater.inflate(R.layout.ser_task_finish_item,null);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,final int position) {
            {
                holder.gift_packs.setText("预约人："+serTaskFinishDataList.get(position).getCustomerName());
                holder.server_content.setText(serTaskFinishDataList.get(position).getServiceName());
                String timeAdd= TimeUtils.dateToString(serTaskFinishDataList.get(position).getAddTime());
                holder.server_surplus_num.setText(StringSubUtils.subStr(timeAdd));
                String startTime=TimeUtils.dateToString(serTaskFinishDataList.get(position).getSerStartTime());
                String stopTime=TimeUtils.dateToString(serTaskFinishDataList.get(position).getSerEndTime());
                holder.server_tiem_content.setText("开始时间："+StringSubUtils.subStr(startTime)+"  结束时间："+StringSubUtils.subStr(stopTime));
                holder.server_Name.setText(serTaskFinishDataList.get(position).getWaitersName());
                holder.server_Name.setVisibility(View.GONE);
                holder.server_rule_content.setVisibility(View.GONE);
                holder. server_rule.setVisibility(View.GONE);
                holder.server_address.setText("等待处理");
                holder.btn_server_centel.setText("撤单");
                holder.btn_server_exec.setVisibility(View.GONE);
                holder.btn_server_centel.setVisibility(View.GONE);

            }
        }

        @Override
        public int getItemCount() {
            if (serTaskFinishDataList==null&&serTaskFinishDataList.size()==0){
                return 0;
            }
            return serTaskFinishDataList.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatTextView gift_packs;
            public AppCompatTextView server_content;
            public AppCompatTextView server_surplus_num;
            public AppCompatTextView server_rule;
            public AppCompatTextView server_rule_content;
            public AppCompatTextView server_time;
            public AppCompatTextView server_tiem_content;
            public AppCompatTextView server_person;
            public TextView server_Name;
            public TextView server_address;
            public TextView server_num;
            public AppCompatButton btn_server_exec;
            public AppCompatButton btn_server_centel;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.gift_packs = (AppCompatTextView) rootView.findViewById(R.id.gift_packs);
                this.server_content = (AppCompatTextView) rootView.findViewById(R.id.server_content);
                this.server_surplus_num = (AppCompatTextView) rootView.findViewById(R.id.server_surplus_num);
                this.server_rule = (AppCompatTextView) rootView.findViewById(R.id.server_rule);
                this.server_rule_content = (AppCompatTextView) rootView.findViewById(R.id.server_rule_content);
                this.server_time = (AppCompatTextView) rootView.findViewById(R.id.server_time);
                this.server_tiem_content = (AppCompatTextView) rootView.findViewById(R.id.server_tiem_content);
                this.server_person = (AppCompatTextView) rootView.findViewById(R.id.server_person);
                this.server_Name = (TextView) rootView.findViewById(R.id.server_Name);
                this.server_address = (TextView) rootView.findViewById(R.id.server_address);
                this.server_num = (TextView) rootView.findViewById(R.id.server_num);
                this.btn_server_exec = (AppCompatButton) rootView.findViewById(R.id.btn_server_exec);
                this.btn_server_centel = (AppCompatButton) rootView.findViewById(R.id.btn_server_centel);
            }
        }
    }
}
