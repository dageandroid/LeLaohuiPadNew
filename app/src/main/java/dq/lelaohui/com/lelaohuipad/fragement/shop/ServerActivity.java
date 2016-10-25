package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.lelaohuipad.port.IControler;

/**
 * Created by ThinkPad on 2016/10/25.
 */

public class ServerActivity extends LeLaoHuiBaseActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    MyShopRecyleViewAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServerControler serverControler= (ServerControler) getControler();
        getSupportLoaderManager().initLoader(0,null,this);
        adapter=new MyShopRecyleViewAdapter(this,serverControler.getQueryCursor(null));
    }

    @Override
    public IControler getControler() {
        return   ServerControler.getControler();
    }

    @Override
    public void result(Bundle bundle) {

    }

    @Override
    protected int getLayoutID() {
        return 0;
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
    public static class MyShopRecyleViewAdapter extends BaseDataBaseAdapter {

        public MyShopRecyleViewAdapter(Context context, Cursor c) {
            super(context, c);
        }

        public MyShopRecyleViewAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }
    }
}
