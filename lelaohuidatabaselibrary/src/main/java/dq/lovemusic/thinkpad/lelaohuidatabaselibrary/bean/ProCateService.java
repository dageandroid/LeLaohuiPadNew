package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.ProCateServiceDao;

@Entity
public class ProCateService extends BaseBean implements Serializable, Parcelable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id(autoincrement = true)
	private Long  id;
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
    public Long getPackorgId() {
		return packorgId;
	}

	public void setPackorgId(Long packorgId) {
		this.packorgId = packorgId;
	}

	public Integer getPackorgTypeId() {
		return packorgTypeId;
	}

	public void setPackorgTypeId(Integer packorgTypeId) {
		this.packorgTypeId = packorgTypeId;
	}

	public Integer getIsEmptyShow() {
		return isEmptyShow;
	}

	public void setIsEmptyShow(Integer isEmptyShow) {
		this.isEmptyShow = isEmptyShow;
	}

	public Integer getPackStatus() {
		return packStatus;
	}

	public void setPackStatus(Integer packStatus) {
		this.packStatus = packStatus;
	}

	public Long getPacksupplierId() {
		return packsupplierId;
	}

	public void setPacksupplierId(Long packsupplierId) {
		this.packsupplierId = packsupplierId;
	}

	public Integer getPacksupplierTypeId() {
		return packsupplierTypeId;
	}

	public void setPacksupplierTypeId(Integer packsupplierTypeId) {
		this.packsupplierTypeId = packsupplierTypeId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	private Integer packorgTypeId;
    private Integer isEmptyShow;//1:不显示 0或者默认：是显示已经使用
    private Integer packStatus;
    private Long packsupplierId;
    private Integer packsupplierTypeId;
    private String pictureName;

    private String pictureUrl;
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public ProCateService() {
		super();
	}

	public Integer getIsPack() {
		return isPack;
	}

	public void setIsPack(Integer isPack) {
		this.isPack = isPack;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public String getpY() {
		return pY;
	}

	public void setpY(String pY) {
		this.pY = pY;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName == null ? null : cateName.trim();
	}

	public Integer getCateLevel() {
		return cateLevel;
	}

	public void setCateLevel(Integer cateLevel) {
		this.cateLevel = cateLevel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId == null ? null : managerId.trim();
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.cateId);
		dest.writeString(this.cateName);
		dest.writeInt(this.cateLevel);
		dest.writeLong(this.parentId);
		dest.writeInt(this.isLeaf);
		dest.writeInt(this.isDelete);
		dest.writeString(this.managerId);
		dest.writeString(this.managerName);
		dest.writeLong(this.orgId);
		dest.writeString(this.orgName);
		dest.writeInt(this.orgTypeId);
		dest.writeLong(this.addTime != null ? this.addTime.getTime() : -1);
		dest.writeLong(this.updTime != null ? this.updTime.getTime() : -1);
		dest.writeString(this.remark);
		dest.writeInt(this.status);
		dest.writeString(this.pinYin);
		dest.writeString(this.pY);
		dest.writeInt(this.isPack);
		dest.writeValue(this.packorgId);
		dest.writeValue(this.packorgTypeId);
		dest.writeValue(this.isEmptyShow);
		dest.writeValue(this.packStatus);
		dest.writeValue(this.packsupplierId);
		dest.writeValue(this.packsupplierTypeId);
		dest.writeString(this.pictureName);
		dest.writeString(this.pictureUrl);
	}

	public void setCateId(long cateId) {
					this.cateId = cateId;
	}

	public void setCateLevel(int cateLevel) {
					this.cateLevel = cateLevel;
	}

	public void setIsLeaf(int isLeaf) {
					this.isLeaf = isLeaf;
	}

	public void setIsDelete(int isDelete) {
					this.isDelete = isDelete;
	}

	public void setOrgId(long orgId) {
					this.orgId = orgId;
	}

	public void setOrgTypeId(int orgTypeId) {
					this.orgTypeId = orgTypeId;
	}

	public void setStatus(int status) {
					this.status = status;
	}

	public String getPY() {
					return this.pY;
	}

	public void setPY(String pY) {
					this.pY = pY;
	}

	public void setIsPack(int isPack) {
					this.isPack = isPack;
	}

	protected ProCateService(Parcel in) {
		this.cateId = in.readLong();
		this.cateName = in.readString();
		this.cateLevel = in.readInt();
		this.parentId = in.readLong();
		this.isLeaf = in.readInt();
		this.isDelete = in.readInt();
		this.managerId = in.readString();
		this.managerName = in.readString();
		this.orgId = in.readLong();
		this.orgName = in.readString();
		this.orgTypeId = in.readInt();
		long tmpAddTime = in.readLong();
		this.addTime = tmpAddTime == -1 ? null : new Date(tmpAddTime);
		long tmpUpdTime = in.readLong();
		this.updTime = tmpUpdTime == -1 ? null : new Date(tmpUpdTime);
		this.remark = in.readString();
		this.status = in.readInt();
		this.pinYin = in.readString();
		this.pY = in.readString();
		this.isPack = in.readInt();
		this.packorgId = (Long) in.readValue(Long.class.getClassLoader());
		this.packorgTypeId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.isEmptyShow = (Integer) in.readValue(Integer.class.getClassLoader());
		this.packStatus = (Integer) in.readValue(Integer.class.getClassLoader());
		this.packsupplierId = (Long) in.readValue(Long.class.getClassLoader());
		this.packsupplierTypeId = (Integer) in.readValue(Integer.class.getClassLoader());
		this.pictureName = in.readString();
		this.pictureUrl = in.readString();
	}
	public ProCateService(ContentValues values){
		this.cateId = values.getAsLong(ProCateServiceDao.Properties.CateId.columnName);
		this.cateName = values.getAsString(ProCateServiceDao.Properties.CateName.columnName);
		this.cateLevel = values.getAsInteger(ProCateServiceDao.Properties.CateLevel.columnName);
		this.parentId = values.getAsLong(ProCateServiceDao.Properties.PackorgId.columnName);
		this.isLeaf = values.getAsInteger(ProCateServiceDao.Properties.IsLeaf.columnName);
		this.isDelete = values.getAsInteger(ProCateServiceDao.Properties.IsDelete.columnName);;
		this.managerId = values.getAsString(ProCateServiceDao.Properties.ManagerId.columnName);
		this.managerName = values.getAsString(ProCateServiceDao.Properties.ManagerName.columnName);
		this.orgId =  values.getAsLong(ProCateServiceDao.Properties.OrgId.columnName);
		this.orgName =values.getAsString(ProCateServiceDao.Properties.OrgName.columnName);
		this.orgTypeId = values.getAsInteger(ProCateServiceDao.Properties.OrgTypeId.columnName);
		long tmpAddTime= values.getAsLong("tmpAddTime");
		this.addTime = tmpAddTime == -1 ? null : new Date(tmpAddTime);
		long tmpUpdTime = values.getAsLong("tmpUpdTime");
		this.updTime = tmpUpdTime == -1 ? null : new Date(tmpUpdTime);
		this.remark =values.getAsString(ProCateServiceDao.Properties.Remark.columnName);
		this.status = values.getAsInteger(ProCateServiceDao.Properties.Status.columnName);
		this.pinYin = values.getAsString(ProCateServiceDao.Properties.PinYin.columnName);
		this.pY =values.getAsString(ProCateServiceDao.Properties.PY.columnName);
		this.isPack =values.getAsInteger(ProCateServiceDao.Properties.IsPack.columnName);
		this.packorgId = values.getAsLong(ProCateServiceDao.Properties.PackorgId.columnName);
		this.packorgTypeId = values.getAsInteger(ProCateServiceDao.Properties.PackorgTypeId.columnName);
		this.isEmptyShow = values.getAsInteger(ProCateServiceDao.Properties.IsEmptyShow.columnName);
		this.packStatus = values.getAsInteger(ProCateServiceDao.Properties.PackStatus.columnName);
		this.packsupplierId = values.getAsLong(ProCateServiceDao.Properties.PacksupplierId.columnName);
		this.packsupplierTypeId = values.getAsInteger(ProCateServiceDao.Properties.PacksupplierTypeId.columnName);
		this.pictureName = values.getAsString(ProCateServiceDao.Properties.PictureName.columnName);
		this.pictureUrl = values.getAsString(ProCateServiceDao.Properties.PictureUrl.columnName);
	}
	public ContentValues toContentValues(){
		ContentValues contentValues=new ContentValues();
		contentValues.put(ProCateServiceDao.Properties.CateId.columnName,this.cateId);
		contentValues.put(ProCateServiceDao.Properties.CateName.columnName,this.cateName);
		contentValues.put(ProCateServiceDao.Properties.CateLevel.columnName,this.cateLevel);
		contentValues.put(ProCateServiceDao.Properties.PackorgId.columnName,this.packorgId);
		contentValues.put(ProCateServiceDao.Properties.IsLeaf.columnName,this.isLeaf);
		contentValues.put(ProCateServiceDao.Properties.IsDelete.columnName,this.isDelete);
		contentValues.put(ProCateServiceDao.Properties.ManagerId.columnName,this.managerId);
		contentValues.put(ProCateServiceDao.Properties.ManagerName.columnName,this.managerName);
		contentValues.put(ProCateServiceDao.Properties.OrgId.columnName,this.orgId);
		contentValues.put(ProCateServiceDao.Properties.OrgName.columnName,this.orgName);
		contentValues.put(ProCateServiceDao.Properties.OrgTypeId.columnName,this.orgTypeId);
		contentValues.put(ProCateServiceDao.Properties.Remark.columnName,this.remark);
		contentValues.put(ProCateServiceDao.Properties.Status.columnName,this.status);
		contentValues.put(ProCateServiceDao.Properties.PinYin.columnName,this.pinYin);
		contentValues.put(ProCateServiceDao.Properties.PY.columnName,this.pY);
		contentValues.put(ProCateServiceDao.Properties.IsPack.columnName,this.isPack);
		contentValues.put(ProCateServiceDao.Properties.PackorgId.columnName,this.packorgId);
		contentValues.put(ProCateServiceDao.Properties.PackorgTypeId.columnName,this.packorgTypeId);
		contentValues.put(ProCateServiceDao.Properties.IsEmptyShow.columnName,this.isEmptyShow);
		contentValues.put(ProCateServiceDao.Properties.PackStatus.columnName,this.packStatus);
		contentValues.put(ProCateServiceDao.Properties.PacksupplierId.columnName,this.packsupplierId);
		contentValues.put(ProCateServiceDao.Properties.PacksupplierTypeId.columnName,this.packsupplierTypeId);
		contentValues.put(ProCateServiceDao.Properties.PictureName.columnName,this.pictureName);
		contentValues.put(ProCateServiceDao.Properties.PictureUrl.columnName,this.pictureUrl);
		contentValues.put("tmpAddTime",new Date(System.currentTimeMillis()).getTime());
		contentValues.put("tmpUpdTime",new Date(System.currentTimeMillis()).getTime());

		return contentValues;

	}
	@Generated(hash = 820579168)
	public ProCateService(Long id, long cateId, String cateName, int cateLevel, long parentId, int isLeaf,
									int isDelete, String managerId, String managerName, long orgId, String orgName, int orgTypeId,
									Date addTime, Date updTime, String remark, int status, String pinYin, String pY, int isPack,
									Long packorgId, Integer packorgTypeId, Integer isEmptyShow, Integer packStatus, Long packsupplierId,
									Integer packsupplierTypeId, String pictureName, String pictureUrl) {
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
					this.packorgTypeId = packorgTypeId;
					this.isEmptyShow = isEmptyShow;
					this.packStatus = packStatus;
					this.packsupplierId = packsupplierId;
					this.packsupplierTypeId = packsupplierTypeId;
					this.pictureName = pictureName;
					this.pictureUrl = pictureUrl;
	}

	public static final Parcelable.Creator<ProCateService> CREATOR = new Parcelable.Creator<ProCateService>() {
		@Override
		public ProCateService createFromParcel(Parcel source) {
			return new ProCateService(source);
		}

		@Override
		public ProCateService[] newArray(int size) {
			return new ProCateService[size];
		}
	};

	@Override
	public String toString() {
		return "ProCateService{" +
				"cateId=" + cateId +
				", cateName='" + cateName + '\'' +
				", cateLevel=" + cateLevel +
				", parentId=" + parentId +
				", isLeaf=" + isLeaf +
				", isDelete=" + isDelete +
				", managerId='" + managerId + '\'' +
				", managerName='" + managerName + '\'' +
				", orgId=" + orgId +
				", orgName='" + orgName + '\'' +
				", orgTypeId=" + orgTypeId +
				", addTime=" + addTime +
				", updTime=" + updTime +
				", remark='" + remark + '\'' +
				", status=" + status +
				", pinYin='" + pinYin + '\'' +
				", pY='" + pY + '\'' +
				", isPack=" + isPack +
				", packorgId=" + packorgId +
				", packorgTypeId=" + packorgTypeId +
				", isEmptyShow=" + isEmptyShow +
				", packStatus=" + packStatus +
				", packsupplierId=" + packsupplierId +
				", packsupplierTypeId=" + packsupplierTypeId +
				", pictureName='" + pictureName + '\'' +
				", pictureUrl='" + pictureUrl + '\'' +
				'}';
	}

	public Long getId() {
					return this.id;
	}

	public void setId(Long id) {
					this.id = id;
	}
}