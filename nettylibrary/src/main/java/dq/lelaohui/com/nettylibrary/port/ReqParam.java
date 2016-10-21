package dq.lelaohui.com.nettylibrary.port;

import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by ThinkPad on 2016/10/12.
 */

public interface ReqParam extends Parcelable {
    public void addHeader(@NonNull String key, @NonNull  Object t);
    public void addBody(@NonNull String key,@NonNull  Object t);
    public String getCurrenSN();
    public Object getHeader(@NonNull String key);
    public Object getBody(@NonNull String key);
}
