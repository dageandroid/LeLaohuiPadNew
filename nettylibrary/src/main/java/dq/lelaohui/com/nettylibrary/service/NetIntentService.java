package dq.lelaohui.com.nettylibrary.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.sun.commontransfer.adroid.TransferClientNetworkImpl;
import com.sun.commontransfer.adroid.TransferObject;

import dq.lelaohui.com.nettylibrary.socket.RequestParam;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NetIntentService extends IntentService {

    public static final String KEY="REQ_SERVICE_KEY";
    private String tag="NetIntentService";

    public NetIntentService() {
        super("NetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            RequestParam rp=intent.getParcelableExtra(KEY);
            handleReqBusses(rp);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleReqBusses(RequestParam rp) {
        // TODO: Handle action Foo
        TransferObject business = new TransferObject();
        business.head=rp.headerToJsonObject();
        business.body=rp.bodyToJsonObject();
        Log.i(tag,"req param="+business.toString());
        boolean ret = TransferClientNetworkImpl.getInstance()
                .sendTransferObject(business);
        if (ret) {
            Log.i("tag_testing_echo", "send package successrull!");
        } else {
            Log.i("tag_testing_echo", "send package failed");
        }

    }


}
