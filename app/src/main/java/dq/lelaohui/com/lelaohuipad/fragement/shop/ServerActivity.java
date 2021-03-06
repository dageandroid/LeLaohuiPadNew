package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.SoftReference;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.FootDataListener;
import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.ServerDataManager;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateServiceDao;

/**
 * Created by ThinkPad on 2016/10/25.
 */

public class ServerActivity extends LeLaoHuiBaseActivity implements FootDataListener {
    private MyShopRecyleViewAdapter adapter = null;
    private ServerControler serverControler;
    private String TAG = getClass().getSimpleName();
    private RecyclerView server_type_menu;
    private SwipeRefreshLayout get_data_refresh;
    private AppCompatImageButton left_btn;
    private AppCompatTextView title_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverControler = (ServerControler) getControler();
        initView();
        ServerDataManager dataManager=new ServerDataManager(serverControler,this);
        serverControler.setDataManager(dataManager);
        dataManager.setDataListener(this);
        setNetResponIntercept(dataManager);
//        Cursor cursor = serverControler.getQueryAll(new ProCateService());

        adapter = new MyShopRecyleViewAdapter(this, null);
        final ProCateServiceDao dao=  (ProCateServiceDao) serverControler.getBaseDaoOperator().get();
        adapter.setDao(dao);


        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        server_type_menu.setLayoutManager(layoutManager);
        server_type_menu.setAdapter(adapter);
        adapter.setmOnItemClickListener(new BaseDataBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Cursor c) {
                ProCateService proCateService=readEntity(c,0);
                ServerDataManager dataManager1= (ServerDataManager) serverControler.getDataManager();
                Log.i(TAG, "onItemClick: "+proCateService.toString());
                dataManager1.checkCache();
               if(proCateService!=null){
                   Intent intent=new Intent(ServerActivity.this,ServerMenuActivity.class);
                   intent.putExtra("proCateServer", (Parcelable) proCateService);
                   startActivity(intent);
               }
            }
        });


        serverControler.getQueryServerCateqory(false);
        get_data_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serverControler.getQueryServerCateqory(true);
            }
        });
    }

    @Override
    public IControler getControler() {
        return ServerControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {
      String action=  bundle.getString("action");
    }

    @Override
    protected int getLayoutID() {
        return R.layout.llh_server_menu;
    }

    @Override
    public void usable() {

    }

    private void initView() {
        left_btn= (AppCompatImageButton) findViewById(R.id.left_btn);
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        title_tv = (AppCompatTextView) findViewById(R.id.title_tv);
        title_tv.setText("服务");
        server_type_menu = (RecyclerView) findViewById(R.id.server_type_menu);
        get_data_refresh = (SwipeRefreshLayout) findViewById(R.id.get_data_refresh);
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(get_data_refresh!=null){
                    get_data_refresh.setRefreshing(true);
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

    public ProCateService readEntity(Cursor cursor, int offset) {
        ProCateService entity = new ProCateService( //
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
                cursor.isNull(offset + 19) ? null : cursor.getLong(offset + 19), // packorgId
                cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // packorgTypeId
                cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // isEmptyShow
                cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22), // packStatus
                cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23), // packsupplierId
                cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24), // packsupplierTypeId
                cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // pictureName
                cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26) // pictureUrl
        );
        return entity;
    }
    @Override
    public void dataChanager(String id) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Cursor cursor = serverControler.getQueryFirstCursor();
               adapter.changeCursor(cursor);
               adapter.notifyDataSetChanged();
               adapter.setmOnItemClickListener(new BaseDataBaseAdapter.OnRecyclerViewItemClickListener() {
                   @Override
                   public void onItemClick(View view, Cursor c) {
                       ProCateService proCateService=readEntity(c,0);
                       ServerDataManager dataManager1= (ServerDataManager) serverControler.getDataManager();
                       Log.i(TAG, "onItemClick: "+proCateService.toString());
                       dataManager1.checkCache();
                       if(proCateService!=null){
                           Intent intent=new Intent(ServerActivity.this,ServerMenuActivity.class);
                           intent.putExtra("proCateServer", (Parcelable) proCateService);
                           startActivity(intent);
                       }
                   }
               });
               hideProgress();
           }
       });
    }

    public static class MyShopRecyleViewAdapter extends BaseDataBaseAdapter<MyShopRecyleViewAdapter.ViewHolder> {
        private SoftReference<ProCateServiceDao> softReference = null;
        private LayoutInflater layoutInflater=null;
        private String TAG="MyShopRecyleViewAdapter";
        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
            if (softReference != null) {
                ProCateServiceDao dao = softReference.get();
                Log.i(TAG, "onBindViewHolder: dao is "+dao);
                if (dao != null) {
                    ProCateService pc = dao.readEntity(cursor, 0);
                    holder.setData(pc);
                    Log.i(TAG, "onBindViewHolder: "+pc.toString());
                }
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor, int postion) {
            onBindViewHolder( holder,  cursor);
        }

        public MyShopRecyleViewAdapter(Context context, Cursor c) {
            super(context, c);
            layoutInflater= LayoutInflater.from(context);
        }

        @Override
        public View getItemView() {
            return layoutInflater.inflate(R.layout.llh_server_menu_item,null);
        }

        public void setDao(ProCateServiceDao dao) {
            softReference = new SoftReference<ProCateServiceDao>(dao);
        }
        @Override
        public ViewHolder onCreatViewHolder(View view) {
            return new  ViewHolder(view);
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatImageView image_menu;
            public AppCompatTextView type_menu_txt;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.image_menu = (AppCompatImageView) rootView.findViewById(R.id.image_menu);
                this.type_menu_txt = (AppCompatTextView) rootView.findViewById(R.id.type_menu_txt);
            }

            public void setData(ProCateService pc) {
                this.type_menu_txt.setText(pc.getCateName());
            }
        }
    }
}
