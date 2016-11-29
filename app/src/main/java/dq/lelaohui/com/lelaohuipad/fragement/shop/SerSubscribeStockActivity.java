package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseFragment;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;

/**
 * Created by ZTF on 2016/11/28.
 * 客户服务预约库存
 */
public class SerSubscribeStockActivity extends LeLaoHuiBaseFragment {
    private RecyclerView my_ser_subscribe;
    private ServerSubscribeControler serverSubscribeControler = null;
    private SysVar var = null;
    private String customerId, customerName;

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
            customerId = var.getUserId();
        }
        serverSubscribeControler.doQueryServerSetatlInfo(var.getUserId());
    }

    @Override
    public IControler getControler() {
        return ServerSubscribeControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {


    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ser_subscribe_stock;
    }

    public class MySerSubStockAdapter extends RecyclerView.Adapter<MySerSubStockAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_server_subscribe_stock_item, parent, false);
           ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
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

}
