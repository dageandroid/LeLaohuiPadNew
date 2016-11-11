package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;

public  class GroupIdListBean extends BaseBean{
    @NoToJson
    private long ownerId;
    @Expose
    private int groupId;

    public GroupIdListBean(long ownerId, int groupId) {
        this.ownerId = ownerId;
        this.groupId = groupId;
    }

    public GroupIdListBean() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    @Override
    public String toString() {
        return "GroupIdListBean{" +
                "ownerId=" + ownerId +
                ", groupId=" + groupId +
                '}';
    }
}
