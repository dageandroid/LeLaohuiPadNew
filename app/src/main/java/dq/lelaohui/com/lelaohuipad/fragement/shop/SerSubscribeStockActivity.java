package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseFragment;
import dq.lelaohui.com.lelaohuipad.bean.SerSubescribeData;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.port.IControlerCallBack;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.NetManager;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/28.
 * 客户服务预约库存
 */
public class SerSubscribeStockActivity extends LeLaoHuiBaseFragment implements NetManager.NetStatueCallBack,IControlerCallBack {
    private RecyclerView my_ser_subscribe;
    private ServerSubscribeControler serverSubscribeControler = null;
    private SysVar var = null;
    private String customerId, customerName;
    private static final String TAG = "SerSubscribeStockActivity";

    @Override
    protected View initView(View view) {
        my_ser_subscribe = (RecyclerView) view.findViewById(R.id.my_ser_subscribe);
        return view;
    }

    @Override
    protected void doBusses() {
        serverSubscribeControler = (ServerSubscribeControler) getControler();
        var = SysVar.getInstance();
        if (this.getActivity().getIntent() != null) {
            customerId = this.getActivity().getIntent().getStringExtra("customerId");
            customerName = this.getActivity().getIntent().getStringExtra("customerName");
        } else {
        }
        customerId = var.getUserId();
        customerName=var.getUserName();
        serverSubscribeControler.doQueryServerSetatlInfo(customerId);
    }

    @Override
    public IControler getControler() {
        return ServerSubscribeControler.getControler();
    }

    MySerSubStockAdapter mySerSubStockAdapter = null;

    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.GET_STOCK_DETAIL_BY_USER_RESPONSE)) {
                List<SerSubescribeData> serSubescribeData = bundle.getParcelableArrayList("serSubescribeDataList");
                if (serSubescribeData != null && serSubescribeData.size() > 0) {
                    mySerSubStockAdapter = new MySerSubStockAdapter(this.getContext(), serSubescribeData);
                    Log.i("", "serSubescribeData.get(0).getOrderId()===" + serSubescribeData.get(0).getOrderId());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    my_ser_subscribe.setLayoutManager(linearLayoutManager);
                    my_ser_subscribe.setAdapter(mySerSubStockAdapter);
                }
            }
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ser_subscribe_stock;
    }

    @Override
    public void usable() {

    }

    /**
     * 服务预约订单号
     */
    public class MySerSubStockAdapter extends RecyclerView.Adapter<MySerSubStockAdapter.ViewHolder> {
        private List<SerSubescribeData> serSubescribeData;
        private Context context;

        public MySerSubStockAdapter(Context context, List<SerSubescribeData> serSubescribeData) {
            this.context = context;
            this.serSubescribeData = serSubescribeData;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_server_subscribe_stock_item, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyStockAdapter myStockAdapter = new MyStockAdapter(context, serSubescribeData.get(position).getStockList());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.stock_data_rv.setLayoutManager(linearLayoutManager);
            holder.stock_data_rv.setAdapter(myStockAdapter);
            holder.order_code.setText("订单号："+serSubescribeData.get(position).getOrderId() + "");
        }

        @Override
        public int getItemCount() {
            return serSubescribeData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatTextView order_code;
            public AppCompatTextView order_time;
            public RecyclerView stock_data_rv;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.order_code = (AppCompatTextView) rootView.findViewById(R.id.order_code);
                this.order_time = (AppCompatTextView) rootView.findViewById(R.id.order_time);
                this.stock_data_rv = (RecyclerView) rootView.findViewById(R.id.stock_data_rv);
            }

        }
    }

    /**
     * 服务预约包
     */
    public class MyStockAdapter extends RecyclerView.Adapter<MyStockAdapter.ViewHolder> {
        Context context;
        List<SerSubescribeData.StockListBean> stockListBeen;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.server_stock_detail_list, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        public MyStockAdapter(Context context, List<SerSubescribeData.StockListBean> stockListBeen) {
            this.context = context;
            this.stockListBeen = stockListBeen;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MySerStockDetailAdapter mySerStockDetailAdapter=new MySerStockDetailAdapter(context,stockListBeen.get(position).getSerStockDetailList());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.detail_list_data.setLayoutManager(linearLayoutManager);
            holder.detail_list_data.setAdapter(mySerStockDetailAdapter);

            holder.gift_packs.setText(stockListBeen.get(position).getPackageName());
        }

        @Override
        public int getItemCount() {
            return stockListBeen.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatTextView gift_packs;
            public RecyclerView detail_list_data;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.gift_packs = (AppCompatTextView) rootView.findViewById(R.id.gift_packs);
                this.detail_list_data = (RecyclerView) rootView.findViewById(R.id.detail_list_data);
            }

        }
    }

    /**
     * 服务预约包
     */
    public class MySerStockDetailAdapter extends RecyclerView.Adapter<MySerStockDetailAdapter.ViewHolder> {
        Context context;
        List<SerSubescribeData.StockListBean.SerStockDetailListBean> serStockDetailListBeen;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.server_stock_content_item, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        public MySerStockDetailAdapter(Context context, List<SerSubescribeData.StockListBean.SerStockDetailListBean> serStockDetailListBeen) {
            this.context = context;
            this.serStockDetailListBeen = serStockDetailListBeen;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.server_content_num.setText("总："+serStockDetailListBeen.get(position).getTotalNum()+"次");
            holder.server_surplus_num.setText("剩余："+serStockDetailListBeen.get(position).getCurrentNum()+"次");
            holder.end_time.setText("库存号："+serStockDetailListBeen.get(position).getSerStockDetailId());
            holder.server_content_name.setText(serStockDetailListBeen.get(position).getServiceName());
            holder.server_rule_content.setText(serStockDetailListBeen.get(position).getRuleStr());
            holder.server_rule_content.setText(serStockDetailListBeen.get(position).getExecDates());
            int isEnable=   serStockDetailListBeen.get(position).getIsEnable();
            int currentNum= serStockDetailListBeen.get(position).getCurrentNum();
          final   SerSubescribeData.StockListBean.SerStockDetailListBean serStockDetailListBean=     serStockDetailListBeen.get(position);

       if (isEnable!=1||currentNum==0){
           holder.upload_server.setText("不可预约");
       }else {
           holder.upload_server.setText("预约");
       }
            holder.upload_server.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,""+holder.upload_server.getText().toString(),Toast.LENGTH_LONG).show();

                    int isEnable=   serStockDetailListBeen.get(position).getIsEnable();
                    int currentNum= serStockDetailListBeen.get(position).getCurrentNum();
                if(isEnable!=1||currentNum==0){
                    Toast.makeText(context,"抱歉该服务项不可预约",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent=new Intent(context,FilterSubscribeActivity.class);
                    intent.putExtra("serStockDetailListBean",serStockDetailListBean);
                    intent.putExtra("customerId",customerId);
                    intent.putExtra("customerName",customerName);
                    context.startActivity(intent);
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
