package dq.lelaohui.com.lelaohuipad.base;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.bean.BaseOrderCate;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ServerOrderPayment;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.controler.FootterControler;
import dq.lelaohui.com.lelaohuipad.controler.SerOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.MyAddressActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.SerOrderInfoActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.ServerMenuActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.SubSerOrderFinishActivity;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/12/9.
 */

public abstract class BaseOrderInfoActivity extends LeLaoHuiBaseActivity implements View.OnClickListener  {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatTextView user_name_view;
    private AppCompatTextView user_phone_view;
    private AppCompatTextView user_address_view;
    private RadioButton radioButton;
    private RecyclerView server_type_menu;
    private LinearLayout order_content;
    private BaseOrderInfoControler infoControler = null;
    private String TAG = getClass().getSimpleName();
    private AppCompatTextView order_number;
    private AppCompatTextView order_price_count;
    private AppCompatTextView pay_status;
    private CardView food_content_cv;
    private MyAdapter adapter = null;
    private TextView option_address;
    private TextView option_pay;

    public BaseOrderCate getInfoData() {
        return infoData;
    }

    public void setInfoData(BaseOrderCate infoData) {
        this.infoData = infoData;
    }

    private BaseOrderCate infoData = null;
    public AppCompatTextView getShopping_product_price() {
        return shopping_product_price;
    }

    private AppCompatTextView shopping_product_price;
    private AppCompatButton upload_shopping_car;
    private SysVar var = null;

    public TextView getOption_address() {
        return option_address;
    }

    public AppCompatButton getUpload_shopping_car() {
        return upload_shopping_car;
    }

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
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);
        upload_shopping_car.setOnClickListener(this);
    }

    private double amountPayable;

    public RecyclerView getServer_type_menu() {
        return server_type_menu;
    }


    public AppCompatTextView getOrder_number() {
        return order_number;
    }

    public AppCompatTextView getOrder_price_count() {
        return order_price_count;
    }

    public AppCompatTextView getPay_status() {
        return pay_status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var = SysVar.getInstance();
        initView();
        infoControler = (BaseOrderInfoControler) getControler();
        infoControler.setContext(this);
        infoControler.doUserAddressInfo();
       initPageData();
       initServerTypeMenuData();
    }

    protected abstract void initPageData();
    protected abstract List<OrderInfoBean> getOrderInfoList();
    private void initServerTypeMenuData() {
        adapter = new MyAdapter(this,  getOrderInfoList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        server_type_menu.setLayoutManager(linearLayoutManager);
        server_type_menu.setAdapter(adapter);
    }

    protected void setOrderInfoStatus(String orderCode,int price, int payStatus) {
        order_number.setText("订单号：" + orderCode);
        order_price_count.setText("总价： ￥" + price + "元");
        shopping_product_price.setText("总价： ￥" + price + "元");
        if (payStatus == 0) {
            pay_status.setText("未支付");
        }
    }

    @Override
    public abstract IControler getControler();
    private   String outTradeNo=null;//订单号
    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if(FootterControler.REQ_MSG_ERROR.equals(action)){
                String reqMag=bundle.getString(FootterControler.REQ_MSG);
                Snackbar.make(upload_shopping_car,""+reqMag,Snackbar.LENGTH_LONG).show();
                hideProgress();
            }else
            if (ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE.equals(action)) {
                UserAddressData data = bundle.getParcelable("userAddress");
                String userAddress = data.getAddress();
                String userName = data.getUserName();
                String userPhone = data.getTelephone();
                user_address_view.setText(userAddress);
                user_name_view.setText(userName);
                user_phone_view.setText(userPhone);
            }else if(saveMobileOrderInfo(action)){
                serOrderPayment(bundle);
            }else if(upLoadFinshOrder(action)){
                    gotoSuccessPage(outTradeNo);
            }
        }
    }

    protected void serOrderPayment(Bundle bundle) {
        ServerOrderPayment orderPayment=bundle.getParcelable("orderPayment");
        if (orderPayment!=null){
            outTradeNo=orderPayment.getOrderCode();
            int payType=orderPayment.getPayStyle();
            double payAmt=orderPayment.getAmountPayable();
            if (payType==1) {
                infoControler.doServerOrderPayment(outTradeNo,String.valueOf(payAmt),String.valueOf(payType));
            }
        }
    }

    protected boolean saveMobileOrderInfo(String action) {
        return ServiceNetContant.ServiceResponseAction.SAVE_MOBILE_ORDER_INFO.equals(action);
    }

    protected void gotoSuccessPage(String outTradeNo) {
        if (!TextUtils.isEmpty(outTradeNo)&&!"".equals(outTradeNo)){
            Intent intent=new Intent(this,SubSerOrderFinishActivity.class);
            intent.putExtra("outTradeNo",outTradeNo);
            startActivityForResult(intent, ServerMenuActivity.FINISH_ACTION);
        }

    }

    protected abstract  boolean upLoadFinshOrder(String action);

    @Override
    protected int getLayoutID() {
        return R.layout.cofirm_order_content;
    }

    @Override
    public void usable() {

    }

    public AppCompatTextView getUser_name_view() {
        return user_name_view;
    }

    public AppCompatTextView getUser_phone_view() {
        return user_phone_view;
    }

    public AppCompatTextView getUser_address_view() {
        return user_address_view;
    }
    protected String getUserName(){
        if (user_name_view == null) {
            return null;
        }
    return  user_name_view.getText().toString();
    }
    protected  String getUserPhone(){
        if (user_phone_view == null) {
            return null;
        }
        return user_phone_view.getText().toString();
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
                infoControler.uploadShoppingData(getBaseOrderCate());
                break;
            case R.id.option_address:
                Intent intent =new Intent(this,MyAddressActivity.class);
                intent.putExtra(Protocol_KEY.IS_FROM_CREAT_ORDER_ADDRESS,true);
                if (var.getUserId()!=null){
                    intent.putExtra(Protocol_KEY.CUSTOMER_ID,var.getUserId());
                    intent.putExtra(Protocol_KEY.CUSTOMER_NAME,var.getUserName());
                    intent.putExtra(Protocol_KEY.IS_FROM_CREAT_ORDER_ADDRESS, true);
                    startActivityForResult(intent, GET_ADDRESS);
                }
                break;
        }
    }
    protected abstract BaseOrderCate getBaseOrderCate();
