package com.llh.ipcarmear.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;

import com.llh.ipcarmear.R;

/**
 * Created by ZTF on 2016/12/27.
 * 服务报警Service
 */

public class HelpService extends Service {

    private MediaPlayer mediaPlayer = null;
    private Vibrator mVibrator01;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.help);
            mediaPlayer.setLooping(true);
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mediaPlayer.start();
        mVibrator01 = (Vibrator) getApplication().getSystemService(
                Service.VIBRATOR_SERVICE);
        mVibrator01.vibrate(new long[] { 1000, 500, 1000, 500, 1000 }, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mVibrator01.cancel();
    }

}
