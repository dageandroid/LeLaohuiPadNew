package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/11/29.
 */

public class SerSubescribeData extends BaseBean implements Parcelable {

    /**
     * orderId : 316
     * stockList : [{"packageName":"足疗套餐","serStockDetailList":[{"serStockDetailId":7634,"serviceName":"足疗","ruleStr":"每天1次","totalNum":15,"currentNum":15,"packageStatus":0,"execDates":"","execNumDay":1,"packDetailId":229,"isEnable":1}]}]
     */
    @Expose
    private int orderId;
    @Expose
    private List<StockListBean> stockList;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<StockListBean> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockListBean> stockList) {
        this.stockList = stockList;
    }

    @Override
    public String toString() {
        return "SerSubescribeData{" +
                "orderId=" + orderId +
                ", stockList=" + stockList +
                '}';
    }

    public static class StockListBean extends BaseBean implements Parcelable{
        @Override
        public String toString() {
            return "StockListBean{" +
                    "packageName='" + packageName + '\'' +
                    ", serStockDetailList=" + serStockDetailList +
                    '}';
        }

        /**
         * packageName : 足疗套餐
         * serStockDetailList : [{"serStockDetailId":7634,"serviceName":"足疗","ruleStr":"每天1次","totalNum":15,"currentNum":15,"packageStatus":0,"execDates":"","execNumDay":1,"packDetailId":229,"isEnable":1}]
         */
        @Expose
        private String packageName;
        @Expose
        private List<SerStockDetailListBean> serStockDetailList;

        protected StockListBean(Parcel in) {
            packageName = in.readString();
            serStockDetailList = in.createTypedArrayList(SerStockDetailListBean.CREATOR);
        }

        public static final Creator<StockListBean> CREATOR = new Creator<StockListBean>() {
            @Override
            public StockListBean createFromParcel(Parcel in) {
                return new StockListBean(in);
            }

            @Override
            public StockListBean[] newArray(int size) {
                return new StockListBean[size];
            }
        };

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public List<SerStockDetailListBean> getSerStockDetailList() {
            return serStockDetailList;
        }

        public void setSerStockDetailList(List<SerStockDetailListBean> serStockDetailList) {
            this.serStockDetailList = serStockDetailList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(packageName);
            parcel.writeTypedList(serStockDetailList);
        }

        public static class SerStockDetailListBean extends BaseBean implements Parcelable{
            @Override
            public String toString() {
                return "SerStockDetailListBean{" +
                        "serStockDetailId=" + serStockDetailId +
                        ", serviceName='" + serviceName + '\'' +
                        ", ruleStr='" + ruleStr + '\'' +
                        ", totalNum=" + totalNum +
                        ", currentNum=" + currentNum +
                        ", packageStatus=" + packageStatus +
                        ", execDates='" + execDates + '\'' +
                        ", execNumDay=" + execNumDay +
                        ", packDetailId=" + packDetailId +
                        ", isEnable=" + isEnable +
                        '}';
            }

            /**
             * serStockDetailId : 7634
             * serviceName : 足疗
             * ruleStr : 每天1次
             * totalNum : 15
             * currentNum : 15
             * packageStatus : 0
             * execDates :
             * execNumDay : 1
             * packDetailId : 229
             * isEnable : 1
             */
            @Expose
            private int serStockDetailId;
            @Expose
            private String serviceName;
            @Expose
            private String ruleStr;
            @Expose
            private int totalNum;
            @Expose
            private int currentNum;
            @Expose
            private int packageStatus;
            @Expose
            private String execDates;
            @Expose
            private int execNumDay;
            @Expose
            private int packDetailId;
            @Expose
            private int isEnable;

            protected SerStockDetailListBean(Parcel in) {
                serStockDetailId = in.readInt();
                serviceName = in.readString();
                ruleStr = in.readString();
                totalNum = in.readInt();
                currentNum = in.readInt();
                packageStatus = in.readInt();
                execDates = in.readString();
                execNumDay = in.readInt();
                packDetailId = in.readInt();
                isEnable = in.readInt();
            }

            public static final Creator<SerStockDetailListBean> CREATOR = new Creator<SerStockDetailListBean>() {
                @Override
                public SerStockDetailListBean createFromParcel(Parcel in) {
                    return new SerStockDetailListBean(in);
                }

                @Override
                public SerStockDetailListBean[] newArray(int size) {
                    return new SerStockDetailListBean[size];
                }
            };

            public int getSerStockDetailId() {
                return serStockDetailId;
            }

            public void setSerStockDetailId(int serStockDetailId) {
                this.serStockDetailId = serStockDetailId;
            }

            public String getServiceName() {
                return serviceName;
            }

            public void setServiceName(String serviceName) {
                this.serviceName = serviceName;
            }

            public String getRuleStr() {
                return ruleStr;
            }

            public void setRuleStr(String ruleStr) {
                this.ruleStr = ruleStr;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getCurrentNum() {
                return currentNum;
            }

            public void setCurrentNum(int currentNum) {
                this.currentNum = currentNum;
            }

            public int getPackageStatus() {
                return packageStatus;
            }

            public void setPackageStatus(int packageStatus) {
                this.packageStatus = packageStatus;
            }

            public String getExecDates() {
                return execDates;
            }

            public void setExecDates(String execDates) {
                this.execDates = execDates;
            }

            public int getExecNumDay() {
                return execNumDay;
            }

            public void setExecNumDay(int execNumDay) {
                this.execNumDay = execNumDay;
            }

            public int getPackDetailId() {
                return packDetailId;
            }

            public void setPackDetailId(int packDetailId) {
                this.packDetailId = packDetailId;
            }

            public int getIsEnable() {
                return isEnable;
            }

            public void setIsEnable(int isEnable) {
                this.isEnable = isEnable;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(serStockDetailId);
                parcel.writeString(serviceName);
                parcel.writeString(ruleStr);
                parcel.writeInt(totalNum);
                parcel.writeInt(currentNum);
                parcel.writeInt(packageStatus);
                parcel.writeString(execDates);
                parcel.writeInt(execNumDay);
                parcel.writeInt(packDetailId);
                parcel.writeInt(isEnable);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderId);
        dest.writeList(this.stockList);
    }

    public SerSubescribeData() {
    }

    protected SerSubescribeData(Parcel in) {
        this.orderId = in.readInt();
        this.stockList = new ArrayList<StockListBean>();
        in.readList(this.stockList, StockListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<SerSubescribeData> CREATOR = new Parcelable.Creator<SerSubescribeData>() {
        @Override
        public SerSubescribeData createFromParcel(Parcel source) {
            return new SerSubescribeData(source);
        }

        @Override
        public SerSubescribeData[] newArray(int size) {
            return new SerSubescribeData[size];
        }
    };
}
