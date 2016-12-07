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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseFragment;
import dq.lelaohui.com.lelaohuipad.bean.MySerSubescribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerTaskFinishData;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.Constants;
import dq.lelaohui.com.lelaohuipad.util.StringSubUtils;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.lelaohuipad.util.TimeUtils;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/12/6.
 * 我的服务预约信息
 */
public class MySerSubscribeActivity extends LeLaoHuiBaseFragment {
    private ServerSubscribeControler serverSubscribeControler = null;
    private RecyclerView my_ser_subscribe;
    private SysVar var = null;
    private String customerId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverSubscribeControler=(ServerSubscribeControler)getControler();
        var = SysVar.getInstance();
        customerId=var.getUserId();
        serverSubscribeControler.doQueryStockDetailByUser(customerId);
    }
    @Override
    public IControler getControler() {
        return ServerSubscribeControler.getControler();
    }

    @Override
    protected View initView(View view) {
        my_ser_subscribe = (RecyclerView) view.findViewById(R.id.my_ser_subscribe);
        return view;
    }

    @Override
    public void result(Bundle bundle) {
        if (bundle!=null){
            String action=bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.SEARCH_APPOINTMENT_FOR_APP_RESPONSE)){
                List<MySerSubescribeData> mySerSubescribeList= (List<MySerSubescribeData>) bundle.get("serTaskFinish");
                if (mySerSubescribeList!=null){
                    MySerSubescribeAdapter adapter=new MySerSubescribeAdapter(this.getContext(),mySerSubescribeList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    my_ser_subscribe.setLayoutManager(linearLayoutManager);
                    my_ser_subscribe.setAdapter(adapter);
                }
            }else if (action.equals(ServerSubscribeControler.MY_SUBSCRIBE_CENTER)){
                Toast.makeText(getContext(),"我的预约操作。。。",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_ser_subscribe_stock;
    }

    @Override
    protected void doBusses() {
    }

    @Override
    public void usable() {
    }

    class MySerSubescribeAdapter extends RecyclerView.Adapter<MySerSubescribeAdapter.ViewHolder> {
        private LayoutInflater layoutInflater = null;
        private List<MySerSubescribeData> mySerSubescribeList=null;
        private static final String TAG="MySerSubescribeAdapter";
        public MySerSubescribeAdapter(Context context, List<MySerSubescribeData> mySerSubescribeList) {
            layoutInflater = LayoutInflater.from(context);
            this.mySerSubescribeList=mySerSubescribeList;
        }
        @Override
        public MySerSubescribeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =  layoutInflater.inflate(R.layout.ser_task_finish_item,null);
            MySerSubescribeAdapter.ViewHolder vh = new MySerSubescribeAdapter.ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(MySerSubescribeAdapter.ViewHolder holder,final int position) {
            holder.gift_packs.setText("预约人："+mySerSubescribeList.get(position).getCustomerName());
            holder.server_content.setText(mySerSubescribeList.get(position).getServiceName());
            String timeAdd= TimeUtils.dateToString(mySerSubescribeList.get(position).getAddTime());
            holder.server_surplus_num.setText(StringSubUtils.subStr(timeAdd));
            String startTime=TimeUtils.dateToString(mySerSubescribeList.get(position).getSerStartTime());
            String stopTime=TimeUtils.dateToString(mySerSubescribeList.get(position).getSerEndTime());
            holder.server_tiem_content.setText("开始时间："+StringSubUtils.subStr(startTime)+"  结束时间："+StringSubUtils.subStr(stopTime));
            holder.server_Name.setText(mySerSubescribeList.get(position).getWaitersName());
            holder.server_Name.setVisibility(View.GONE);
            holder.server_rule_content.setVisibility(View.GONE);
            holder. server_rule.setVisibility(View.GONE);
            if ("1".equals(mySerSubescribeList.get(position).getTransStatus())){
                holder.server_address.setText("指派中");
                holder.btn_server_centel.setText("取消");
                holder.btn_server_exec.setVisibility(View.INVISIBLE);
                holder.btn_server_centel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long serTransId=mySerSubescribeList.get(position).getSerTransId();
                        serverSubscribeControler.doUploadReSend(serTransId, Constants.SERVICE_HAVE_CANCEL);
                    }
                });
            }else if ("2".equals(mySerSubescribeList.get(position).getTransStatus())){
                holder.server_address.setText("确认中");
                holder.btn_server_centel.setText("乐老汇派");
                holder.btn_server_exec.setVisibility(View.INVISIBLE);
                holder.btn_server_centel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long serTransId=mySerSubescribeList.get(position).getSerTransId();
                        serverSubscribeControler.doUploadReSend(serTransId,Constants.WAITE_FOR_ASSIGN);
                    }
                });

            }else if ("3".equals(mySerSubescribeList.get(position).getTransStatus())){
                holder.server_address.setText("繁忙");
                holder.btn_server_centel.setText("乐老汇派");
                holder.btn_server_exec.setVisibility(View.INVISIBLE);
                holder.btn_server_centel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long serTransId=mySerSubescribeList.get(position).getSerTransId();
                        serverSubscribeControler.doUploadReSend(serTransId,Constants.WAITE_FOR_ASSIGN);
                    }
                });
            }else if ("4".equals(mySerSubescribeList.get(position).getTransStatus())){
                holder.server_address.setText("已确认");
                holder.btn_server_centel.setText("撤单");
                holder.btn_server_exec.setVisibility(View.INVISIBLE);
                holder.server_num.setText("");
                holder.btn_server_centel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long serTransId=mySerSubescribeList.get(position).getSerTransId();
                        serverSubscribeControler.doUploadReSend(serTransId,Constants.SERVICE_HAVE_DENYED);
                    }
                });
            }else{
                holder.server_address.setText("等待处理");
                holder.btn_server_centel.setText("撤单");
                holder.btn_server_exec.setVisibility(View.GONE);
                holder.btn_server_centel.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            if (mySerSubescribeList==null&&mySerSubescribeList.size()==0){
                return 0;
            }
            return mySerSubescribeList.size();
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
