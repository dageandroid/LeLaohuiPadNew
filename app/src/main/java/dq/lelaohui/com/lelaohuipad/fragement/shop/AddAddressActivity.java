package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.UserAddressData;
import dq.lelaohui.com.lelaohuipad.controler.MyAddressConreoler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/17.
 */

public class AddAddressActivity extends LeLaoHuiBaseActivity {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private AppCompatEditText name;
    private AppCompatEditText phone;
    private AppCompatEditText address;
    private AppCompatButton commit;
    private MyAddressConreoler addressConreoler ;
    private SysVar var=null;
    private UserAddressData addressModel=null;
    private String customerId;
    private int centerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addressConreoler=(MyAddressConreoler)getControler();
        var= SysVar.getInstance();
        initView();
        centerId=var.getCenterId();
    }
    public void commitAddress(){
        if (addressModel == null) {
            addOldManAddress(customerId, 0,name.getText().toString(),address.getText().toString(),phone.getText().toString());
        } else {
            addOldManAddress(customerId, addressModel.getAddressId(),name.getText().toString(), address.getText().toString(),phone.getText().toString());
        }
    }
    public void addOldManAddress(String customerId, int addressId,String usreName,
                                 String address,String phone){
        addressConreoler.addUserAddress(customerId,centerId,addressId,usreName,address,phone);
    }
    @Override
    public IControler getControler() {
        return MyAddressConreoler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
    if(bundle!=null){
        String action=bundle.getString("action");
        if (action.equals(ServiceNetContant.ServiceResponseAction.ADD_USER_ADDRESS_RESPONSE)){
            Intent intent=new Intent(AddAddressActivity.this,AddAddressActivity.class);
            intent.putExtra(Protocol_KEY.CUSTOMER_ID,customerId);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    }
    public boolean checkAddress() {
        if (name.getText() == null || name.getText().equals("")) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (address.getText() == null || address.getText().equals("")) {
            Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_add_address;
    }

    @Override
    public void usable() {

    }

    public  void initView(){
        name=(AppCompatEditText)findViewById(R.id.name);
        phone=(AppCompatEditText)findViewById(R.id.phone);
        address=(AppCompatEditText)findViewById(R.id.address);
        commit=(AppCompatButton) findViewById(R.id.commit);
        reight_tv=(AppCompatTextView)findViewById(R.id.reight_tv);
        left_btn=(AppCompatImageButton)findViewById(R.id.left_btn);
        title_tv=(AppCompatTextView)findViewById(R.id.title_tv);
        if(getIntent()!=null) {
            customerId=getIntent().getStringExtra("customerId");
        }else{
            customerId=var.getUserId();
        }
        if(getIntent().getParcelableExtra("addressModel")!=null){
            addressModel=getIntent().getParcelableExtra("addressModel");
            title_tv.setText("编辑地址");
            if(addressModel!=null){
                name.setText(""+addressModel.getUserName());
                phone.setText(""+addressModel.getTelephone());
                address.setText(""+addressModel.getAddress());
            }
        }else{
            title_tv.setText("新增地址");
        }
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAddress()) {
                    commitAddress();
                }
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
