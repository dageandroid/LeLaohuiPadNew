package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPayment;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.controler.SerOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
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
    private SysVar var = null;

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
//        shopping_product_price.setOnClickListener(this);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);
        upload_shopping_car.setOnClickListener(this);

    }

    private SerOrderInfoData infoData = null;
    private double amountPayable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoControler = (SerOrderInfoControler) getControler();
        initView();
        infoControler.doUserAddressInfo();
        var = SysVar.getInstance();
        if (getIntent() != null) {
            infoData = getIntent().getParcelableExtra("infoData");
            String orderCode = infoData.getSerOrderInfo().getOrderCode();
            amountPayable = infoData.getSerOrderInfo().getAmountPayable();
            int payStatus = infoData.getSerOrderInfo().getPayStatus();
            for (int i = 0; i < infoData.getSerOrderInfoDetailBeanList().size(); i++) {
                String packagerName = infoData.getSerOrderInfoDetailBeanList().get(i).getSerOrderInfoDetail().getPackageName();
                Log.i(TAG, "packagerName==" + packagerName);
            }
            Log.i(TAG, "OrderCode==" + orderCode);
            order_number.setText("订单号：" + orderCode);
            order_price_count.setText("总价： ￥" + amountPayable + "元");
            shopping_product_price.setText("总价： ￥" + amountPayable + "元");
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

    private   String outTradeNo=null;//订单号
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
            }else if(action.equals(ServiceNetContant.ServiceResponseAction.SAVE_MOBILE_ORDER_INFO)){
                ServerOrderPayment orderPayment=bundle.getParcelable("orderPayment");
                if (orderPayment!=null){
                     outTradeNo=orderPayment.getOrderCode();
                    int payType=orderPayment.getPayStyle();
                    double payAmt=orderPayment.getAmountPayable();
                    Log.i(TAG,"outTradeNo=="+outTradeNo+",payType=="+payType+"payAmt=="+payAmt);
                    if (payType==1) {
                        infoControler.doServerOrderPayment(outTradeNo,String.valueOf(payAmt),String.valueOf(payType));
                    }
                }
            }else if(action.equals(ServiceNetContant.ServiceResponseAction.UPLOAD_SERVER_ORDER_PAYMENY)){
                if (!TextUtils.isEmpty(outTradeNo)&&!"".equals(outTradeNo)){
                    Intent intent=new Intent(SerOrderInfoActivity.this,SubSerOrderFinishActivity.class);
                    intent.putExtra("outTradeNo",outTradeNo);
                    Log.i(TAG,"ORDERNO==="+outTradeNo);
//                    startActivity(intent);
                    startActivityForResult(intent, ServerMenuActivity.FINISH_ACTION);

                }
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
                String userAddressStr = user_address_view.getText().toString();
                String userNameStr = user_name_view.getText().toString();
                String userPhoneStr = user_phone_view.getText().toString();
                if (TextUtils.isEmpty(userAddressStr) && "".equals(userAddressStr)) {
                    Snackbar.make(v, "地址不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                if (TextUtils.isEmpty(userNameStr) && "".equals(userNameStr)) {
                    Snackbar.make(v, "用户名不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                if (TextUtils.isEmpty(userPhoneStr) && "".equals("")) {
                    Snackbar.make(v, "电话号码不能为空", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                SerOrderInfoData.SerOrderInfoBean serOrderInfo = infoData.getSerOrderInfo();
                String address = userNameStr + "(" + userPhoneStr + "):" + userAddressStr;
                serOrderInfo.setContactAddress(address);
                serOrderInfo.setCustomerName(userNameStr);
                serOrderInfo.setCustomerPhone(userPhoneStr);
                serOrderInfo.setCustomerId(Long.valueOf(var.getUserId()));
                if ("3".equals(var.getOrgId())) {
                    serOrderInfo.setOrgId(Integer.valueOf(var.getOrgId()));
                    serOrderInfo.setOrgTypeId(Integer.valueOf(var.getOrgType()));
                    serOrderInfo.setOrgName(var.getOrgName());
                } else {
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
            case R.id.option_address:
                Intent intent =new Intent(SerOrderInfoActivity.this,MyAddressActivity.class);
                intent.putExtra(Protocol_KEY.IS_FROM_CREAT_ORDER_ADDRESS,true);
                if (var.getUserId()!=null){
                    Log.i(TAG, "var.getUserId()==" + var.getUserId());
                    intent.putExtra(Protocol_KEY.CUSTOMER_ID,var.getUserId());
                    intent.putExtra(Protocol_KEY.CUSTOMER_NAME,var.getUserName());
//                    startActivity(intent);
                    intent.putExtra(Protocol_KEY.IS_FROM_CREAT_ORDER_ADDRESS, true);
                    startActivityForResult(intent, GET_ADDRESS);

                }
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
            viewHolder.order_num_txt.setText("订单号：" + serOrderInfoDetailBeanList.get(position).getSerOrderInfoDetail().getOrderCode());
            viewHolder.product_price.setText("价格：￥" + serOrderInfoDetailBeanList.get(position).getSerOrderInfoDetail().getPrice() + "元");
            viewHolder.order_amount_txt.setText("共" + serOrderInfoDetailBeanList.get(position).getSerOrderInfoDetail().getSerNum() + "件");
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
    public static final int GET_ADDRESS=1011;
    public static final int ADD_ADDRESS=511;
    private  UserAddressData addressModel=null;
    private String addressType = "1";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GET_ADDRESS&& resultCode == RESULT_OK
                && data != null){
            Parcelable pb=data.getParcelableExtra("addressModel");
            if (pb!=null){
                addressModel=(UserAddressData)pb;
                if ( addressModel.getAddressType()==0) {
                    user_name_view.setText("" + addressModel.getUserName());
                    user_phone_view.setText("" + addressModel.getTelephone());
                    user_address_view.setText("" + addressModel.getAddress());
                    addressType = "1";
                } else if (addressModel.getAddressType()==2) {
                    user_name_view.setText("" + addressModel.getUserName());
                    user_phone_view.setText("" + addressModel.getTelephone());
                    user_address_view.setText("" + addressModel.getAddress());
                    addressType = "0";
                } else {
                    user_name_view.setText("" + addressModel.getUserName());
                    user_phone_view.setText("");
                    user_address_view.setText("" + addressModel.getAddress());
                    addressType = "1";
                }
            }
        } else if (requestCode == ADD_ADDRESS && resultCode == RESULT_OK
                && data != null) {
            String userNameStr = data.getStringExtra("name");
            String phoneStr = data.getStringExtra("phone");
            String addressStr = data.getStringExtra("address");
            addressType = "0";
            user_name_view.setText("" + userNameStr);
            user_phone_view.setText("" + phoneStr);
            user_address_view.setText("" + addressStr);
        } else if (requestCode == ServerMenuActivity.FINISH_ACTION
                && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
