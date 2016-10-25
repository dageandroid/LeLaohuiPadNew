package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.BaseDataBaseAdapter;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;

public class FooterActivity extends LeLaoHuiBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private FragmentManager manager;
    private MyRecyleViewAdapter adapter=null;
    @Override
    public IControler getControler() {
        return ServerControler.getControler();
//        return FootterControler.getControler();
    }
    private ShopListFragment shopListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        shopListFragment=ShopListFragment.newInstance("1","2");
        shopListFragment.setControler((LaoHuiBaseControler) getControler());
        manager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= manager.beginTransaction();
        fragmentTransaction.add(R.id.footmenum,shopListFragment);
        fragmentTransaction.commit();
        ServerControler footterControler= (ServerControler) getControler();
        footterControler.doQueryServerCategory();
        adapter=new MyRecyleViewAdapter(this,((ServerControler) getControler()).getQueryCursor(null));
        getSupportLoaderManager().initLoader(0,null,this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_footer;
    }

    @Override
    public void result(Bundle bundle) {
        if(shopListFragment!=null){
            shopListFragment.result(bundle);
        }
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
    public static class MyRecyleViewAdapter extends BaseDataBaseAdapter {

        public MyRecyleViewAdapter(Context context, Cursor c) {
            super(context, c);
        }

        public MyRecyleViewAdapter(Context context, Cursor c, int flags) {
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
