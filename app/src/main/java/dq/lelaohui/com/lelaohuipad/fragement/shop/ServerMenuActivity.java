package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.bean.SerOrderInfoData;
import dq.lelaohui.com.lelaohuipad.bean.ShoppingCarListBean;
import dq.lelaohui.com.lelaohuipad.controler.ServerMenuControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.adapter.BaseShopInfoRecyleViewAdapter;
import dq.lelaohui.com.lelaohuipad.fragement.shop.car.BaseShopCart;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.util.ServerRequestParam;
import dq.lelaohui.com.nettylibrary.socket.RequestParam;
import dq.lelaohui.com.nettylibrary.util.ServiceNetContant;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateMenuServiceDao;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.SerInitProPackDao;

/**
 * Created by ZTF on 2016/10/30.
 */

public class ServerMenuActivity extends LeLaoHuiBaseActivity implements BaseShopCart.CardDataChange, BaseShopCart.UiOperator, View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView server_menu_content;
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
    private long cateIdL;
    private int isPackInt;
    private Cursor cursor = null;
    private AppCompatImageButton left_btn;
    ImageView show_pp;
    BaseShopCart shopCartBase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverControler = (ServerMenuControler) getControler();
        initView();
        shopCartBase = new BaseShopCart(this);
        shopCartBase.setCardDataChange(this);
        shopCartBase.setUiOperator(this);
//        shopCartBase.init();
        if (getIntent() != null) {
            proCateService = getIntent().getParcelableExtra("proCateServer");
            cateIdL = proCateService.getCateId();
            isPackInt = proCateService.getIsPack();
            serverControler.doQueryServerCategory(cateIdL, isPackInt, 1);
        }
        getSupportLoaderManager().initLoader(0, null, this);

        if (cateIdL == -1) {
            cursor = serverControler.getQueryCateCursor();
        } else {
            cursor = serverControler.getQueryTwoCursor(cateIdL);
        }
        adapter = new MyServerCateRecyleViewAdapter(this, cursor);
        final ProCateMenuServiceDao dao = (ProCateMenuServiceDao) serverControler.getBaseDaoOperator().get();
        adapter.setDao(dao);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        server_menu_content.setLayoutManager(linearLayoutManager);
        server_menu_content.setAdapter(adapter);
        Log.i(TAG, "onCreate: cursor count=" + cursor.getCount() + "," + cursor.getExtras());
        adapter.setmOnItemClickListener(new BaseDataBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Cursor c) {
                ProCateMenuService proCateMenu = dao.readEntity(c, 0);
                String cateIdStr = null;
                if (proCateMenu != null) {
                    cateIdL = proCateMenu.getCateId();
                    cateIdStr = String.valueOf(proCateMenu.getCateId());
                    isPackInt = proCateMenu.getIsPack();
                    serverControler.doQueryServerCategory(cateIdL, isPackInt);
                }
                getSupportLoaderManager().initLoader(1, null, ServerMenuActivity.this);
                cursor = serverControler.getSerInitProCursor(Integer.parseInt(cateIdStr));
                Log.i(TAG, "onItemClick: ==" + cursor.getCount());
                serverContentAdapter = new MyServerContentRecyleViewAdapter(ServerMenuActivity.this, cursor);
                final SerInitProPackDao contentDao = (SerInitProPackDao) serverControler.getBaseDaoOperator("getInitSerProPackList").get();
                serverContentAdapter.setDao(contentDao);
                serverContentAdapter.setShopCartBase(shopCartBase);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServerMenuActivity.this);
//                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                server_content_rv.setLayoutManager(linearLayoutManager);
                server_content_rv.setAdapter(serverContentAdapter);
            }
        });
    }

    private void initView() {
        server_menu_content = (RecyclerView) findViewById(R.id.server_menu_content);
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
            Log.i(TAG, "action: ==" + action);
            if (action.equals(ServiceNetContant.ServiceResponseAction.CAL_ORDER_MONEY)) {
                SerOrderInfoData infoData = bundle.getParcelable("serOrderInfo");
                String getOrderCode = infoData.getSerOrderInfo().getOrderCode();
                if(infoData.getSerOrderInfoDetailBeanList()!=null&&infoData.getSerOrderInfoDetailBeanList().size()>0){
                    String packageName=     infoData.getSerOrderInfoDetailBeanList().get(0).getSerOrderInfoDetail().getPackageName();
                    Log.i(TAG,"packageName=="+packageName);
                }
                Log.i(TAG,"getOrderCode=="+getOrderCode);
                Intent intent = new Intent(ServerMenuActivity.this, SerOrderInfoActivity.class);
                intent.putExtra("infoData", infoData);
                startActivityForResult(intent, FINISH_ACTION);
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
        adapter.notifyDataSetChanged();
        serverContentAdapter.changeCursor(data);
        serverContentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

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

    }

    @Override
    public void hideProgress() {

    }

    public static class MyServerCateRecyleViewAdapter extends BaseDataBaseAdapter<MyServerCateRecyleViewAdapter.ViewHolder> {
        private LayoutInflater layoutInflater = null;
        private String TAG = "MyServerCateRecyleViewAdapter";
        private SoftReference<ProCateMenuServiceDao> softReference = null;

        public MyServerCateRecyleViewAdapter(Context context, Cursor c) {
            super(context, c);
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
            if (softReference != null) {
                ProCateMenuServiceDao dao = softReference.get();
                if (dao != null) {
                    ProCateMenuService proCateMenuService = dao.readEntity(cursor, 0);
                    holder.setData(proCateMenuService);
                }
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor, int postion) {
            onBindViewHolder( holder,  cursor);
        }

        public void setDao(ProCateMenuServiceDao dao) {
            softReference = new SoftReference<ProCateMenuServiceDao>(dao);
        }

        @Override
        public ViewHolder onCreatViewHolder(View view) {
            return new ViewHolder(view);
        }

        @Override
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
}
