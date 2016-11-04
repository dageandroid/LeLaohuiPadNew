package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.SoftReference;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.ProCateService;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateServiceDao;

/**
 * Created by ThinkPad on 2016/10/25.
 */

public class ServerActivity extends LeLaoHuiBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private MyShopRecyleViewAdapter adapter = null;
    private ServerControler serverControler;
    private String TAG = getClass().getSimpleName();
    private RecyclerView server_type_menu;
    private SwipeRefreshLayout get_data_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverControler = (ServerControler) getControler();
        initView();
        serverControler.doQueryServerCategory();
        getSupportLoaderManager().initLoader(0, null, this);
//        Cursor cursor = serverControler.getQueryCursor(new ProCateService());
      Cursor cursor = serverControler.getQueryFirstCursor();
        Log.i(TAG, "onCreate: cursor count=" + cursor.getCount()+","+cursor.getExtras());
        adapter = new MyShopRecyleViewAdapter(this, cursor);
        final ProCateServiceDao dao=  (ProCateServiceDao) serverControler.getBaseDaoOperator().get();
        adapter.setDao(dao);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        server_type_menu.setLayoutManager(layoutManager);
        server_type_menu.setAdapter(adapter);
        adapter.setmOnItemClickListener(new BaseDataBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Cursor c) {
                ProCateService proCateService=dao.readEntity(c,0);
               if(proCateService!=null){
                   Intent intent=new Intent(ServerActivity.this,ServerMenuActivity.class);
                   intent.putExtra("proCateServer", (Parcelable) proCateService);
                   startActivity(intent);
               }
            }
        });
    }

    @Override
    public IControler getControler() {
        return ServerControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.llh_server_menu;
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
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void initView() {
        server_type_menu = (RecyclerView) findViewById(R.id.server_type_menu);
        get_data_refresh = (SwipeRefreshLayout) findViewById(R.id.get_data_refresh);
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
                    Log.i(TAG, "onBindViewHolder: ");
                    holder.setData(pc);
                }
            }
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

        public MyShopRecyleViewAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
            layoutInflater= LayoutInflater.from(context);
        }


        @Override
        public ViewHolder onCreatViewHolder(View view) {
            return new ViewHolder(view);
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
