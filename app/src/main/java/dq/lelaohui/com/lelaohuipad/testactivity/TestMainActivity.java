package dq.lelaohui.com.lelaohuipad.testactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.util.MainActivityUtil;
import dq.lelaohui.com.lelaohuipad.view.DividerLine;

import static dq.lelaohui.com.lelaohuipad.R.layout.main_item;


public class TestMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView main_view = null;
    private MainAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
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
        main_view = (RecyclerView) findViewById(R.id.main_view);
        adapter=new MainAdapter();
        adapter.setContext(this);
//        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
//        dividerLine.setSize(10);
//        dividerLine.setColor(Color.LTGRAY);
//        main_view.addItemDecoration(dividerLine);
//        dividerLine = new DividerLine(DividerLine.HORIZONTAL);
//        dividerLine.setSize(10);
//        dividerLine.setColor(Color.LTGRAY);
//        main_view.addItemDecoration(dividerLine);
        main_view .setAdapter(adapter);
        RecyclerView.LayoutManager lm=new GridLayoutManager(this,3);
        main_view.setLayoutManager(lm);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_main, menu);
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

    public static class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
        private Context context;

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(main_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return MainActivityUtil.getData().size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,  int position) {
         final   ItemModel itemModel=getItemModel(position);
            if(itemModel==null)
                return;
            holder.img_log.setImageResource(itemModel.imageId);;
            holder.label.setText(itemModel.lable);
            holder.rootView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,itemModel.action);
                    context.startActivity(intent);
                }
            });

        }
        public ItemModel getItemModel( int position){
            return  MainActivityUtil.getData().get(position);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public View rootView;
            public AppCompatImageView img_log;
            public AppCompatTextView label;

            public ViewHolder(View rootView) {
                super(rootView);
                this.rootView = rootView;
                this.img_log = (AppCompatImageView) rootView.findViewById(R.id.img_log);
                this.label = (AppCompatTextView) rootView.findViewById(R.id.label);
            }

        }
    }
   public static class ItemModel{
        public int imageId;
        public  String  lable;
      public   Class action;
    }
}
