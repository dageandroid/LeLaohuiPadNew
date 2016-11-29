package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

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
 * 我的地址
 */

public class MyAddressActivity extends LeLaoHuiBaseActivity {

    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private ExpandableListView address_list;
    private SysVar var=null;
    private MyAddressConreoler addressConreoler=null;
    private boolean isFromCreatOrder;
    private String TAG = getClass().getSimpleName();
    private String customerId,customerName;
    private MyExpandableListViewAdapter adapter=null;
    private int centerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addressConreoler=(MyAddressConreoler)getControler();
        var= SysVar.getInstance();
        if(getIntent()!=null){
            isFromCreatOrder=getIntent().getBooleanExtra(Protocol_KEY.IS_FROM_CREAT_ORDER_ADDRESS,false);
            customerId=getIntent().getStringExtra(Protocol_KEY.CUSTOMER_ID);
            customerName=getIntent().getStringExtra(Protocol_KEY.CUSTOMER_NAME);
            centerId=var.getCenterId();
        }
        addressConreoler.queryUserAddressInfo(customerId,centerId);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        addressConreoler.queryUserAddressInfo(customerId,centerId);
    }

    @Override
    public IControler getControler() {
        return MyAddressConreoler.getControler();
    }
    List<UserAddressData> userAddressDataList=new ArrayList<>();
    @Override
    public void result(Bundle bundle) {
    if (bundle!=null){
        String action=bundle.getString("action");
        if(action.equals(ServiceNetContant.ServiceResponseAction.QUERY_USER_ADDRESS_RESPONSE)){
            userAddressDataList=bundle.getParcelableArrayList("userAddress");
          String userAddress=  userAddressDataList.get(0).getAddress();
            adapter=new MyExpandableListViewAdapter(this);
            address_list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else if(action.equals( ServiceNetContant.ServiceResponseAction.DELETE_USER_ADDRESS_RESPONSE))
        {
            if(userAddressDataList!=null){
                userAddressDataList.clear();
                addressConreoler.queryUserAddressInfo(customerId,centerId);
            }
        }
    }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_address;
    }

    @Override
    public void usable() {

    }
    private void initView() {
        title_tv=(AppCompatTextView)findViewById(R.id.title_tv);
        left_btn=(AppCompatImageButton)findViewById(R.id.left_btn);
        reight_tv=(AppCompatTextView)findViewById(R.id.reight_tv);
        reight_tv.setVisibility(View.VISIBLE);
        reight_tv.setText("新增地址");
        reight_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyAddressActivity.this,AddAddressActivity.class);
                intent.putExtra(Protocol_KEY.CUSTOMER_ID,customerId);
                intent.putExtra(Protocol_KEY.CUSTOMER_NAME,customerName);//测试
                startActivity(intent);
            }
        });
        address_list=(ExpandableListView)findViewById(R.id.address_list);
        title_tv.setText("我的地址");
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return userAddressDataList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return userAddressDataList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return userAddressDataList.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().from(context).inflate(
                        R.layout.address_father_item, null);
                groupHolder = new GroupHolder();
                groupHolder.name = (AppCompatTextView) convertView
                        .findViewById(R.id.name);
                groupHolder.address = (AppCompatTextView) convertView
                        .findViewById(R.id.address);
                groupHolder.operate = (AppCompatButton) convertView
                        .findViewById(R.id.operate);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
            final UserAddressData addressModel = userAddressDataList.get(groupPosition);
            String name = addressModel.getUserName();
            Log.i(TAG,"userName=="+name);
            if ( addressModel.getAddressType()==0) {
                groupHolder.name.setText(name);
                groupHolder.address.setText(addressModel.getAddress());
            } else {
//                groupHolder.name.setText(addressModel.getDiningName());
//                groupHolder.address.setText(addressModel.getCenterName());
            }

            /**
             * 2016-04-08 09:36
             */
            if (isFromCreatOrder) {
                groupHolder.operate.setVisibility(View.VISIBLE);
                groupHolder.operate.setText("选择");
                groupHolder.operate
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.putExtra("addressModel", addressModel);
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            }
                        });
            } else {
                groupHolder.operate.setVisibility(View.GONE);
            }
            return convertView;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final UserAddressData addressModel = userAddressDataList.get(groupPosition);

            convertView = getLayoutInflater().from(context).inflate(
                    R.layout.address_child_item, null);
            AppCompatTextView name = (AppCompatTextView) convertView.findViewById(R.id.name);
            AppCompatTextView phonenum = (AppCompatTextView) convertView
                    .findViewById(R.id.phone_num);
            AppCompatTextView address = (AppCompatTextView) convertView
                    .findViewById(R.id.address);

            if ( addressModel.getAddressType()==0) {

                AppCompatButton edit = (AppCompatButton) convertView.findViewById(R.id.edit);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        editDefaultAddress(addressModel);
                    }
                });
            } else {
                AppCompatButton edit = (AppCompatButton) convertView.findViewById(R.id.edit);
                edit.setVisibility(View.GONE);
            }

            if (isFromCreatOrder|| addressModel.getAddressType()==1) {
                AppCompatButton delete = (AppCompatButton) convertView.findViewById(R.id.delete);
                delete.setVisibility(View.GONE);
            } else {
                AppCompatButton delete = (AppCompatButton) convertView.findViewById(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (addressModel!=null){
                            addressConreoler.deleteUserAddress(customerId,centerId,addressModel.getAddressId());
                        }
                    }
                });
            }
            if ( addressModel.getAddressType()==0) {
                name.setText("姓名：" + addressModel.getUserName());
                phonenum.setText("电话：" + addressModel.getTelephone());
                address.setText("地址：" + addressModel.getAddress());
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }
public void editDefaultAddress( final  UserAddressData userAddressData){
    if(userAddressData!=null){
        Intent intent=new Intent(MyAddressActivity.this,AddAddressActivity.class);
        intent.putExtra("addressModel",userAddressData);
        intent.putExtra(Protocol_KEY.CUSTOMER_ID,customerId+"");
//        startActivity(intent);
        startActivityForResult(intent, 1100);

    }
}

    class GroupHolder {
        public AppCompatTextView name;
        public AppCompatTextView address;
        public AppCompatButton operate;
    }


private UserAddressData addressModel=null;
    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        // resultCode就是在B页面中返回时传的parama，可以根据需求做相应的处理
        if (arg0 == 1100 && arg1 == RESULT_OK && arg2 != null) {
            Parcelable info = arg2.getParcelableExtra("customerId");
            if (info != null) {
                // ToastTool.showText(getApplicationContext(), "hjehe=="+info);
            }
        } else if (arg0 == 1011 && arg1 == RESULT_OK && arg2 != null) {
            Intent intent = new Intent();
            Parcelable ob = arg2.getParcelableExtra("addressModel");
            if (ob != null) {
                addressModel = (UserAddressData) ob;
            }
            intent.putExtra("addressModel", addressModel);
            setResult(Activity.RESULT_OK, intent);
            super.onActivityResult(arg0, arg1, arg2);
            finish();
        }
    }
}
