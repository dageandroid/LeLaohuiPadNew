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
import dq.lelaohui.com.lelaohuipad.bean.MyOldManInfo;
import dq.lelaohui.com.lelaohuipad.controler.MainControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.AddAddressActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.CamearQueryActivity;
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
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

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
                       if (tag){
                           intent=new Intent(LeLaohuiMainActivity.this,CamearQueryActivity.class);
                           intent.putExtra("deviceName",deviceName);
                           Log.i(TAG,"deviceName=="+deviceName);
                           intent.putExtra("deviceCode",deviceCode);
                           Log.i(TAG,"deviceCode=="+deviceCode);
                       }else{
                           Toast.makeText(getApplicationContext(),"抱歉，你暂时不能享受该服务,请购买设备!",Toast.LENGTH_LONG).show();
                       }
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
        mainControler.queryUserOldMainInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NativeCaller.Free();
        Intent intent = new Intent();
        intent.setClass(this, BridgeService.class);
        stopService(intent);
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
        getMenuInflater().inflate(R.menu.le_laohui_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
    public static  List<MyOldManInfo> myOldManInfosCaChe=new ArrayList<>();
    private String deviceCode=null;
    private String deviceName=null;
    private static  final String TAG="LeLaohuiMainActivity";
    private boolean tag=false;
    @Override
    public void result(Bundle bundle) {
        if (bundle==null){
            return ;
        }
            String action=bundle.getString("action");
            if (ServiceNetContant.ServiceResponseAction.GET_DEVICE_STATUS_INFOS_RESONSE.equals(action)){
                data= bundle.getParcelableArrayList("userDevice");
                if (data!=null&&data.size()>0){
                   for (int i=0;i<data.size();i++){
                       if ("2".equals(data.get(i).getStatus())){
                           deviceCode= data.get(i).getDeviceCode();
                           deviceName=data.get(i).getDeviceName();
                       }
                   }
                   if (TextUtils.isEmpty(deviceCode)){
                       Toast.makeText(getApplicationContext(),"抱歉，您的设备目前处于欠或过期状态！",Toast.LENGTH_LONG).show();
                       tag=false;
                       return;
                   }else{
                       //获取摄像头初始化信息
                       tag=true;
//                     SystemValue.deviceId=deviceCode;
                       SystemValue.deviceId="VSTB067542HSYXP";
                   }
            }
        }else if(ServiceNetContant.ServiceResponseAction.GET_MY_OLDMAN_INFO_RESONSE.equals(action)){
                myOldManInfosCaChe=bundle.getParcelableArrayList("userOldManInfos");
                if (myOldManInfosCaChe!=null&&myOldManInfosCaChe.size()==0){
                    MyOldManInfo myOldManInfo=new MyOldManInfo();
                    myOldManInfo.setUserId(var.getUserId());
                    myOldManInfo.setUserName(var.getUserName());
                    myOldManInfosCaChe.add(myOldManInfo);
                }
            }
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
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
//              Intent  intent=new Intent(getApplicationContext(),FoodActivity.class);
                Intent  intent=new Intent(getApplicationContext(),ServerSubscribeActivity.class);
                startActivity(intent);
                break;

            case R.id.fab:

                break;
        }
    }
}
