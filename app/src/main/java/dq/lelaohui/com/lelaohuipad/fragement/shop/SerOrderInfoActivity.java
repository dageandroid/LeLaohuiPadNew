package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfo;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderStoreBean;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.controler.SerOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoActivity extends LeLaoHuiBaseActivity implements View.OnClickListener {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatTextView user_name_view;
    private AppCompatTextView user_phone_view;
    private AppCompatTextView user_address_view;
    private RadioButton radioButton;
    private RecyclerView server_type_menu;
    private LinearLayout order_content;
    private SerOrderInfoControler infoControler = null;
    private String TAG = getClass().getSimpleName();
    private AppCompatTextView order_number;
    private AppCompatTextView order_price_count;
    private AppCompatTextView pay_status;
    private CardView food_content_cv;
    private MyAdapter adapter = null;
    private TextView option_address;
    private TextView option_pay;
    private AppCompatTextView shopping_product_price;
    private AppCompatButton upload_shopping_car;
    private SysVar var=null;
    public void initView() {
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        left_btn = (AppCompatImageButton) findViewById(R.id.left_btn);
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        reight_tv = (AppCompatTextView) findViewById(R.id.reight_tv);
        user_name_view = (AppCompatTextView) findViewById(R.id.user_name_view);
        user_phone_view = (AppCompatTextView) findViewById(R.id.user_phone_view);
        user_address_view = (AppCompatTextView) findViewById(R.id.user_address_view);
        order_content = (LinearLayout) findViewById(R.id.order_content);
        server_type_menu = (RecyclerView) findViewById(R.id.server_type_menu);
        title_tv.setText("提交订单");
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        order_number = (AppCompatTextView) findViewById(R.id.order_number);
        order_price_count = (AppCompatTextView) findViewById(R.id.order_price_count);
        pay_status = (AppCompatTextView) findViewById(R.id.pay_status);
        food_content_cv = (CardView) findViewById(R.id.food_content_cv);
        option_address = (TextView) findViewById(R.id.option_address);
        option_address.setOnClickListener(this);
        option_pay = (TextView) findViewById(R.id.option_pay);
        option_pay.setOnClickListener(this);
        shopping_product_price = (AppCompatTextView) findViewById(R.id.shopping_product_price);
        shopping_product_price.setOnClickListener(this);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);
        upload_shopping_car.setOnClickListener(this);

    }
    private  SerOrderInfoData infoData=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoControler = (SerOrderInfoControler) getControler();
        initView();
        infoControler.doUserAddressInfo();
        var= SysVar.getInstance();
        if (getIntent() != null) {
             infoData = getIntent().getParcelableExtra("infoData");
            String orderCode = infoData.getSerOrderInfo().getOrderCode();
            double amountPayable = infoData.getSerOrderInfo().getAmountPayable();
            int payStatus = infoData.getSerOrderInfo().getPayStatus();
            for(int i=0;i<infoData.getSerOrderInfoDetailBeanList().size();i++){
             String packagerName=   infoData.getSerOrderInfoDetailBeanList().get(i).getSerOrderInfoDetail().getPackageName();
                Log.i(TAG,"packagerName=="+packagerName);
            }
            Log.i(TAG, "OrderCode==" + orderCode);
            order_number.setText("订单号：" + orderCode);
            order_price_count.setText("总价： ￥" + amountPayable + "元");
            if (payStatus == 0) {
                pay_status.setText("未支付");
            }
            adapter = new MyAdapter(SerOrderInfoActivity.this, infoData.getSerOrderInfoDetailBeanList());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SerOrderInfoActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            server_type_menu.setLayoutManager(linearLayoutManager);
            server_type_menu.setAdapter(adapter);
        }
    }

    @Override
    public IControler getControler() {
        return SerOrderInfoControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE)) {
                UserAddressData data = bundle.getParcelable("userAddress");
                String userAddress = data.getAddress();
                String userName = data.getUserName();
                String userPhone = data.getTelephone();
                user_address_view.setText(userAddress);
                user_name_view.setText(userName);
                user_phone_view.setText(userPhone);
            }
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.cofirm_order_content;
    }

    @Override
    public void usable() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_shopping_car:
                String userAddressStr=user_address_view.getText().toString();
                  String userNameStr=user_name_view.getText().toString();
              String userPhoneStr=user_phone_view.getText().toString();
            if(TextUtils.isEmpty(userAddressStr)&&"".equals(userAddressStr)){
                Snackbar.make(v, "地址不能为空", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return;
            }
                if (TextUtils.isEmpty(userNameStr)&&"".equals(userNameStr)){
                    Snackbar.make(v, "用户名不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
            if(TextUtils.isEmpty(userPhoneStr)&&"".equals("")){
                Snackbar.make(v, "电话号码不能为空", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
           return;
            }
                SerOrderInfoData.SerOrderInfoBean serOrderInfo= infoData.getSerOrderInfo();
                String address =userNameStr+"("+userPhoneStr+"):"+userAddressStr;
                serOrderInfo.setContactAddress(address);
                serOrderInfo.setCustomerName(userNameStr);
                serOrderInfo.setCustomerPhone(userPhoneStr);
                serOrderInfo.setCustomerId(Long.valueOf(var.getUserId()));
                if ("3".equals(var.getOrgId())) {
                    serOrderInfo.setOrgId(Integer.valueOf(var.getOrgId()));
                    serOrderInfo.setOrgTypeId(Integer.valueOf(var.getOrgType()));
                    serOrderInfo.setOrgName(var.getOrgName());
                }else{
                    serOrderInfo.setSupplierId(Long.valueOf(var.getOrgId()));
                    serOrderInfo.setSupplierTypeId(Integer.valueOf(var.getOrgType()));
                    serOrderInfo.setSupplierName(var.getOrgName());
                    serOrderInfo.setOrgId(Integer.valueOf(var.getCenterId()));
                    serOrderInfo.setOrgTypeId(Integer.valueOf(3));
                    serOrderInfo.setOrgName(var.getCenterName());
                }
                serOrderInfo.setOrderPersionId(Long.parseLong(var.getUserId()));
                serOrderInfo.setOrderPersionName(var.getUserName());
                serOrderInfo.setPayStyle(1);
                infoData.setSerOrderInfo(serOrderInfo);
                infoControler.uploadShoppingData(infoData);
                break;
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<SerOrderInfoData.SerOrderInfoDetailBeanListBean> serOrderInfoDetailBeanList;
        Context context;

        public MyAdapter(Context context, List<SerOrderInfoData.SerOrderInfoDetailBeanListBean> serOrderInfoDetailBeanList) {
            this.context = context;
            this.serOrderInfoDetailBeanList = serOrderInfoDetailBeanList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.confirm_order_item_content, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.product_name.setText("商品名：" + serOrderInfoDetailBeanList.get(position).getSerOrderInfoDetail().getPackageName());
        }

        @Override
        public int getItemCount() {
            return serOrderInfoDetailBeanList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public ImageView order_image;
            public AppCompatTextView product_name;
            public AppCompatTextView order_num_txt;
            public AppCompatTextView order_amount_txt;
            public AppCompatTextView product_price;
            public CardView food_content_cv;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.order_image = (ImageView) rootView.findViewById(R.id.order_image);
                this.product_name = (AppCompatTextView) rootView.findViewById(R.id.product_name);
                this.order_num_txt = (AppCompatTextView) rootView.findViewById(R.id.order_num_txt);
                this.order_amount_txt = (AppCompatTextView) rootView.findViewById(R.id.order_amount_txt);
                this.product_price = (AppCompatTextView) rootView.findViewById(R.id.product_price);
                this.food_content_cv = (CardView) rootView.findViewById(R.id.food_content_cv);
            }

        }
    }

}
