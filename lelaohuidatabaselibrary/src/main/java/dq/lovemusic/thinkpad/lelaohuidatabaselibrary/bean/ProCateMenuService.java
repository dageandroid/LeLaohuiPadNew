package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;


/**
 * Created by ZTF on 2016/10/27.
 */
@Entity
public class ProCateMenuService extends BaseBean  {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id(autoincrement = true)
    @NoToJson
    private Long id;
    @Expose
    private long cateId;
    @Expose
    private String cateName;
    @Expose
    private int cateLevel = 0;
    @Expose
    private long parentId;
    @Expose
    private int isLeaf;
    @Expose
    private int isDelete = 0;
    @Expose
    private String managerId;
    @Expose
    private String managerName;
    @Expose
    private long orgId = 0l;
    @Expose
    private String orgName;
    @Expose
    private int orgTypeId;
    @Expose
    private Date addTime;
    @Expose
    private Date updTime;
    @Expose
    private String remark;
    @Expose
    private int status;
    @Expose
    private String pinYin;
    @Expose
    private String pY;
    @Expose
    private int isPack;
    @Expose
    private Long packorgId;
    @Generated(hash = 132832366)
    public ProCateMenuService(Long id, long cateId, String cateName, int cateLevel,
            long parentId, int isLeaf, int isDelete, String managerId,
            String managerName, long orgId, String orgName, int orgTypeId,
            Date addTime, Date updTime, String remark, int status, String pinYin,
            String pY, int isPack, Long packorgId) {
        this.id = id;
        this.cateId = cateId;
        this.cateName = cateName;
        this.cateLevel = cateLevel;
        this.parentId = parentId;
        this.isLeaf = isLeaf;
        this.isDelete = isDelete;
        this.managerId = managerId;
        this.managerName = managerName;
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgTypeId = orgTypeId;
        this.addTime = addTime;
        this.updTime = updTime;
        this.remark = remark;
        this.status = status;
        this.pinYin = pinYin;
        this.pY = pY;
        this.isPack = isPack;
        this.packorgId = packorgId;
    }
    @Generated(hash = 676913607)
    public ProCateMenuService() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getCateId() {
        return this.cateId;
    }
    public void setCateId(long cateId) {
        this.cateId = cateId;
    }
    public String getCateName() {
        return this.cateName;
    }
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    public int getCateLevel() {
        return this.cateLevel;
    }
    public void setCateLevel(int cateLevel) {
        this.cateLevel = cateLevel;
    }
    public long getParentId() {
        return this.parentId;
    }
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
    public int getIsLeaf() {
        return this.isLeaf;
    }
    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }
    public int getIsDelete() {
        return this.isDelete;
    }
    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
    public String getManagerId() {
        return this.managerId;
    }
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
    public String getManagerName() {
        return this.managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public long getOrgId() {
        return this.orgId;
    }
    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return this.orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public int getOrgTypeId() {
        return this.orgTypeId;
    }
    public void setOrgTypeId(int orgTypeId) {
        this.orgTypeId = orgTypeId;
    }
    public Date getAddTime() {
        return this.addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Date getUpdTime() {
        return this.updTime;
    }
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getPinYin() {
        return this.pinYin;
    }
    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }
    public String getPY() {
        return this.pY;
    }
    public void setPY(String pY) {
        this.pY = pY;
    }
    public int getIsPack() {
        return this.isPack;
    }
    public void setIsPack(int isPack) {
        this.isPack = isPack;
    }
    public Long getPackorgId() {
        return this.packorgId;
    }
    public void setPackorgId(Long packorgId) {
        this.packorgId = packorgId;
    }


}