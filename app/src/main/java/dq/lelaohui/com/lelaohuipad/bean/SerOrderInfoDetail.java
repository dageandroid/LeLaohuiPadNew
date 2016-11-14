package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SerOrderInfoDetail  implements Serializable{
    /**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public Long detailId;
    public Long orderId;

    public String orderCode;

    public int proType;

    public Long packageId;

    public String packageName;

    public Long levelId;

    public Double price;
    public int serNum;
    public String pictureName;

    public String pictureUrl;
    public Date addTime;
    
    
	public Date updTime;
    /**
     * 显示
     */
	public long packId;
    public String packName;
    public String orgName;
    public int orgTypeId;
    public long orgId;
    public long serviceCateId;
    public int isPro;
    public String reamark;//详细描述
    public int saleNums;//销量
    public List<SerInitProPackDetail> serInitProPackDetailList;//项中的数据
    public double amount;//系统算出的价格原价
	public int buyNum;
   

    public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public Long getServiceCateId() {
		return serviceCateId;
	}

	public void setServiceCateId(long serviceCateId) {
		this.serviceCateId = serviceCateId;
	}

	public Integer getIsPro() {
		return isPro;
	}

	public void setIsPro(Integer isPro) {
		this.isPro = isPro;
	}

	public String getReamark() {
		return reamark;
	}

	public void setReamark(String reamark) {
		this.reamark = reamark;
	}

	public Integer getSaleNums() {
		return saleNums;
	}

	public void setSaleNums(Integer saleNums) {
		this.saleNums = saleNums;
	}

	public List<SerInitProPackDetail> getSerInitProPackDetailList() {
		return serInitProPackDetailList;
	}

	public void setSerInitProPackDetailList(
			List<SerInitProPackDetail> serInitProPackDetailList) {
		this.serInitProPackDetailList = serInitProPackDetailList;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
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

	public Integer getSerNum() {
		return serNum;
	}

	public void setSerNum(Integer serNum) {
		this.serNum = serNum;
	}

	public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getProType() {
        return proType;
    }

    public void setProType(Integer proType) {
        this.proType = proType;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
    
}