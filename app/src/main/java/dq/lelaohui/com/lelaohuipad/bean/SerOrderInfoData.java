package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoData extends BaseOrderCate implements Parcelable {
    @Override
    public String toString() {
        return "SerOrderInfoData{" +
                "serOrderInfo=" + serOrderInfo +
                ", serOrderInfoDetailBeanList=" + serOrderInfoDetailBeanList +
                '}';
    }

    /**
     * orderCode : 100020161113092212786
     * amountPayable : 38
     * payStatus : 0
     */

    private SerOrderInfoBean serOrderInfo;
    /**
     * serOrderInfoDetail : {"orderCode":"110020161113092212786","packId":25,"packName":"中心修脚单次包","levelId":1,"price":38,"serNum":1,"pictureUrl":"/LelaoHuiWebApp/folder/xiujiao.jpg","orgTypeId":3,"orgName":"乐老汇罗庄养老中心"}
     */

    private List<SerOrderInfoDetailBeanListBean> serOrderInfoDetailBeanList;

    public SerOrderInfoBean getSerOrderInfo() {
        return serOrderInfo;
    }

    public void setSerOrderInfo(SerOrderInfoBean serOrderInfo) {
        this.serOrderInfo = serOrderInfo;
    }

    public List<SerOrderInfoDetailBeanListBean> getSerOrderInfoDetailBeanList() {
        return serOrderInfoDetailBeanList;
    }

    public void setSerOrderInfoDetailBeanList(List<SerOrderInfoDetailBeanListBean> serOrderInfoDetailBeanList) {
        this.serOrderInfoDetailBeanList = serOrderInfoDetailBeanList;
    }

    public static class SerOrderInfoBean extends BaseOrderCate implements Parcelable {
        private String orderCode;
        private int amountPayable;
        private int payStatus;

        private String contactAddress;

        private Long customerId;

        private String customerName;

        private String customerPhone;

        private Long orderPersionId;

        private String orderPersionName;
        private Long supplierId;
        private int supplierTypeId;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public Long getSupplierId() {
            return supplierId;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public int getOrgId() {
            return orgId;
        }

        @Override
        public String toString() {
            return "SerOrderInfo{" +
                    "orderCode='" + orderCode + '\'' +
                    ", amountPayable=" + amountPayable +
                    ", payStatus=" + payStatus +
                    ", contactAddress='" + contactAddress + '\'' +
                    ", customerId=" + customerId +
                    ", customerName='" + customerName + '\'' +
                    ", customerPhone='" + customerPhone + '\'' +
                    ", orderPersionId=" + orderPersionId +
                    ", orderPersionName='" + orderPersionName + '\'' +
                    ", supplierId=" + supplierId +
                    ", supplierTypeId=" + supplierTypeId +
                    ", payStyle=" + payStyle +
                    ", supplierName='" + supplierName + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", orgId=" + orgId +
                    ", orgTypeId=" + orgTypeId +
                    '}';
        }

        public int getPayStyle() {
            return payStyle;
        }

        public void setPayStyle(int payStyle) {
            this.payStyle = payStyle;
        }

        private int payStyle;
        public int getOrgTypeId() {
            return orgTypeId;
        }

        public void setOrgTypeId(int orgTypeId) {
            this.orgTypeId = orgTypeId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public int getSupplierTypeId() {
            return supplierTypeId;
        }

        public void setSupplierTypeId(int supplierTypeId) {
            this.supplierTypeId = supplierTypeId;
        }

        public void setSupplierId(Long supplierId) {
            this.supplierId = supplierId;
        }

        public String getOrderPersionName() {
            return orderPersionName;
        }

        public void setOrderPersionName(String orderPersionName) {
            this.orderPersionName = orderPersionName;
        }

        public Long getOrderPersionId() {
            return orderPersionId;
        }

        public void setOrderPersionId(Long orderPersionId) {
            this.orderPersionId = orderPersionId;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getContactAddress() {
            return contactAddress;
        }

        public void setContactAddress(String contactAddress) {
            this.contactAddress = contactAddress;
        }

        private String supplierName;
        private String orgName;

        private int orgId;
        private int orgTypeId;

        public int getAmountPayable() {
            return amountPayable;
        }

        public void setAmountPayable(int amountPayable) {
            this.amountPayable = amountPayable;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.orderCode);
            dest.writeInt(this.amountPayable);
            dest.writeInt(this.payStatus);
            dest.writeString(this.contactAddress);
            dest.writeValue(this.customerId);
            dest.writeString(this.customerName);
            dest.writeString(this.customerPhone);
            dest.writeValue(this.orderPersionId);
            dest.writeString(this.orderPersionName);
            dest.writeValue(this.supplierId);
            dest.writeInt(this.supplierTypeId);
            dest.writeString(this.supplierName);
            dest.writeString(this.orgName);
            dest.writeInt(this.orgId);
            dest.writeInt(this.orgTypeId);
        }

        public SerOrderInfoBean() {
        }

        protected SerOrderInfoBean(Parcel in) {
            this.orderCode = in.readString();
            this.amountPayable = in.readInt();
            this.payStatus = in.readInt();
            this.contactAddress = in.readString();
            this.customerId = (Long) in.readValue(Long.class.getClassLoader());
            this.customerName = in.readString();
            this.customerPhone = in.readString();
            this.orderPersionId = (Long) in.readValue(Long.class.getClassLoader());
            this.orderPersionName = in.readString();
            this.supplierId = (Long) in.readValue(Long.class.getClassLoader());
            this.supplierTypeId = in.readInt();
            this.supplierName = in.readString();
            this.orgName = in.readString();
            this.orgId = in.readInt();
            this.orgTypeId = in.readInt();
        }

        public static final Creator<SerOrderInfoBean> CREATOR = new Creator<SerOrderInfoBean>() {
            @Override
            public SerOrderInfoBean createFromParcel(Parcel source) {
                return new SerOrderInfoBean(source);
            }

            @Override
            public SerOrderInfoBean[] newArray(int size) {
                return new SerOrderInfoBean[size];
            }
        };
    }

    public static class SerOrderInfoDetailBeanListBean  extends BaseOrderCate implements Parcelable {
        @Override
        public String toString() {
            return "SerOrderInfoDetailBeanListBean{" +
                    "serOrderInfoDetail=" + serOrderInfoDetail +
                    '}';
        }

        /**
         * orderCode : 110020161113092212786
         * packId : 25
         * packName : 中心修脚单次包
         * levelId : 1
         * price : 38
         * serNum : 1
         * pictureUrl : /LelaoHuiWebApp/folder/xiujiao.jpg
         * orgTypeId : 3
         * orgName : 乐老汇罗庄养老中心
         */

        private SerOrderInfoDetailBean serOrderInfoDetail;

        public SerOrderInfoDetailBean getSerOrderInfoDetail() {
            return serOrderInfoDetail;
        }

        public void setSerOrderInfoDetail(SerOrderInfoDetailBean serOrderInfoDetail) {
            this.serOrderInfoDetail = serOrderInfoDetail;
        }

        public static class SerOrderInfoDetailBean implements Parcelable {
            private String orderCode;
            private int packageId;
            private String packageName;
            private int levelId;
            private int price;
            private int serNum;
            private String pictureUrl;
            private int orgTypeId;
            private String orgName;

            public String getOrderCode() {
                return orderCode;
            }

            public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
            }

            public int getPackageId() {
                return packageId;
            }

            public void setPackageId(int packageId) {
                this.packageId = packageId;
            }

            public String getPackageName() {
                return packageName;
            }

            public void setPackageName(String packageName) {
                this.packageName = packageName;
            }

            public int getLevelId() {
                return levelId;
            }

            public void setLevelId(int levelId) {
                this.levelId = levelId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSerNum() {
                return serNum;
            }

            public void setSerNum(int serNum) {
                this.serNum = serNum;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public int getOrgTypeId() {
                return orgTypeId;
            }

            public void setOrgTypeId(int orgTypeId) {
                this.orgTypeId = orgTypeId;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            @Override
            public String toString() {
                return "SerOrderInfoDetailBean{" +
                        "orderCode='" + orderCode + '\'' +
                        ", packId=" + packageId +
                        ", packName='" + packageName + '\'' +
                        ", levelId=" + levelId +
                        ", price=" + price +
                        ", serNum=" + serNum +
                        ", pictureUrl='" + pictureUrl + '\'' +
                        ", orgTypeId=" + orgTypeId +
                        ", orgName='" + orgName + '\'' +
                        '}';
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.orderCode);
                dest.writeInt(this.packageId);
                dest.writeString(this.packageName);
                dest.writeInt(this.levelId);
                dest.writeInt(this.price);
                dest.writeInt(this.serNum);
                dest.writeString(this.pictureUrl);
                dest.writeInt(this.orgTypeId);
                dest.writeString(this.orgName);
            }

            public SerOrderInfoDetailBean() {
            }

            protected SerOrderInfoDetailBean(Parcel in) {
                this.orderCode = in.readString();
                this.packageId = in.readInt();
                this.packageName = in.readString();
                this.levelId = in.readInt();
                this.price = in.readInt();
                this.serNum = in.readInt();
                this.pictureUrl = in.readString();
                this.orgTypeId = in.readInt();
                this.orgName = in.readString();
            }

            public static final Creator<SerOrderInfoDetailBean> CREATOR = new Creator<SerOrderInfoDetailBean>() {
                @Override
                public SerOrderInfoDetailBean createFromParcel(Parcel source) {
                    return new SerOrderInfoDetailBean(source);
                }

                @Override
                public SerOrderInfoDetailBean[] newArray(int size) {
                    return new SerOrderInfoDetailBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.serOrderInfoDetail, flags);
        }

        public SerOrderInfoDetailBeanListBean() {
        }

        protected SerOrderInfoDetailBeanListBean(Parcel in) {
            this.serOrderInfoDetail = in.readParcelable(SerOrderInfoDetailBean.class.getClassLoader());
        }

        public static final Creator<SerOrderInfoDetailBeanListBean> CREATOR = new Creator<SerOrderInfoDetailBeanListBean>() {
            @Override
            public SerOrderInfoDetailBeanListBean createFromParcel(Parcel source) {
                return new SerOrderInfoDetailBeanListBean(source);
            }

            @Override
            public SerOrderInfoDetailBeanListBean[] newArray(int size) {
                return new SerOrderInfoDetailBeanListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.serOrderInfo, flags);
        dest.writeList(this.serOrderInfoDetailBeanList);
    }

    public SerOrderInfoData() {
    }

    protected SerOrderInfoData(Parcel in) {
        this.serOrderInfo = in.readParcelable(SerOrderInfoBean.class.getClassLoader());
        this.serOrderInfoDetailBeanList = new ArrayList<SerOrderInfoDetailBeanListBean>();
        in.readList(this.serOrderInfoDetailBeanList, SerOrderInfoDetailBeanListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SerOrderInfoData> CREATOR = new Parcelable.Creator<SerOrderInfoData>() {
        @Override
        public SerOrderInfoData createFromParcel(Parcel source) {
            return new SerOrderInfoData(source);
        }

        @Override
        public SerOrderInfoData[] newArray(int size) {
            return new SerOrderInfoData[size];
        }
    };
}
