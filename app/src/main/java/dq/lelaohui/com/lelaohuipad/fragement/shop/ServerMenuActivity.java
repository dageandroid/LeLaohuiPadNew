package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.ref.SoftReference;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.lelaohuipad.controler.ServerMenuControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateMenuService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateMenuServiceDao;

/**
 * Created by ZTF on 2016/10/30.
 */

public class ServerMenuActivity extends LeLaoHuiBaseActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView server_menu_content;
    private RecyclerView server_content_rv;
    private SwipeRefreshLayout get_data_refresh;
    private AppCompatTextView shopping_num_txt;
    private RelativeLayout shopping_num;
    private AppCompatTextView shopping_product_price;
    private AppCompatButton upload_shopping_car;
    private ServerMenuControler serverControler;
    private String TAG = getClass().getSimpleName();
    private ProCateService proCateService = null;
    private MyServerCateRecyleViewAdapter adapter = null;

    private GoogleApiClient client;
    private long cateIdL;
    private int isPackInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverControler = (ServerMenuControler) getControler();
        initView();
        if (getIntent() != null) {
            proCateService = (ProCateService) getIntent().getSerializableExtra("proCateServer");
            cateIdL = proCateService.getCateId();
            isPackInt = proCateService.getIsPack();
            serverControler.doQueryServerCategory(cateIdL, isPackInt, 1);
            Toast.makeText(ServerMenuActivity.this, "cateIdL=="+cateIdL+"  isPackInt=="+isPackInt, Toast.LENGTH_SHORT).show();
        }
        getSupportLoaderManager().initLoader(0, null, this);
        Cursor cursor = serverControler.getQueryTwoCursor(cateIdL);
//      adapter = new MyServerCateRecyleViewAdapter(this, serverControler.getQueryCursor(new ProCateMenuService()));
        adapter = new MyServerCateRecyleViewAdapter(this,cursor);
        ProCateMenuServiceDao dao = (ProCateMenuServiceDao) serverControler.getBaseDaoOperator().get();
        adapter.setDao(dao);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        server_menu_content.setLayoutManager(linearLayoutManager);
        server_menu_content.setAdapter(adapter);
        Log.i(TAG, "onCreate: cursor count=" + cursor.getCount() + "," + cursor.getExtras());

    }

    @Override
    public void result(Bundle bundle) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.llh_server_two_menu;
    }

    @Override
    public void usable() {

    }

    private void initView() {
        server_menu_content = (RecyclerView) findViewById(R.id.server_menu_content);
        server_content_rv = (RecyclerView) findViewById(R.id.server_content_rv);
        get_data_refresh = (SwipeRefreshLayout) findViewById(R.id.get_data_refresh);
        shopping_num_txt = (AppCompatTextView) findViewById(R.id.shopping_num_txt);
        shopping_num = (RelativeLayout) findViewById(R.id.shopping_num);
        shopping_product_price = (AppCompatTextView) findViewById(R.id.shopping_product_price);
        upload_shopping_car = (AppCompatButton) findViewById(R.id.upload_shopping_car);
        upload_shopping_car.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_shopping_car:

                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public IControler getControler() {
        return ServerMenuControler.getControler();
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
}
