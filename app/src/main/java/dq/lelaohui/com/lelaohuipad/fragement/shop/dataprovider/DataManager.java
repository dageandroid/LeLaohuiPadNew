package dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import dq.lelaohui.com.lelaohuipad.base.LaoHuiBaseControler;
import dq.lelaohui.com.nettylibrary.port.NetResponIntercept;

/**
 * Created by ThinkPad on 2016/12/1.
 */

public abstract class DataManager implements NetResponIntercept {
    private ThreadManager manager=ThreadManager.getInstance();
    private LaoHuiBaseControler controler;
    public void register(LaoHuiBaseControler controler) {
        this.controler = controler;
    }

    public Future<?> addTask(Callable task){

        return  manager.addRunnable(task);
    }
}
