package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.base.IControler;
import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.FootterControler;
import dq.lelaohui.com.lelaohuipad.controler.ServerControler;
import dq.lelaohui.com.nettylibrary.activity.BaseActivity;

public class FooterActivity extends LeLaoHuiBaseActivity {
    private FragmentManager manager;
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
}
