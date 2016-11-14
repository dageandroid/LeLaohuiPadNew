package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;


public class SerInitProPackDetail implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long packDetailId;
    private Long packId;
    private String serviceId;
    public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	private String serviceName;
    private String pictureName;
    private String pictureUrl;
	public Long getPackDetailId() {
		return packDetailId;
	}
	public void setPackDetailId(Long packDetailId) {
		this.packDetailId = packDetailId;
	}
	public Long getPackId() {
		return packId;
	}
	public void setPackId(Long packId) {
		this.packId = packId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}