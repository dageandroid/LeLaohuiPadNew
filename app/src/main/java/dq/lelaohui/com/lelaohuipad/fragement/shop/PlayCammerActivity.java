package dq.lelaohui.com.lelaohuipad.fragement.shop;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;

import com.llh.ipcarmear.PlayActivity;

import dq.lelaohui.com.lelaohuipad.R;
import dq.lelaohui.com.lelaohuipad.adapter.CamerFunctionAdapter;

/**
 * Created by ZTF on 2016/12/27.
 */

public class PlayCammerActivity extends PlayActivity {
    private CamerFunctionAdapter adapter;
    private GridView cammer_function_id;
    private GLSurfaceView playSurface;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_cammer);
    }


    @Override
    public void findView() {
        super.findView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
