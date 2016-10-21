package dq.lelaohui.com.lelaohuipad.cache;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.UserInfo;

/**
 * Created by ThinkPad on 2016/10/14.
 */
public class UserInfoCache {
    private static UserInfoCache ourInstance = new UserInfoCache();

    public static UserInfoCache getInstance() {
        return ourInstance;
    }
    private static UserInfo userInfo;
    private UserInfoCache() {
    }

}
