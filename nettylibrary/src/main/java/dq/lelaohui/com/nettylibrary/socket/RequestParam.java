package dq.lelaohui.com.nettylibrary.socket;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.sun.commontransfer.adroid.TransferClientNetworkImpl;

import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import dq.lelaohui.com.nettylibrary.port.ReqParam;

/**
 * Created by ThinkPad on 2016/10/12.
 */

public class RequestParam implements ReqParam {
    private Bundle header;
    private Bundle body;
    private String SN;
    public RequestParam(){
        header=new Bundle();
        body=new Bundle();
    }
    public void addHeader(@NonNull String key,@NonNull  Object t){
        setBundlerData(header,key, t);
    }
    public void addBody(@NonNull String key,@NonNull  Object t){
        setBundlerData(body,key, t);
    }

    @Override
    public String getCurrenSN() {
        return SN;
    }

    @Override
    public Object getHeader(@NonNull String key) {
        return header.get(key);
    }

    @Override
    public Object getBody(@NonNull String key) {
        return body.get(key);
    }


    private void setBundlerData(@NonNull Bundle budle,@NonNull String key, @NonNull Object t) {
        if(t.getClass()==String.class){
            budle.putString(key,String.valueOf(t));
        }
        if(t.getClass()==int.class||t.getClass()==Integer.class){
            budle.putInt(key,(Integer)t);
        }
        if(t.getClass()==double.class||t.getClass()==Double.class){
            budle.putDouble(key,(Double)t);
        }
        if(t.getClass()==float.class||t.getClass()==Float.class){
            budle.putFloat(key,(Float)t);
        }
        if(t.getClass()==String[].class){
            budle.putStringArray(key,(String[])t);
        }
        if(t.getClass()==Bundle.class){
            budle.putBundle(key,(Bundle)t);
        }
    }
    private String getSN(){
        return String.format("%d", System.currentTimeMillis());
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public JsonObject headerToJsonObject(){
        SN = getSN();
        JsonObjectBuilder jsonObjectBuilder=Json.createObjectBuilder() ;
        jsonObjectBuilder.add("sn", SN);
        jsonObjectBuilder.add("uid", TransferClientNetworkImpl.getInstance().getUid());
        Set<String> headerKeySet=header.keySet();
        for(String keyStr:headerKeySet){
            jsonBuilderAdd( jsonObjectBuilder, keyStr, header.get(keyStr));
        }

        return jsonObjectBuilder.build();
    }
    public JsonObject bodyToJsonObject(){
        JsonObjectBuilder jsonObjectBuilder=Json.createObjectBuilder() ;
        Set<String> headerKeySet=body.keySet();
        for(String keyStr:headerKeySet){
            Object value=body.get(keyStr);
            keyStr=keyStr.trim();
            jsonBuilderAdd( jsonObjectBuilder, keyStr, value);
        }
        return jsonObjectBuilder.build();
    }
    private void jsonBuilderAdd(JsonObjectBuilder jsonObjectBuilder,String keyStr,Object value){
        if(value.getClass()==int.class||value.getClass()==Integer.class){
            jsonObjectBuilder.add(keyStr,(Integer)body.get(keyStr));
        }else if(value.getClass()==String.class){
            jsonObjectBuilder.add(keyStr, String.valueOf(value) );
        }else if(value.getClass()==Long.class||value.getClass()==long.class){
            jsonObjectBuilder.add(keyStr,(Long)value);
        }else if(value.getClass()==Float.class||value.getClass()==float.class){
            jsonObjectBuilder.add(keyStr,(Float)value);
        }else if(value.getClass()==Double.class||value.getClass()==double.class){
            jsonObjectBuilder.add(keyStr,(Double) value);
        }else if (value.getClass()==Bundle.class){
            jsonObjectBuilder.add(keyStr,ObjectToJson( (Bundle)value));
        }

    }
    private JsonObjectBuilder ObjectToJson(Bundle bundle){
        Bundle tempBundle=bundle;
        JsonObjectBuilder jsonObjectBuilder=Json.createObjectBuilder();
        Set<String> headerKeySet=tempBundle.keySet();
        for(String keyStr:headerKeySet){
            Object value=tempBundle.get(keyStr);
            keyStr=keyStr.trim();
            jsonBuilderAdd( jsonObjectBuilder, keyStr, value);
        }
        return jsonObjectBuilder;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(this.header);
        dest.writeBundle(this.body);
    }

    protected RequestParam(Parcel in) {
        this.header = in.readBundle();
        this.body = in.readBundle();
    }

    public static final Parcelable.Creator<RequestParam> CREATOR = new Parcelable.Creator<RequestParam>() {
        @Override
        public RequestParam createFromParcel(Parcel source) {
            return new RequestParam(source);
        }

        @Override
        public RequestParam[] newArray(int size) {
            return new RequestParam[size];
        }
    };
}
