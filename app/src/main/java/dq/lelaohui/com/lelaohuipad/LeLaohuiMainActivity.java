package dq.lelaohui.com.lelaohuipad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.base.LeLaoHuiBaseActivity;
import dq.lelaohui.com.lelaohuipad.controler.MainControler;
import dq.lelaohui.com.lelaohuipad.fragement.shop.FooterActivity;
import dq.lelaohui.com.lelaohuipad.fragement.shop.ServerActivity;
import dq.lelaohui.com.lelaohuipad.port.IControler;

public class LeLaohuiMainActivity extends LeLaoHuiBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private Button button;
    private RelativeLayout content_le_laohui_main;
    private FloatingActionButton fab;
    private RecyclerView main_view;
    private NavigationView nav_view;
    private DrawerLayout drawer_layout;
    MyAdapter myAdapter=null;
    @Override
    public IControler getControler() {
        return MainControler.getControler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
    }
    public   List<String> getData(){
        List<String> listData=new ArrayList<String>();
        listData.add("服务");
        listData.add("餐品");
        listData.add("商品");
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

    @Override
    public void result(Bundle bundle) {

    }

    @Override
    public void usable() {

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        button = (Button) findViewById(R.id.button);
        content_le_laohui_main = (RelativeLayout) findViewById(R.id.content_le_laohui_main);
//        fab = (FloatingActionButton) findViewById(R.id.fab);
        main_view = (RecyclerView) findViewById(R.id.main_view);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        button.setOnClickListener(this);
//        fab.setOnClickListener(this);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<String> data;
        Context context;
        public MyAdapter(Context context, List<String> data) {
            this.context = context;
            this.data = data;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.llh_server_menu_item, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.type_menu_txt.setText("" +data.get(position).toString());
            viewHolder.image_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=null;
                    if (position==0){
                        intent=new Intent(getApplicationContext(),ServerActivity.class);
                    }else{
                        intent=new Intent(getApplicationContext(),FooterActivity.class);
                    }
                    if (intent!=null){
                        Toast.makeText(LeLaohuiMainActivity.this,"position==="+position,Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
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

                break;
            case R.id.fab:

                break;
        }
    }
}
