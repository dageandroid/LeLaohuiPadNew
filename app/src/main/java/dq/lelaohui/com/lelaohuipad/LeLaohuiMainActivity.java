package dq.lelaohui.com.lelaohuipad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.llh.ipcarmear.BridgeService;
import com.llh.ipcarmear.PlayActivity;
import com.llh.ipcarmear.StartActivity;
import com.llh.ipcarmear.util.ContentCommon;
import com.llh.ipcarmear.util.SystemValue;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.MyDeviceInfo;
import dq.lelaohui.com.lelaohuipad.controler.MainControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.AddAddressActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.FoodActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.ServerActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.ServerSubscribeActivity;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.StringSubUtils;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.util.Protocol_KEY;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import vstc2.nativecaller.NativeCaller;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class LeLaohuiMainActivity extends LeLaoHuiBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,BridgeService.AddCameraInterface,BridgeService.IpcamClientInterface,BridgeService.CallBackMessageInterface {

    private Toolbar toolbar;
    private Button button;
    private RelativeLayout content_le_laohui_main;
    private FloatingActionButton fab;
    private RecyclerView main_view;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    private MyAdapter myAdapter=null;
    private MainControler mainControler=null;
    private SysVar var=null;
    private static final String DEVICE_NAME="admin";//设备姓名
    private static final String DEVICE_PASS="888888";//设备密码

    @Override
    public IControler getControler() {
        return MainControler.getControler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainControler=(MainControler)getControler();
        var= SysVar.getInstance();
        initView();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        myAdapter=new MyAdapter(getApplicationContext(), getData());
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        main_view.setLayoutManager(layoutManager);
        main_view.setAdapter(myAdapter);
        myAdapter.setmOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            Intent intent=null;
               switch (position){
                   case 0:
                       intent=new Intent(LeLaohuiMainActivity.this,ServerActivity.class);
                       break;
                   case 1:
                       intent=new Intent(LeLaohuiMainActivity.this,FoodActivity.class);
                       break;
                   case 3:
                       intent=new Intent(LeLaohuiMainActivity.this,ServerSubscribeActivity.class);
                       break;
                   case 4:
//                       ProFoodInfoDaoOperator.getInstance().setmContext(getApplicationContext());
//                       ProFoodInfoDaoOperator.getInstance().delete("");
                       if (tag==1){
                        intent=new Intent(LeLaohuiMainActivity.this,PlayActivity.class);
                       }else{
                           Toast.makeText(getApplicationContext(),"抱歉，您的设备不在线！",Toast.LENGTH_LONG).show();
                       }
//                   intent=new Intent(LeLaohuiMainActivity.this,StartActivity.class);
                       break;
               }
               if (intent!=null){
                   startActivity(intent);
               }else{
                   Toast.makeText(LeLaohuiMainActivity.this, "抱歉你没有打开权限！", Toast.LENGTH_SHORT).show();
               }
            
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainControler.queryUserDeviceInfo(var.getUserId(),var.getCenterId());
    }

    public   List<String> getData(){
        List<String> listData=new ArrayList<String>();
        listData.add("服务");
        listData.add("餐品");
        listData.add("商品");
        listData.add("服务预约");
        listData.add("摄像头");
        return listData;
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_le_laohui_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void serclick(View view) {
        getControler().gotoPage(ServerActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.le_laohui_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private   List<MyDeviceInfo> data=new ArrayList<>();
    private String deviceCode=null;
    private String deviceId=null;
    private int option= ContentCommon.INVALID_OPTION;
    private int CameraType=ContentCommon.CAMERA_TYPE_MJPEG;
    private static  final String TAG="LeLaohuiMainActivity";
    @Override
    public void result(Bundle bundle) {
        if (bundle==null){
            return ;
        }
        if(bundle!=null){
            String action=bundle.getString("action");

            if (ServiceNetContant.ServiceResponseAction.GET_DEVICE_STATUS_INFOS_RESONSE.equals(action)){
                data= bundle.getParcelableArrayList("userDevice");
                if (data!=null&&data.size()>0){
                   for (int i=0;i<data.size();i++){
                       if ("2".equals(data.get(i).getStatus())){
                           deviceCode= data.get(i).getDeviceCode();
                       }
                   }
                   if (TextUtils.isEmpty(deviceCode)){
                       Toast.makeText(getApplicationContext(),"抱歉，您的设备目前处于欠或过期状态！",Toast.LENGTH_LONG).show();
                       return;
                   }else{
                       //获取摄像头初始化信息
                       Intent intent=new Intent();
                       if (option==ContentCommon.INVALID_OPTION){
                           option=ContentCommon.ADD_CAMERA;
                       }
                       intent.putExtra(ContentCommon.CAMERA_OPTION,option);
                       intent.putExtra(ContentCommon.STR_CAMERA_ID,deviceCode);
                       intent.putExtra(ContentCommon.STR_CAMERA_USER,DEVICE_NAME);
                       intent.putExtra(ContentCommon.STR_CAMERA_PWD,DEVICE_PASS);
                       intent.putExtra(ContentCommon.STR_CAMERA_TYPE,CameraType);

//                     SystemValue.deviceId=deviceCode;
                       SystemValue.deviceId="VSTB067542HSYXP";
                       SystemValue.deviceName=DEVICE_NAME;
                       SystemValue.devicePass=DEVICE_PASS;
                       //与摄像头建立连接
                       BridgeService.setIpcamClientInterface(this);
                       NativeCaller.Init();
                       new Thread(new StartPPPPThread()).start();
                   }
               }
            }
        }

    }
    //启动摄像头线程
    class StartPPPPThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
                startCameraPPPP();
            } catch (Exception e) {
            }
        }
    }
    //与摄像头建立连接
    private void startCameraPPPP() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        //判断摄像头类别
        if(SystemValue.deviceId.toLowerCase().startsWith("vsta"))
        {
            NativeCaller.StartPPPPExt(SystemValue.deviceId, SystemValue.deviceName,
                    SystemValue.devicePass,1,"","EFGFFBBOKAIEGHJAEDHJFEEOHMNGDCNJCDFKAKHLEBJHKEKMCAFCDLLLHAOCJPPMBHMNOMCJKGJEBGGHJHIOMFBDNPKNFEGCEGCBGCALMFOHBCGMFK");
        }else {
            NativeCaller.StartPPPP(SystemValue.deviceId, SystemValue.deviceName,
                    SystemValue.devicePass,1,"");
        }

    }
    //关闭摄像头数据流
    private void stopCameraPPPP()
    {
        NativeCaller.StopPPPP(SystemValue.deviceId);
    }


    @Override
    public void usable() {

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        button = (Button) findViewById(R.id.button);
        content_le_laohui_main = (RelativeLayout) findViewById(R.id.content_le_laohui_main);
//      fab = (FloatingActionButton) findViewById(R.id.fab);
        main_view = (RecyclerView) findViewById(R.id.main_view);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        button.setOnClickListener(this);
//      fab.setOnClickListener(this);
        BridgeService.setAddCameraInterface(this);
        BridgeService.setCallBackMessage(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private static final String STR_MSG_PARAM="msgparam";//摄像头返回状态
    private static final String STR_DID="did";
    private int tag=0;
    //判断摄像头状态消息分发
    private Handler PPPPMsgHandler = new Handler() {
        public void handleMessage(Message msg) {

            Bundle bd = msg.getData();
            int msgParam = bd.getInt(STR_MSG_PARAM);
            int msgType = msg.what;
            Log.i(TAG, "===="+msgType+"--msgParam:"+msgParam);
            String did = bd.getString(STR_DID);
            switch (msgType) {
                case ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS:
                    int resid;
                    switch (msgParam) {
                        case ContentCommon.PPPP_STATUS_CONNECTING://0
                            resid = R.string.pppp_status_connecting;
//                          progressBar.setVisibility(View.VISIBLE);
                            tag = 2;
                            break;
                        case ContentCommon.PPPP_STATUS_CONNECT_FAILED://3
                            resid = R.string.pppp_status_connect_failed;
//                          progressBar.setVisibility(View.GONE);
                            tag = 0;
                            break;
                        case ContentCommon.PPPP_STATUS_DISCONNECT://4
                            resid = R.string.pppp_status_disconnect;
//                          progressBar.setVisibility(View.GONE);
                            tag = 0;
                            break;
                        case ContentCommon.PPPP_STATUS_INITIALING://1
                            resid = R.string.pppp_status_initialing;
//                          progressBar.setVisibility(View.VISIBLE);
                            tag = 2;
                            break;
                        case ContentCommon.PPPP_STATUS_INVALID_ID://5
                            resid = R.string.pppp_status_invalid_id;
//                          progressBar.setVisibility(View.GONE);
                            tag = 0;
                            break;
                        case ContentCommon.PPPP_STATUS_ON_LINE://2 在线状态
                            resid = R.string.pppp_status_online;
//                          progressBar.setVisibility(View.GONE);
                            //摄像机在线之后读取摄像机类型
                            String cmd="get_status.cgi?loginuse=admin&loginpas=" + SystemValue.devicePass
                                    + "&user=admin&pwd=" + SystemValue.devicePass;
                            NativeCaller.TransferMessage(did, cmd, 1);
                            Toast.makeText(getApplicationContext(),"设备在线",Toast.LENGTH_LONG).show();
                            Log.i(TAG,"PPPP_STATUS_ON_LINE ");
                            tag = 1;
                            break;
                        case ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE://6
                            resid = R.string.device_not_on_line;
                            tag = 0;
                            break;
                        case ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT://7
                            resid = R.string.pppp_status_connect_timeout;
                            tag = 0;
                            break;
                        case ContentCommon.PPPP_STATUS_CONNECT_ERRER://8
                            resid =R.string.pppp_status_pwd_error;
//                          progressBar.setVisibility(View.GONE);
                            tag = 0;
                            break;
                        default:
                            resid = R.string.pppp_status_unknown;
                    }
//                    Toast.makeText(getApplicationContext(),""+resid,Toast.LENGTH_LONG).show();
                    if (msgParam == ContentCommon.PPPP_STATUS_ON_LINE) {
                        NativeCaller.PPPPGetSystemParams(did,ContentCommon.MSG_TYPE_GET_PARAMS);
                    }
                    if (msgParam == ContentCommon.PPPP_STATUS_INVALID_ID
                            || msgParam == ContentCommon.PPPP_STATUS_CONNECT_FAILED
                            || msgParam == ContentCommon.PPPP_STATUS_DEVICE_NOT_ON_LINE
                            || msgParam == ContentCommon.PPPP_STATUS_CONNECT_TIMEOUT
                            || msgParam == ContentCommon.PPPP_STATUS_CONNECT_ERRER) {
                        NativeCaller.StopPPPP(did);
                    }
                    break;
                case ContentCommon.PPPP_MSG_TYPE_PPPP_MODE:
                    break;

            }

        }
    };
    @Override
    public void callBackSearchResultData(int cameraType, String strMac, String strName, String strDeviceID, String strIpAddr, int port) {

    }
    //摄像头状态返回
    @Override
    public void BSMsgNotifyData(String did, int type, int param) {
        Log.d("ip", "type:" + type + " param:" + param);
        Bundle bd = new Bundle();
        Message msg = PPPPMsgHandler.obtainMessage();
        msg.what = type;
        bd.putInt(STR_MSG_PARAM, param);
        bd.putString(STR_DID, did);
        msg.setData(bd);
        PPPPMsgHandler.sendMessage(msg);
        Log.i(TAG, "BSMsgNotifyData: ");
        if (type == ContentCommon.PPPP_MSG_TYPE_PPPP_STATUS) {
//            intentbrod.putExtra("ifdrop", param);
//            sendBroadcast(intentbrod);
        }

    }

    @Override
    public void BSSnapshotNotify(String did, byte[] bImage, int len) {

    }

    @Override
    public void callBackUserParams(String did, String user1, String pwd1, String user2, String pwd2, String user3, String pwd3) {

    }

    @Override
    public void CameraStatus(String did, int status) {

    }

    @Override
    public void CallBackGetStatus(String did, String resultPbuf, int cmd) {
        if (cmd == ContentCommon.CGI_IEGET_STATUS) {
            String cameraType = StringSubUtils.spitValue(resultPbuf, "upnp_status=");
            int intType = Integer.parseInt(cameraType);
            int type14 = (int) (intType >> 16) & 1;// 14位 来判断是否报警联动摄像机
            if (intType == 2147483647) {// 特殊值
                type14 = 0;
            }
//            if(type14==1){
//                updateListHandler.sendEmptyMessage(2);
//            }

        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener{
        List<String> data;
        Context context;

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        public interface OnRecyclerViewItemClickListener {
            void onItemClick(View view , int data);
        }

        public MyAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.llh_server_menu_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
           view.setOnClickListener(this);
            return vh;
        }

        public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.type_menu_txt.setText("" +data.get(position).toString());
            viewHolder.itemView.setTag(position);
        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatImageView image_menu;
            public AppCompatTextView type_menu_txt;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.image_menu = (AppCompatImageView) rootView.findViewById(R.id.image_menu);
                this.type_menu_txt = (AppCompatTextView) rootView.findViewById(R.id.type_menu_txt);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(receiver);
        NativeCaller.Free();
        Intent intent = new Intent();
        intent.setClass(this, BridgeService.class);
        stopService(intent);
        tag = 0;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //ServerSubscribeActivity
//              Intent  intent=new Intent(getApplicationContext(),FoodActivity.class);
                Intent  intent=new Intent(getApplicationContext(),ServerSubscribeActivity.class);
                startActivity(intent);
                break;

            case R.id.fab:

                break;
        }
    }
}
