package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerSubescribeData;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/29.
 * 根据选择时间对服务项库存进行查询
 */

public class FilterSubscribeActivity extends LeLaoHuiBaseActivity {
    private  final static  String TAG="FilterSubscribeActivity";
    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatTextView start_time;
    private AppCompatTextView stop_time;
    private AppCompatButton querySubscribe;
    private RecyclerView query_ser_subscribe;
    private SysVar var=null;
    private ServerSubscribeControler serverSubscribeControler=null;
    private String customerId;
    private SerSubescribeData.StockListBean.SerStockDetailListBean serStockDetailListBean=null;
    private  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    private  Date curDate=new Date(System.currentTimeMillis());//获取当前时间
    private  String str=formatter.format(curDate);
    private long serStockDetailId;
    public void  initView(){
        title_tv= (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("客户预约");
        reight_tv= (AppCompatTextView) findViewById(R.id.reight_tv);
        start_time= (AppCompatTextView) findViewById(R.id.start_time);
        stop_time= (AppCompatTextView) findViewById(R.id.stop_time);
        querySubscribe= (AppCompatButton) findViewById(R.id.querySubscribe);
        left_btn= (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        query_ser_subscribe= (RecyclerView) findViewById(R.id.query_ser_subscribe);
        stop_time.setText(str);
        querySubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endTime=stop_time.getText().toString();
                if(serStockDetailListBean!=null){
                    serStockDetailId=serStockDetailListBean.getSerStockDetailId();
                    serverSubscribeControler.doQueryServerSetatlData(customerId,endTime,serStockDetailId);
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var = SysVar.getInstance();
        serverSubscribeControler = (ServerSubscribeControler) getControler();
        if (getIntent()!=null){
            serStockDetailListBean=getIntent().getParcelableExtra("serStockDetailListBean");
            customerId=getIntent().getStringExtra("customerId");
        }
        initView();
    }
    @Override
    public IControler getControler() {
        return ServerSubscribeControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
        if(bundle!=null){
            String action=bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.GET_SERVER_DETAIL_BY_INFO_RESPONSE)){
                List<FilterSubscribeData> filterSubscribeDataList=bundle.getParcelableArrayList("filterSubscribeDataList");
            if (filterSubscribeDataList!=null&&filterSubscribeDataList.size()>0){
                SerStockDetailAdapter serStockDetailAdapter=new SerStockDetailAdapter(FilterSubscribeActivity.this,filterSubscribeDataList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                query_ser_subscribe.setLayoutManager(linearLayoutManager);
                query_ser_subscribe.setAdapter(serStockDetailAdapter);
            }
            }
        }

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_filter_subscribe;
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

    /**
     * 服务预约包
     */
    public class SerStockDetailAdapter extends RecyclerView.Adapter<SerStockDetailAdapter.ViewHolder> {
        Context context;
        List<FilterSubscribeData> serStockDetailListBeen;
        @Override
        public SerStockDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.server_stock_content_item, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        public SerStockDetailAdapter(Context context, List<FilterSubscribeData> serStockDetailListBeen) {
            this.context = context;
            this.serStockDetailListBeen = serStockDetailListBeen;
        }

        @Override
        public void onBindViewHolder(final SerStockDetailAdapter.ViewHolder holder, final int position) {
            holder.server_content_num.setText("");
            holder.server_surplus_num.setText("剩余："+serStockDetailListBeen.get(position).getCurrentNum()+"次");
            holder.end_time.setText("库存号："+serStockDetailListBeen.get(position).getSerStockDetailId());
            holder.server_content_name.setText(serStockDetailListBeen.get(position).getServiceName());
            holder.server_rule_content.setText(serStockDetailListBeen.get(position).getRuleStr());
            holder.server_time_content.setText("");
            holder.server_time.setText("");
            final FilterSubscribeData filterSubscribeData=serStockDetailListBeen.get(position);
            holder.upload_server.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int currentNum=filterSubscribeData.getCurrentNum();
                    if (currentNum>0){
                        Intent intent=new Intent(context,FilterSubscribeActivity.class);
                        intent.putExtra("filterSubscribeData",filterSubscribeData);
                        intent.putExtra("customerId",customerId);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(FilterSubscribeActivity.this,"抱歉，您当前的剩余次数为0，不能预约了",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return serStockDetailListBeen.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatTextView end_time;
            public AppCompatTextView server_content_name;
            public AppCompatTextView server_content_num;
            public AppCompatTextView server_surplus_num;
            public AppCompatTextView server_rule;
            public AppCompatTextView server_rule_content;
            public AppCompatTextView server_time;
            public AppCompatTextView server_time_content;
            public AppCompatButton upload_server;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.end_time = (AppCompatTextView) rootView.findViewById(R.id.end_time);
                this.server_content_name = (AppCompatTextView) rootView.findViewById(R.id.server_content_name);
                this.server_content_num = (AppCompatTextView) rootView.findViewById(R.id.server_content_num);
                this.server_surplus_num = (AppCompatTextView) rootView.findViewById(R.id.server_surplus_num);
                this.server_rule = (AppCompatTextView) rootView.findViewById(R.id.server_rule);
                this.server_rule_content = (AppCompatTextView) rootView.findViewById(R.id.server_rule_content);
                this.server_time = (AppCompatTextView) rootView.findViewById(R.id.server_time);
                this.server_time_content = (AppCompatTextView) rootView.findViewById(R.id.server_time_content);
                this.upload_server = (AppCompatButton) rootView.findViewById(R.id.upload_server);
            }

        }
    }

}
