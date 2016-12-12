package dq.lelaohui.com.lelaohuipad.bean;

import android.os.Parcel;
import android.os.Parcelable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * Created by ZTF on 2016/12/9.
 */

public class FoodTradeNoData extends BaseBean implements Parcelable {
    /**
     * out_trade_no : 453134465336036063
     * subject : 乐老汇餐饮订单支付
     * body : 乐老汇餐饮订单支付
     * payType : 1
     * totalMoney : 22.0
     */
    private String out_trade_no;
    private String subject;
    private String body;
    private int payType;
    private String totalMoney;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.out_trade_no);
        dest.writeString(this.subject);
        dest.writeString(this.body);
        dest.writeInt(this.payType);
        dest.writeString(this.totalMoney);
    }

    public FoodTradeNoData() {
    }

    protected FoodTradeNoData(Parcel in) {
        this.out_trade_no = in.readString();
        this.subject = in.readString();
        this.body = in.readString();
        this.payType = in.readInt();
        this.totalMoney = in.readString();
    }

    public static final Parcelable.Creator<FoodTradeNoData> CREATOR = new Parcelable.Creator<FoodTradeNoData>() {
        @Override
        public FoodTradeNoData createFromParcel(Parcel source) {
            return new FoodTradeNoData(source);
        }

        @Override
        public FoodTradeNoData[] newArray(int size) {
            return new FoodTradeNoData[size];
        }
    };
}
