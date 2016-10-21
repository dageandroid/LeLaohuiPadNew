package dq.lelaohui.com.lelaohuipad.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dq.lelaohui.com.lelaohuipad.util.SysVar;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public abstract class LaoHuiBaseControler implements IControler {
    private String tag=getClass().getSimpleName();
    private Context mContext;
    public static final String RESPONSE_CODE_KEY="CODE";
    public static final String RESPONSE_MESSAGE="MESSAGE";
    private IControlerCallBack controlerCallBack;
    private SysVar sysVar=SysVar.getInstance();

    public SysVar getSysVar() {
        return SysVar.getInstance();
    }

    public void setContext(Context context){
        this.mContext=context;
    };
    public Context getContext(){
        if(this.mContext==null){
            return null;
        }
        return this.mContext.getApplicationContext();

    };
    public void gotoPage(Class<?extends Activity> activity){
        if(mContext!=null){
            Intent intent=new Intent(mContext,activity);
            mContext.startActivity(intent);
        }
    };
    public void gotoPage(String action){
        if(mContext!=null){
            Intent intent=new Intent(action);
            intent.setPackage(mContext.getPackageName());
            mContext.startActivity(intent);
        }
    };
    protected  void log(String message){
        Log.i(tag,message);
    }
    public void setIControlerCallBack(IControlerCallBack controlerCallBack){
        this.controlerCallBack=controlerCallBack;
    };
  protected void  doErrorPromot(Bundle bundle){
        if(this.controlerCallBack!=null){
            this.controlerCallBack.result(bundle);
        }
    }
    protected int getCenterId(){
        if(getSysVar()!=null){
           return getSysVar().getCenterId();
        }
        return -1;
    }
    protected  int getOrgId(){
        if(getSysVar()!=null){
            return getSysVar().getOrgId();
        }
        return -1;
    }
    protected  int getOrgType(){
        if(getSysVar()!=null){
            return getSysVar().getOrgType();
        }
        return -1;
    }
}
