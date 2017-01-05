package dq.lelaohui.com.lelaohuipad.bean;

import android.graphics.Bitmap;

/**
 * Created by ZTF on 2017/1/5.
 * 摄像头显示设备信息
 */


public class ShowCamInfo {

    private String deviceName;//设备名称
    private String deviceAddress;//设备所在地
    private Bitmap bitmap;//设备显示图片

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
