package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.SerOrderInfoControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoActivity extends LeLaoHuiBaseActivity {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatTextView user_name_view;
    private AppCompatTextView user_phone_view;
    private AppCompatTextView user_address_view;
    private RadioButton radioButton;
    private RecyclerView server_type_menu;
    private LinearLayout order_content;
    private ImageView show_pp;
    private AppCompatTextView shopping_num_txt;
    private FrameLayout shopping_num;
    private AppCompatTextView shopping_product_price;
    private AppCompatButton upload_shopping_car;
    SerOrderInfoControler infoControler=null;
    public void initView(){
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        left_btn=(AppCompatImageButton)findViewById(R.id.left_btn);
        title_tv=(AppCompatTextView)findViewById(R.id.title_tv);
        reight_tv=(AppCompatTextView)findViewById(R.id.reight_tv);
        user_name_view=(AppCompatTextView)findViewById(R.id.user_name_view);
        user_phone_view=(AppCompatTextView)findViewById(R.id.user_phone_view);
        user_address_view=(AppCompatTextView)findViewById(R.id.user_address_view);
        shopping_num_txt=(AppCompatTextView)findViewById(R.id.shopping_num_txt);
        upload_shopping_car=(AppCompatButton)findViewById(R.id.upload_shopping_car);
        shopping_num=(FrameLayout)findViewById(R.id.shopping_num);
        show_pp=(ImageView)findViewById(R.id.show_pp);
        order_content=(LinearLayout)findViewById(R.id.order_content);
        server_type_menu=(RecyclerView)findViewById(R.id.server_type_menu);
        shopping_product_price=(AppCompatTextView)findViewById(R.id.shopping_product_price);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoControler=(SerOrderInfoControler)getControler();
        initView();
        infoControler.doUserAddressInfo();

    }
    @Override
    public IControler getControler() {
        return null;
    }

    @Override
    public void result(Bundle bundle) {

    }

    public SerOrderInfoActivity() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.cofirm_order_content;
    }

    @Override
    public void usable() {

    }


}
