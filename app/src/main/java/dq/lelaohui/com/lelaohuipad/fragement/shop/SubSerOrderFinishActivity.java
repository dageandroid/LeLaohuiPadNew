package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoFinish;
import dq.lelaohui.com.lelaohuipad.controler.SerOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/15.
 */

public class SubSerOrderFinishActivity extends LeLaoHuiBaseActivity {
    private AppCompatTextView pay_order_status;
    private AppCompatTextView order_num;
    private AppCompatTextView order_clie_name;
    private AppCompatTextView pay_order_name;
    private AppCompatTextView pay_order_money;
    private AppCompatTextView order_address;
    private AppCompatTextView order_time;
    private AppCompatButton pay_money;
    private AppCompatButton back_order;
    private AppCompatButton query_order;
    private SerOrderInfoControler orderInfoControler=null;
    private String TAG = getClass().getSimpleName();
    private  String outTradeNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderInfoControler = (SerOrderInfoControler) getControler();
        initView();
        if (getIntent()!=null){
            outTradeNo=getIntent().getStringExtra("outTradeNo");
            Log.i(TAG,"outTradeNo=="+outTradeNo);
            orderInfoControler.getMyServerOrderInfo(outTradeNo);
        }
    }
    private void initView() {
        pay_order_status=(AppCompatTextView)findViewById(R.id.pay_order_status);
        order_num=(AppCompatTextView)findViewById(R.id.order_num);
        order_clie_name=(AppCompatTextView)findViewById(R.id.order_clie_name);
        pay_order_name=(AppCompatTextView)findViewById(R.id.pay_order_name);
        pay_order_money=(AppCompatTextView)findViewById(R.id.pay_order_money);
        order_address=(AppCompatTextView)findViewById(R.id.order_address);
        order_time=(AppCompatTextView)findViewById(R.id.order_time);
        pay_money=(AppCompatButton)findViewById(R.id.pay_money);
        back_order=(AppCompatButton)findViewById(R.id.back_order);
        query_order=(AppCompatButton)findViewById(R.id.query_order);
    }
    @Override
    public IControler getControler() {
        return SerOrderInfoControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
        if(bundle!=null){
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.GET_SERVER_ORDER_SUCC_INFO_RESPONSE)) {
               if( bundle.getParcelable("orderInfoFinish")!=null){
                   SerOrderInfoFinish data = bundle.getParcelable("orderInfoFinish");
                   String orderCodeStr= data.getOrderCode();
                  String customerNameStr= data.getCustomerName();
                  String contactAddress= data.getContactAddress();
                 int amountPayable=  data.getAmountPayable();
                   int payStatus=data.getPayStatus();
                   Log.i(TAG,"orderCodeStr=="+orderCodeStr);
                   if (payStatus==1){
                       pay_order_status.setText("支付成功！");
                   }else {
                       pay_order_status.setText("支付失败，请重新支付！");
                   }
                   order_num.setText("订单号："+outTradeNo);
                   order_clie_name.setText("付款人："+customerNameStr);
                   pay_order_name.setText("订单地址："+contactAddress);
                   pay_order_money.setText("支付金额："+amountPayable);
//                   order_address.setText("订单地址："+contactAddress);
//                   order_time.setText("");
//                   pay_money.setText("");
               }

            }
        }

    }
    int layout= R.layout.activity_sub_finish_order_info;
    @Override
    protected int getLayoutID() {
        return layout;
    }

    @Override
    public void usable() {

    }



}
