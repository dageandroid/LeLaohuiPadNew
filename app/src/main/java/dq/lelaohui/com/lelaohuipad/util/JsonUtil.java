package dq.lelaohui.com.lelaohuipad.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;


/**
 * Created by ThinkPad on 2016/10/14.
 */
public class JsonUtil {
    private static JsonUtil ourInstance = new JsonUtil();

    public static JsonUtil getInstance() {
        return ourInstance;
    }
    private Gson gson;
    private Gson gsonNoSkip=null;
    private JsonUtil() {
        if(gson==null){
            gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }
    }
    @Deprecated
    public Object jsonToObject(String jsonStr,Class cls){

        return gson.fromJson(jsonStr,cls);
    }
    public Object jsonToObject(String jsonStr,Class cls,boolean isSkip){
        Gson temp=null;
        if(isSkip){
            temp=gson;
        }else{
            temp=gsonNoSkip;

        }
        return temp.fromJson(jsonStr,cls);

    }

    private void getGsonNoSkip() {
        if(gsonNoSkip==null){
            gsonNoSkip=new GsonBuilder().create();
        }
    }
    @Deprecated
    public Object jsonToObject(String jsonStr,Type type){
        return gson.fromJson(jsonStr,type);
    }

    public Object jsonToObject(String jsonStr,Type type,boolean isSkip){
        Gson temp=null;
        if(isSkip){
            temp=gson;
        }else{
            temp=gsonNoSkip;

        }
        return temp.fromJson(jsonStr,type);
    }
    @Deprecated
    public String ObjectTojson(Object data){
       return  gson.toJson(data);
    }

    public String ObjectTojson(Object data,boolean isSkip){

        Gson temp=null;
        if(isSkip){
            temp=gson;
        }else{
            temp=gsonNoSkip;

        }
        return  temp.toJson(data);
    }
}
