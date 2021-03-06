package dq.lelaohui.com.lelaohuipad.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.List;

import dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider.DataManager;
import dq.lelaohui.com.lelaohuipad.port.IControler;
import dq.lelaohui.com.lelaohuipad.port.IControlerCallBack;
import dq.lelaohui.com.lelaohuipad.util.JsonUtil;
import dq.lelaohui.com.lelaohuipad.util.SysVar;
import dq.lelaohui.com.nettylibrary.socket.LlhResponseHandler;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ThinkPad on 2016/10/14.
 */

public abstract class LaoHuiBaseControler implements IControler {
    
    private String tag=getClass().getSimpleName();
    private Context mContext;
    public static final String RESPONSE_CODE_KEY="CODE";
    public static final String RESPONSE_MESSAGE="MESSAGE";
    public static final String CONTROLER_ACTION="action";
    private IControlerCallBack controlerCallBack;
    private SysVar sysVar=SysVar.getInstance();
    private JsonUtil jsonUtil;

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    private DataManager dataManager;
    public SysVar getSysVar() {
        return SysVar.getInstance();
    }

    public void setContext(Context context){
        this.mContext=context;
    }

    public Context getContext(){
        if(this.mContext==null){
            return null;
        }
        return this.mContext.getApplicationContext();

    }

    public void gotoPage(Class<?extends Activity> activity){
        if(mContext!=null){
            Intent intent=new Intent(mContext,activity);
            mContext.startActivity(intent);
            ((Activity)mContext).finish();
        }
    }

    public void gotoPage(String action){
        if(mContext!=null){
            Intent intent=new Intent(action);
            intent.setPackage(mContext.getPackageName());
            mContext.startActivity(intent);
        }
    }


    protected  void log(String message){
        Log.i(tag,message);
    }
    public void setIControlerCallBack(IControlerCallBack controlerCallBack){
        this.controlerCallBack=controlerCallBack;
    }

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
    protected  int getCenterType(){
        if(getSysVar()!=null){
            return getSysVar().getCenterType();
        }
        return 3;
    }
    public String getResponseAction(Bundle bundle){
        return bundle.getString(LlhResponseHandler.Respon_Key.ACTION);
    }
    public String getResponseBody(Bundle bundle){
        return bundle.getString(LlhResponseHandler.Respon_Key.BODY);
    }
    @Deprecated
    public Object getJsonToObject(String body, Class<?> t){
        if(jsonUtil==null){
            jsonUtil=JsonUtil.getInstance();
        }
        return jsonUtil.jsonToObject(body, t,false);
    }
    public Object getJsonToObject(String body, Class<?> t,boolean flag){
        if(jsonUtil==null){
            jsonUtil=JsonUtil.getInstance();
        }
        return jsonUtil.jsonToObject(body, t,flag);
    }
    @Deprecated
    public Object getJsonToObject(String body,Type t){
        if(jsonUtil==null){
            jsonUtil=JsonUtil.getInstance();
        }
        return jsonUtil.jsonToObject(body, t,false);
    }
    public Object getJsonToObject(String body,Type t,boolean flag){
        if(jsonUtil==null){
            jsonUtil=JsonUtil.getInstance();
        }
        return jsonUtil.jsonToObject(body, t,flag);
    }
    public IControlerCallBack getIControlerCallBack(){
        return controlerCallBack;
    }
    public List<? extends BaseBean> queryData(BaseBean obj){
       return queryData(null, obj);
    }
    public List<? extends BaseBean> queryData(String version,BaseBean obj){
        if(getBaseDaoOperator()!=null){
            return  getBaseDaoOperator().queryDataList(obj);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
    }
    public void updateData(List<?extends BaseBean> obj){
        if(getBaseDaoOperator()!=null){
            getBaseDaoOperator().updateData(obj);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
    }
    public void insertData(List<?extends  BaseBean> obj){
        if(getBaseDaoOperator()!=null){
            log("insert obj :"+obj.toString());
            getBaseDaoOperator().intsert(obj);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
    }

    public Cursor getQueryCursor(BaseBean bean){
        Cursor cursor=null;
        if(getBaseDaoOperator()!=null){
            cursor= getBaseDaoOperator().query(bean);
        }else{
            throw new RuntimeException("获取数据库对象为null");
        }
        return cursor;
    }

    public void destroy() {

    }
}
