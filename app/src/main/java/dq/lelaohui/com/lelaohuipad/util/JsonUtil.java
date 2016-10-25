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
    private JsonUtil() {
        if(gson==null){
            gson=new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    Collection<Annotation> a= f.getAnnotations();
                    boolean isSkip=false;
                    for(Annotation annotation:a){
                        if(annotation.getClass()== NoToJson.class){
                            isSkip=true;
                            break;
                        }
                    }
                    return isSkip;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    //过滤掉 类名包含 Bean的类
                    return false;
                }
            }).create();
        }
    }

    public Object jsonToObject(String jsonStr,Class cls){

        return gson.fromJson(jsonStr,cls);
    }
    public Object jsonToObject(String jsonStr,Type type){
        return gson.fromJson(jsonStr,type);
    }
}
