package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/9.
 */

public class FoodOrederData  extends BaseBean implements Parcelable {


    /**
     * total : 12
     * supplierInfo : [{"supplierId":"10000","supplierName":"罗庄餐厅A","supplierType":4,"supplierAmt":12,"supplierQty":1,"distrPrice":0,"product":[{"cateId":0,"cateName":"","mealTime":3,"mealType":0,"proId":"10147","proName":"京酱肉丝","proNum":"1","proPic":"","proPrice":12,"remark":"","supplierId":"10000","supplierName":"罗庄餐厅A","supplierType":"4"}]}]
     */

    private int total;
    private List<SupplierInfoBean> supplierInfo;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SupplierInfoBean> getSupplierInfo() {
        return supplierInfo;
    }

    public void setSupplierInfo(List<SupplierInfoBean> supplierInfo) {
        this.supplierInfo = supplierInfo;
    }

    public static class SupplierInfoBean extends BaseBean implements Parcelable {
        /**
         * supplierId : 10000
         * supplierName : 罗庄餐厅A
         * supplierType : 4
         * supplierAmt : 12
         * supplierQty : 1
         * distrPrice : 0
         * product : [{"cateId":0,"cateName":"","mealTime":3,"mealType":0,"proId":"10147","proName":"京酱肉丝","proNum":"1","proPic":"","proPrice":12,"remark":"","supplierId":"10000","supplierName":"罗庄餐厅A","supplierType":"4"}]
         */

        private String supplierId;
        private String supplierName;
        private int supplierType;
        private int supplierAmt;
        private int supplierQty;
        private int distrPrice;
        private List<ProductBean> product;

        protected SupplierInfoBean(Parcel in) {
            supplierId = in.readString();
            supplierName = in.readString();
            supplierType = in.readInt();
            supplierAmt = in.readInt();
            supplierQty = in.readInt();
            distrPrice = in.readInt();
            product = in.createTypedArrayList(ProductBean.CREATOR);
        }

        public static final Creator<SupplierInfoBean> CREATOR = new Creator<SupplierInfoBean>() {
            @Override
            public SupplierInfoBean createFromParcel(Parcel in) {
                return new SupplierInfoBean(in);
            }

            @Override
            public SupplierInfoBean[] newArray(int size) {
                return new SupplierInfoBean[size];
            }
        };

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public int getSupplierType() {
            return supplierType;
        }

        public void setSupplierType(int supplierType) {
            this.supplierType = supplierType;
        }

        public int getSupplierAmt() {
            return supplierAmt;
        }

        public void setSupplierAmt(int supplierAmt) {
            this.supplierAmt = supplierAmt;
        }

        public int getSupplierQty() {
            return supplierQty;
        }

        public void setSupplierQty(int supplierQty) {
            this.supplierQty = supplierQty;
        }

        public int getDistrPrice() {
            return distrPrice;
        }

        public void setDistrPrice(int distrPrice) {
            this.distrPrice = distrPrice;
        }

        public List<ProductBean> getProduct() {
            return product;
        }

        public void setProduct(List<ProductBean> product) {
            this.product = product;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(supplierId);
            parcel.writeString(supplierName);
            parcel.writeInt(supplierType);
            parcel.writeInt(supplierAmt);
            parcel.writeInt(supplierQty);
            parcel.writeInt(distrPrice);
            parcel.writeTypedList(product);
        }

        public static class ProductBean  extends BaseBean implements Parcelable {
            /**
             * cateId : 0
             * cateName :
             * mealTime : 3
             * mealType : 0
             * proId : 10147
             * proName : 京酱肉丝
             * proNum : 1
             * proPic :
             * proPrice : 12
             * remark :
             * supplierId : 10000
             * supplierName : 罗庄餐厅A
             * supplierType : 4
             */

            private int cateId;
            private String cateName;
            private int mealTime;
            private int mealType;
            private String proId;
            private String proName;
            private String proNum;
            private String proPic;
            private int proPrice;
            private String remark;
            private String supplierId;
            private String supplierName;
            private String supplierType;

            public int getCateId() {
                return cateId;
            }

            public void setCateId(int cateId) {
                this.cateId = cateId;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public int getMealTime() {
                return mealTime;
            }

            public void setMealTime(int mealTime) {
                this.mealTime = mealTime;
            }

            public int getMealType() {
                return mealType;
            }

            public void setMealType(int mealType) {
                this.mealType = mealType;
            }

            public String getProId() {
                return proId;
            }

            public void setProId(String proId) {
                this.proId = proId;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public String getProNum() {
                return proNum;
            }

            public void setProNum(String proNum) {
                this.proNum = proNum;
            }

            public String getProPic() {
                return proPic;
            }

            public void setProPic(String proPic) {
                this.proPic = proPic;
            }

            public int getProPrice() {
                return proPrice;
            }

            public void setProPrice(int proPrice) {
                this.proPrice = proPrice;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(String supplierId) {
                this.supplierId = supplierId;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public String getSupplierType() {
                return supplierType;
            }

            public void setSupplierType(String supplierType) {
                this.supplierType = supplierType;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.cateId);
                dest.writeString(this.cateName);
                dest.writeInt(this.mealTime);
                dest.writeInt(this.mealType);
                dest.writeString(this.proId);
                dest.writeString(this.proName);
                dest.writeString(this.proNum);
                dest.writeString(this.proPic);
                dest.writeInt(this.proPrice);
                dest.writeString(this.remark);
                dest.writeString(this.supplierId);
                dest.writeString(this.supplierName);
                dest.writeString(this.supplierType);
            }

            public ProductBean() {
            }

            protected ProductBean(Parcel in) {
                this.cateId = in.readInt();
                this.cateName = in.readString();
                this.mealTime = in.readInt();
                this.mealType = in.readInt();
                this.proId = in.readString();
                this.proName = in.readString();
                this.proNum = in.readString();
                this.proPic = in.readString();
                this.proPrice = in.readInt();
                this.remark = in.readString();
                this.supplierId = in.readString();
                this.supplierName = in.readString();
                this.supplierType = in.readString();
            }

            public static final Creator<ProductBean> CREATOR = new Creator<ProductBean>() {
                @Override
                public ProductBean createFromParcel(Parcel source) {
                    return new ProductBean(source);
                }

                @Override
                public ProductBean[] newArray(int size) {
                    return new ProductBean[size];
                }
            };
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeList(this.supplierInfo);
    }

    public FoodOrederData() {
    }

    protected FoodOrederData(Parcel in) {
        this.total = in.readInt();
        this.supplierInfo = new ArrayList<SupplierInfoBean>();
        in.readList(this.supplierInfo, SupplierInfoBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FoodOrederData> CREATOR = new Parcelable.Creator<FoodOrederData>() {
        @Override
        public FoodOrederData createFromParcel(Parcel source) {
            return new FoodOrederData(source);
        }

        @Override
        public FoodOrederData[] newArray(int size) {
            return new FoodOrederData[size];
        }
    };
}
