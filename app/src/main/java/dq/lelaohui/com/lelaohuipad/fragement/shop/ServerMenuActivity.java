package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;
import dq.lelaohui.com.lelaohuipad.controler.ServerMenuControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.adapter.BaseShopInfoRecyleViewAdapter;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;
import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.FootDataListener;
import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.ServerMenuDataManager;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateMenuServiceDao;

/**
 * Created by ZTF on 2016/10/30.
 */

public class ServerMenuActivity extends LeLaoHuiBaseActivity implements BaseShopCart.CardDataChange, BaseShopCart.UiOperator, View.OnClickListener,FootDataListener {
    private ListView server_menu_content;
    private ListView server_content_rv;
    private SwipeRefreshLayout get_data_refresh;
    private AppCompatTextView shopping_num_txt;
    private FrameLayout shopping_num;
    private AppCompatTextView shopping_product_price, title_tv;
    private AppCompatButton upload_shopping_car;
    private ServerMenuControler serverControler;
    private String TAG = getClass().getSimpleName();
    private ProCateService proCateService = null;
    private MyServerCateRecyleViewAdapter adapter = null;
    private MyServerContentRecyleViewAdapter serverContentAdapter = null;
    private GoogleApiClient client;
    private View llh_shopping_bottom;
    private long cateIdL=0;
    private int isPackInt;
    private int postion;
    private AppCompatImageButton left_btn;
    ImageView show_pp;
    BaseShopCart shopCartBase = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverControler = (ServerMenuControler) getControler();
        ServerMenuDataManager dataManager=ServerMenuDataManager.getInstance(serverControler,this);
        dataManager.setDataListener(this);
        serverControler.setDataManager(dataManager);
        setNetResponIntercept(dataManager);
        shopCartBase = new BaseShopCart(this);
        shopCartBase.setCardDataChange(this);
        shopCartBase.setUiOperator(this);
        initView();

//        shopCartBase.init();
        if (getIntent() != null) {
            proCateService = getIntent().getParcelableExtra("proCateServer");
            cateIdL = proCateService.getCateId();
            isPackInt = proCateService.getIsPack();
//            serverControler.doQueryServerCategory(cateIdL, isPackInt, 1);
        }
        server_menu_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor= (Cursor) adapterView.getItemAtPosition(i);
                postion=i;
                ProCateMenuService proCateMenu=   adapter.readEntity(cursor,0);
                String cateIdStr = null;
                if (proCateMenu != null) {
                    cateIdL = proCateMenu.getCateId();
                    if (serverContentAdapter != null) {
                        Cursor tempCursor= serverContentAdapter. swapCursor(null);
                        if(tempCursor!=null){
                            tempCursor.close();
                        }
                    }

                    cateIdStr = String.valueOf(proCateMenu.getCateId());
                    isPackInt = proCateMenu.getIsPack();
                    try {
                        serverControler.getQueryServerCateqory(false,cateIdL, isPackInt, ServerMenuControler.DETAIL_CATEID);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


        //下拉刷新获取左侧服务类别相关数据
        get_data_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    serverControler.getQueryServerCateqory(true,cateIdL, isPackInt, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        initData();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent: ...........");
        setIntent(intent);
        super.onNewIntent(intent);
//        initData();
    }


    private void initData() {
        if (getIntent() != null) {
            proCateService = getIntent().getParcelableExtra("proCateServer");
            cateIdL = proCateService.getCateId();
            isPackInt = proCateService.getIsPack();
//            serverControler.doQueryServerCategory(cateIdL, isPackInt, 1);
        }
        try {
            serverControler.getQueryServerCateqory(false,cateIdL, isPackInt, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dataChanager(final String id) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(String.valueOf(ServerMenuDataManager.LEFT_MENUM).equals(id)||id==null){
                    Cursor cursor;
                    if (cateIdL == -1) {
                        cursor = serverControler.getQueryCateCursor();
                    } else {
                        cursor = serverControler.getQueryTwoCursor(cateIdL);
                    }
                    if(cursor!=null&&adapter!=null){
                        adapter.changeCursor(cursor);
                        adapter.notifyDataSetChanged();

                    }
                }else if(String.valueOf(ServerMenuDataManager.SERVER_DETAILE_PAGE).equals(id)){
                    Cursor  cursor = serverControler.getSerInitProCursor((int)cateIdL);
                    int count=cursor.getCount();
                    if(count==0){
                        Log.i(TAG, "run: "+postion);
                        new Handler(getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                               Cursor cursor = serverControler.getSerInitProCursor((int)cateIdL);
                               Cursor olderCursor= serverContentAdapter.swapCursor(cursor);
                                if(olderCursor!=null){
                                    olderCursor.close();
                                }
                            }
                        },5000);
                        return;

                    }
                    Cursor cursorTemp=serverContentAdapter.swapCursor(cursor);
                    if(cursorTemp!=null){
                        cursorTemp.close();
                    }
                    serverContentAdapter.notifyDataSetChanged();
                    hideProgress();

                }


            }
        });
    }

    private void initView() {
        server_menu_content = (ListView) findViewById(R.id.server_menu_content);
        server_content_rv = (ListView) findViewById(R.id.server_content_rv);
        get_data_refresh = (SwipeRefreshLayout) findViewById(R.id.get_data_refresh);
        shopping_num_txt = (AppCompatTextView) findViewById(R.id.shopping_num_txt);
        shopping_num = (FrameLayout) findViewById(R.id.shopping_num);
        shopping_product_price = (AppCompatTextView) findViewById(R.id.shopping_product_price);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);
        left_btn = (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        show_pp = (ImageView) findViewById(R.id.show_pp);
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("服务");
        llh_shopping_bottom = findViewById(R.id.llh_shopping_bottom);
        Cursor cursor;
        if (cateIdL == -1) {
            cursor = serverControler.getQueryCateCursor();
        } else {
            cursor = serverControler.getQueryTwoCursor(cateIdL);
        }
        adapter = new MyServerCateRecyleViewAdapter(this, null);
        server_menu_content.setAdapter(adapter);
        serverContentAdapter = new MyServerContentRecyleViewAdapter(ServerMenuActivity.this, null);
        serverContentAdapter.setShopCartBase(shopCartBase);
        server_content_rv.setAdapter(serverContentAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_shopping_car:

                break;
        }
    }

    //当controle 收到数据，调用getIControlerCallBack().result(bundle);
    @Override
    public void result(Bundle bundle) {
        if (bundle != null) {
            String action = bundle.getString("action");
            if (action.equals(ServiceNetContant.ServiceResponseAction.CAL_ORDER_MONEY)) {
                SerOrderInfoData infoData = bundle.getParcelable("serOrderInfo");
                Intent intent = new Intent(ServerMenuActivity.this, SerOrderInfoActivity.class);
                intent.putExtra("infoData", infoData);
//              startActivityForResult(intent, FINISH_ACTION);
                startActivity(intent);
                Log.i(TAG,"infoData"+infoData.toString());
            }
        }
    }
    public static final int FINISH_ACTION=66;
    @Override
    protected int getLayoutID() {
        return R.layout.llh_server_two_menu;
    }

    @Override
    public void usable() {

    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return null;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        adapter.changeCursor(data);
//        adapter.notifyDataSetChanged();
//        serverContentAdapter.changeCursor(data);
//        serverContentAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }

    @Override
    public IControler getControler() {
        return ServerMenuControler.getControler();
    }

    @Override
    public void setPromot(String promot) {
        shopping_product_price.setText("总价：" + promot);
    }

    @Override
    public View getParnetView() {
        return llh_shopping_bottom;
    }

    @Override
    public View getCardView() {
        return show_pp;
    }

    @Override
    public View getOrderView() {
        return upload_shopping_car;
    }

    @Override
    public View getPormotView() {
        return shopping_num_txt;
    }

    /**
     * 封装购物车下单请求
     *
     * @param data
     * @return
     */
    @Override
    public RequestParam getOrderParam(Vector<ShoppingCarListBean> data) {
        ServerRequestParam serverRequestParam = new ServerRequestParam();
        List<SerInitProPack> cartBeanList = null;
        if (data != null && !data.isEmpty()) {
            cartBeanList = new ArrayList<>(data.size());
            for (ShoppingCarListBean tempBean : data) {
                SerInitProPack serInitProPack = (SerInitProPack) tempBean.getBean();
                serInitProPack.setSerNum(tempBean.proNum);
                serInitProPack.setPackageName(tempBean.proName);
                cartBeanList.add(serInitProPack);
            }
        }
        RequestParam rp = serverRequestParam.doConfirmOrderInfo(cartBeanList);//此方法需要根据具体服务器定义的接口文档来实现
        return rp;
    }
    @Override
    public void notifyCardDataChanger(int posion) {
        serverContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyCardDataChanger() {
        serverContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(get_data_refresh!=null){
                    get_data_refresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(get_data_refresh!=null){
                    get_data_refresh.setRefreshing(false);
                }
            }
        });
    }


    public static class MyServerCateRecyleViewAdapter extends CursorAdapter{
        private LayoutInflater layoutInflater = null;
        private String TAG = "MyServerCateRecyleViewAdapter";
        private SoftReference<ProCateMenuServiceDao> softReference = null;

        public MyServerCateRecyleViewAdapter(Context context, Cursor c) {
            super(context, c,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view=getItemView();
            MyServerCateRecyleViewAdapter.ViewHolder holder=new MyServerCateRecyleViewAdapter.ViewHolder(view);
            view.setTag(holder);
            return  view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Object tag=view.getTag();
            if (tag != null) {
                MyServerCateRecyleViewAdapter.ViewHolder holder = (MyServerCateRecyleViewAdapter.ViewHolder) tag;
                daoToEntity( holder, cursor,-1);
            }
        }

        private void daoToEntity(MyServerCateRecyleViewAdapter.ViewHolder holder, Cursor cursor, int i) {
            ProCateMenuService menumService=readEntity( cursor,0);
            holder.setData(menumService);
        }

        public ProCateMenuService readEntity(Cursor cursor, int offset) {
            ProCateMenuService entity = new ProCateMenuService( //
                    cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                    cursor.getLong(offset + 1), // cateId
                    cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cateName
                    cursor.getInt(offset + 3), // cateLevel
                    cursor.getLong(offset + 4), // parentId
                    cursor.getInt(offset + 5), // isLeaf
                    cursor.getInt(offset + 6), // isDelete
                    cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // managerId
                    cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // managerName
                    cursor.getLong(offset + 9), // orgId
                    cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // orgName
                    cursor.getInt(offset + 11), // orgTypeId
                    cursor.isNull(offset + 12) ? null : new java.util.Date(cursor.getLong(offset + 12)), // addTime
                    cursor.isNull(offset + 13) ? null : new java.util.Date(cursor.getLong(offset + 13)), // updTime
                    cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // remark
                    cursor.getInt(offset + 15), // status
                    cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // pinYin
                    cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // pY
                    cursor.getInt(offset + 18), // isPack
                    cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19) // packorgId
            );
            return entity;
        }

        public void setDao(ProCateMenuServiceDao dao) {
            softReference = new SoftReference<ProCateMenuServiceDao>(dao);
        }

        public ViewHolder onCreatViewHolder(View view) {
            return new ViewHolder(view);
        }

        public View getItemView() {
            return layoutInflater.inflate(R.layout.food_type_item, null);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatTextView food_type_name;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.food_type_name = (AppCompatTextView) rootView.findViewById(R.id.food_type_name);
            }

            public void setData(ProCateMenuService pc) {
                this.food_type_name.setText(pc.getCateName());
            }
        }
    }

    public class MyServerContentRecyleViewAdapter extends BaseShopInfoRecyleViewAdapter {
        private LayoutInflater layoutInflater = null;
        private String TAG = "MyServerContentRecyleViewAdapter";
        private Context context;

        public MyServerContentRecyleViewAdapter(Context context, Cursor c) {
            super(context, c);
            this.context = context;
            layoutInflater = LayoutInflater.from(context);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: "+getClass().getSimpleName());
    }
}
