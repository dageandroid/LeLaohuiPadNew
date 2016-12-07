package dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by ThinkPad on 2016/12/1.
 */

public class ThreadManager {
    private static ThreadManager threadManager=null;
    private ExecutorService fixedThreadPool;
    private static final int MAX_THREAD_COUNT=3;
    private ThreadManager(){
        fixedThreadPool= Executors.newFixedThreadPool(MAX_THREAD_COUNT);
    }
    public static ThreadManager getInstance(){
        if (threadManager == null) {
            synchronized (ThreadManager.class){
                if (threadManager == null) {
                    threadManager=new ThreadManager();
                }
            }
        }
        return threadManager;
    }
    public  Future<? extends  Object> addRunnable(Callable runnable){
        Future<?>  future= fixedThreadPool.submit(runnable);
        return future;
    }
    public void shoutDown(){
        fixedThreadPool.shutdownNow();
    }
}
