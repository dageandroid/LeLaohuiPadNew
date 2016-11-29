package dq.lelaohui.com.lelaohuipad.job.lowversion;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

public class MyJobService extends GcmTaskService {
    public MyJobService() {
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        return 0;
    }

}
