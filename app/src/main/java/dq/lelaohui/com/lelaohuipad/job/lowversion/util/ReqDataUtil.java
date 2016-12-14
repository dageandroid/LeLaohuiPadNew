package dq.lelaohui.com.lelaohuipad.job.lowversion.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ThinkPad on 2016/11/4.
 */

public class ReqDataUtil {
    private String ip="";
    private String port="";
    private  String url="http://"+ip+":"+port;
    OkHttpClient okHttpClient=new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    String post(String category,String action, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
