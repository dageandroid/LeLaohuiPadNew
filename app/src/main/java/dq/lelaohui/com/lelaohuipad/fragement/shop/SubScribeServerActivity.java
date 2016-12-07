package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.ServerPersonInfoAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerSubsctibeData;
import dq.lelaohui.com.lelaohuipad.bean.ServerPersonData;
import dq.lelaohui.com.lelaohuipad.bean.SubScribeOrderBean;
import dq.lelaohui.com.lelaohuipad.controler.ServerSubscribeControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.SubScribeServerSets;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.ServerSubscribeRequestParam;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;

/**
 * Created by ZTF on 2016/11/29.
 * 提交预约相关信息
 */

public class SubScribeServerActivity extends LeLaoHuiBaseActivity implements View.OnClickListener, SubScribeServerSets.UiOperators {
    private static final String TAG="SubScribeServerActivity";
    private AppCompatTextView title_tv;
    private AppCompatImageButton left_btn;
    private AppCompatTextView reight_tv;
    private ViewStub subscribe_data;
    private AppCompatTextView serverPagerName;
    private AppCompatTextView sub_name;
    private RadioButton system_assign;
    private RadioButton server_assign;
    private RadioGroup option_style;
    private ListView server_person;
    private AppCompatTextView server_time_name;
    private AppCompatTextView day_time;
    private AppCompatTextView day_start_time;
    private AppCompatTextView day_stop_time;
    private LinearLayout option_time;
    private AppCompatTextView remark_name;
    private AppCompatEditText remark_content;
    private AppCompatButton next_data,upload_ser_scribe;
    private SysVar var=null;
    private ServerSubscribeControler serverSubscribeControler=null;
    private String customerId,customerName,customerPhone;
    private FilterSubscribeData filterSubscribeData=null;
    private int execNumDay;
    private long serStockDetailId;
    private int optionAssign;
    private String serverId="",serverName="";
    private List<ServerPersonData> serverPersonDataList=new ArrayList<>();
    private SubScribeServerSets subScribeServerSets;
    public void initView() {
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("提交预约");
        reight_tv = (AppCompatTextView) findViewById(R.id.reight_tv);
        left_btn = (AppCompatImageButton) findViewById(R.id.left_btn);
        subscribe_data = (ViewStub) findViewById(R.id.subscribe_data);
        subscribe_data.inflate();
        serverPagerName = (AppCompatTextView) findViewById(R.id.serverPagerName);
        sub_name = (AppCompatTextView) findViewById(R.id.sub_name);
        system_assign = (RadioButton) findViewById(R.id.system_assign);
        system_assign.setOnClickListener(this);
        server_assign = (RadioButton) findViewById(R.id.server_assign);
        server_assign.setOnClickListener(this);
        option_style = (RadioGroup) findViewById(R.id.option_style);
        option_style.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i==R.id.system_assign){
                    optionAssign=0;
                }else{
                    optionAssign=1;
                    Intent intent=new Intent(SubScribeServerActivity.this,ServerPersonInfoActivity.class);
                     startActivityForResult(intent,GER_SERVER_PERSON);
                }
            }
        });
        server_person = (ListView) findViewById(R.id.server_person);
        server_time_name = (AppCompatTextView) findViewById(R.id.server_time_name);
        day_time = (AppCompatTextView) findViewById(R.id.day_time);
        day_time.setOnClickListener(this);
        day_start_time = (AppCompatTextView) findViewById(R.id.day_start_time);
        day_start_time.setOnClickListener(this);
        day_stop_time = (AppCompatTextView) findViewById(R.id.day_stop_time);
        day_stop_time.setOnClickListener(this);
        remark_content = (AppCompatEditText) findViewById(R.id.remark_content);
        next_data = (AppCompatButton) findViewById(R.id.next_data);
        upload_ser_scribe = (AppCompatButton) findViewById(R.id.upload_ser_scribe);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        var = SysVar.getInstance();
        serverSubscribeControler = (ServerSubscribeControler) getControler();
        initView();
        subScribeServerSets=new SubScribeServerSets(this);
        subScribeServerSets.setUiOperators(this);
        if (getIntent()!=null){
            filterSubscribeData=getIntent().getParcelableExtra("filterSubscribeData");
            customerId=getIntent().getStringExtra("customerId");
            customerName=getIntent().getStringExtra("customerName");
            if (!TextUtils.isEmpty(customerName)&&filterSubscribeData!=null){
                serStockDetailId=filterSubscribeData.getSerStockDetailId();
                Log.i(TAG," filterSubscribeData.getExecNumDay()=="+ filterSubscribeData.getExecNumDay());
                execNumDay=filterSubscribeData.getExecNumDay();
                serverPagerName.setText("客户姓名："+customerName+"");
                sub_name.setText("2016-11-30");
                subScribeServerSets.addData(filterSubscribeData);
            }
        }
        customerPhone="18510397270";
        customerId=var.getUserId();
        customerName=var.getUserName();
    }
    @Override
    public IControler getControler() {
        return ServerSubscribeControler.getControler();
    }
    @Override
    public void result(Bundle bundle) {
        if(bundle!=null){
            String action=bundle.getString("action");
            if(action.equals(ServiceNetContant.ServiceResponseAction.CONFIRM_ORDER_SERVER_APP_RESONSE)){
                String message=bundle.getString("message");
                Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_sub_scribe_server;
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
    @Override
    public void onClick(View view) {
    }
    private List<SerSubsctibeData> serSubsctibeDataList =new ArrayList<SerSubsctibeData>();
    /**
     * 设置预约条件
     * @param subScribeData
     * @param execNum
     */
    @Override
    public void setSubScribeData(SerSubsctibeData subScribeData,int execNum) {
        setSerScribeContent(execNum);
    }
    /**
     * 设置服务预约每次的添加和修改数据
     * @param execNum
     */
    private void setSerScribeContent(int execNum) {
        SerSubsctibeData subScribeData = getSerSubsctibeData(execNum);
        serSubsctibeDataList.add(subScribeData);
    }
    @NonNull
    private SerSubsctibeData getSerSubsctibeData(int execNum) {
        SerSubsctibeData subScribeData=new SerSubsctibeData();
        subScribeData.setId(execNum);
        String dayStartTime=day_start_time.getText().toString();
        String dayStopTime=day_stop_time.getText().toString();
        subScribeData.setStartTime(dayStartTime);
        subScribeData.setEndTime(dayStopTime);
        if("".equals(serverId)||"".equals(serverName)){
            subScribeData.setWaiters("");
        }else{
            subScribeData.setWaiters(serverId+"_"+serverName);
        }
        subScribeData.setIsChange(String.valueOf(optionAssign));
        subScribeData.setAssignType(optionAssign);
        subScribeData.setOrdreNum(String.valueOf(execNum));
        return subScribeData;
    }

    @Override
    public List<SerSubsctibeData> setSubScribeDataList() {
        if (serSubsctibeDataList!=null){
            return serSubsctibeDataList;
        }
        return null;
    }

    /**
     * 修改预约设置参数相关数据
     * @param execNum
     * @return
     */
    @Override
    public SerSubsctibeData updateSubScribeData(int execNum) {
        SerSubsctibeData updataSubScribeData = getSerSubsctibeData(execNum);
        return updataSubScribeData;
    }

    /**
     * 设置提交数据
     * @return
     */
    @Override
    public SubScribeOrderBean setSubScribeOrder(SubScribeOrderBean subScribeOrderBean) {
        String dayTime=  day_time.getText().toString();
        String remarkContent=remark_content.getText().toString();
          subScribeOrderBean=new SubScribeOrderBean();
        subScribeOrderBean.setMobile(customerPhone);
        subScribeOrderBean.setOrderPerson(customerName);
        subScribeOrderBean.setOrderPersonId(Long.parseLong(customerId));
        subScribeOrderBean.setOrderDateStr(dayTime);
        subScribeOrderBean.setRemark(remarkContent);
        List<SerSubsctibeData> serSubsctibeDataList=setSubScribeDataList();
        if (serSubsctibeDataList!=null){
            subScribeOrderBean.setSerSubsctibeDataList(serSubsctibeDataList);
        }
        return subScribeOrderBean;
    }

    /**
     * 提交预约设置数据
     * @param subScribeOrderBeanList
     * @return
     */
    @Override
    public RequestParam getOrderParam(SubScribeOrderBean subScribeOrderBeanList) {
        subScribeOrderBeanList=  setSubScribeOrder(subScribeOrderBeanList);
        ServerSubscribeRequestParam serverRequestParam = new ServerSubscribeRequestParam();
        RequestParam rp =serverRequestParam.doUploadServer(serStockDetailId,subScribeOrderBeanList);
        return rp;
    }

    @Override
    public void setExecNumShowNextView(int execNum) {
//        if (execNum==1){
//            next_data.setVisibility(View.GONE);
//        }
    }
    @Override
    public View nextButton() {
        return next_data;
    }
    @Override
    public View uploadButton() {
        return upload_ser_scribe;
    }
    @Override
    public void setButtonTxt(String txtStr) {
        upload_ser_scribe.setText(txtStr+"");
    }
    @Override
    public void setSerPagerName(String serPagerName) {
        serverPagerName.setText(serPagerName);
    }
    public static final int GER_SERVER_PERSON=999;
    private ServerPersonInfoAdapter adapter=null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GER_SERVER_PERSON&&resultCode==RESULT_OK&&data!=null){
            Parcelable parcelable=data.getParcelableExtra("serverPerson");
            if (parcelable!=null){
                serverPersonDataList=new ArrayList<>();
                ServerPersonData serverPersonData=(ServerPersonData)parcelable;
                if (serverPersonData!=null){
                  serverId=  serverPersonData.getUserId();
                  serverName=  serverPersonData.getUserName();
                    serverPersonDataList.add(serverPersonData);
                    if (server_person.getAdapter()==null){
                        adapter=new ServerPersonInfoAdapter(getApplicationContext(),serverPersonDataList);
                        server_person.setAdapter(adapter);
                        server_person.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                server_person.setSelected(true);
                                server_person.setSelection(0);
                                server_person.setItemChecked(0,true);
                                server_person.performItemClick(server_person.getChildAt(0),0,0);
                            }
                        },500);
                    }else{
                        adapter.setData(serverPersonDataList);
                        server_person.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
