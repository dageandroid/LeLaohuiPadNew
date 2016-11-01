package dq.lelaohui.com.lelaohuipad.controler;

import android.os.Bundle;

import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.manager.BaseDaoOperator;

/**
 * Created by ZTF on 2016/10/26.
 */

public class MainControler extends LaoHuiBaseControler {
    private static MainControler mainControler=null;
    private MainControler(){

    }
    public static MainControler getControler(){
        if(mainControler==null){
            synchronized (MainControler.class){
                if(mainControler==null){
                    mainControler=new MainControler();
                }
            }
        }
        return  mainControler;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator(String version) {
        return null;
    }

    @Override
    public BaseDaoOperator getBaseDaoOperator() {
        return null;
    }

    @Override
    public void doBusses(Bundle responseData) {

    }
}
