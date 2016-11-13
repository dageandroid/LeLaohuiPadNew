package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZTF on 2016/11/13.
 */

public class SerOrderInfoData implements Parcelable {
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
     * serOrderInfoDetail : {"orderCode":"110020161113092212786","packageId":25,"packageName":"中心修脚单次包","levelId":1,"price":38,"serNum":1,"pictureUrl":"/LelaoHuiWebApp/folder/xiujiao.jpg","orgTypeId":3,"orgName":"乐老汇罗庄养老中心"}
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

    public static class SerOrderInfoBean implements Parcelable {
        private String orderCode;
        private int amountPayable;
        private int payStatus;

        public String getOrderCode() {
            return orderCode;
        }

        @Override
        public String toString() {
            return "SerOrderInfoBean{" +
                    "orderCode='" + orderCode + '\'' +
                    ", amountPayable=" + amountPayable +
                    ", payStatus=" + payStatus +
                    '}';
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getAmountPayable() {
            return amountPayable;
        }

        public void setAmountPayable(int amountPayable) {
            this.amountPayable = amountPayable;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
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
        }

        public SerOrderInfoBean() {
        }

        protected SerOrderInfoBean(Parcel in) {
            this.orderCode = in.readString();
            this.amountPayable = in.readInt();
            this.payStatus = in.readInt();
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

    public static class SerOrderInfoDetailBeanListBean {
        /**
         * orderCode : 110020161113092212786
         * packageId : 25
         * packageName : 中心修脚单次包
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

        public static class SerOrderInfoDetailBean {
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
                        ", packageId=" + packageId +
                        ", packageName='" + packageName + '\'' +
                        ", levelId=" + levelId +
                        ", price=" + price +
                        ", serNum=" + serNum +
                        ", pictureUrl='" + pictureUrl + '\'' +
                        ", orgTypeId=" + orgTypeId +
                        ", orgName='" + orgName + '\'' +
                        '}';
            }
        }
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