//    protected BaseOrderCate getBaseOrderCate() {
//        SerOrderInfoData.SerOrderInfoBean serOrderInfo = infoData.getSerOrderInfo();
//        String userAddressStr = user_address_view.getText().toString();
//        String userNameStr = user_name_view.getText().toString();
//        String userPhoneStr = user_phone_view.getText().toString();
//        String address = userNameStr + "(" + userPhoneStr + "):" + userAddressStr;
//        serOrderInfo.setContactAddress(address);
//        serOrderInfo.setCustomerName(userNameStr);
//        serOrderInfo.setCustomerPhone(userPhoneStr);
//        serOrderInfo.setCustomerId(Long.valueOf(var.getUserId()));
//        if ("3".equals(var.getOrgId())) {
//            serOrderInfo.setOrgId(Integer.valueOf(var.getOrgId()));
//            serOrderInfo.setOrgTypeId(Integer.valueOf(var.getOrgType()));
//            serOrderInfo.setOrgName(var.getOrgName());
//        } else {
//            serOrderInfo.setSupplierId(Long.valueOf(var.getOrgId()));
//            serOrderInfo.setSupplierTypeId(Integer.valueOf(var.getOrgType()));
//            serOrderInfo.setSupplierName(var.getOrgName());
//            serOrderInfo.setOrgId(Integer.valueOf(var.getCenterId()));
//            serOrderInfo.setOrgTypeId(Integer.valueOf(3));
//            serOrderInfo.setOrgName(var.getCenterName());
//        }
//        serOrderInfo.setOrderPersionId(Long.parseLong(var.getUserId()));
//        serOrderInfo.setOrderPersionName(var.getUserName());
//        serOrderInfo.setPayStyle(1);
//        infoData.setSerOrderInfo(serOrderInfo);
//        return  infoData;
//    }
    protected String getAddressType(){
        String userAddressStr = user_address_view.getText().toString();
        String userNameStr = user_name_view.getText().toString();
        String userPhoneStr = user_phone_view.getText().toString();
        String address = userNameStr + "(" + userPhoneStr + "):" + userAddressStr;
        return address;
    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
    public  class OrderInfoBean{
        private String cateName;

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        private String orderCode;
        private String price;
        private int num;

        public BaseOrderCate getOrderCate() {
            return orderCate;
        }

        public void setOrderCate(BaseOrderCate orderCate) {
            this.orderCate = orderCate;
        }

        private BaseOrderCate orderCate;

    }
    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<OrderInfoBean> serOrderInfoDetailBeanList;
        Context context;

        public MyAdapter(Context context, List<OrderInfoBean> serOrderInfoDetailBeanList) {
            this.context = context;
            this.serOrderInfoDetailBeanList = serOrderInfoDetailBeanList;
        }
        public MyAdapter(Context context){

        }
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.confirm_order_item_content, viewGroup, false);
           MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int position) {
            viewHolder.product_name.setText("商品名：" + serOrderInfoDetailBeanList.get(position).getCateName());
            viewHolder.order_num_txt.setText("订单号：" + serOrderInfoDetailBeanList.get(position).getOrderCode());
            viewHolder.product_price.setText("价格：￥" + serOrderInfoDetailBeanList.get(position).getPrice() + "元");
            viewHolder.order_amount_txt.setText("共" + serOrderInfoDetailBeanList.get(position).getNum() + "件");
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
