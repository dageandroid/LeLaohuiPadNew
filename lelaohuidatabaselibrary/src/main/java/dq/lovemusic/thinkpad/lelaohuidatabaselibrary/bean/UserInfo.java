package dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.port.NoToJson;
import org.greenrobot.greendao.DaoException;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.dao.DaoSession;

/**
 * Created by ThinkPad on 2016/10/13.
 */

public class UserInfo {

    private String helpPhone;
    private String servicePhone;


    /**
     * groupId : 32
     */
    private List<GroupIdListBean> groupIdList;
    private int centerId;
    private String centerName;
    private String userId;
    private String realName;
    private int orgId;
    private int orgType;
    private String orgName;
    private int cardNo;
    private String telephone;
    private int balance;
    private String bindCustomerStatus;
    private String serSignRecordId;
    private String signStatus;
    private String signCustomerId;




    public UserInfo() {
    }



    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getBindCustomerStatus() {
        return bindCustomerStatus;
    }

    public void setBindCustomerStatus(String bindCustomerStatus) {
        this.bindCustomerStatus = bindCustomerStatus;
    }

    public String getSerSignRecordId() {
        return serSignRecordId;
    }

    public void setSerSignRecordId(String serSignRecordId) {
        this.serSignRecordId = serSignRecordId;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getSignCustomerId() {
        return signCustomerId;
    }

    public void setSignCustomerId(String signCustomerId) {
        this.signCustomerId = signCustomerId;
    }

    public String getHelpPhone() {
        return helpPhone;
    }

    public void setHelpPhone(String helpPhone) {
        this.helpPhone = helpPhone;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "helpPhone='" + helpPhone + '\'' +
                ", servicePhone='" + servicePhone + '\'' +
                ", groupIdList=" + groupIdList +
                ", centerId=" + centerId +
                ", centerName='" + centerName + '\'' +
                ", userId='" + userId + '\'' +
                ", realName='" + realName + '\'' +
                ", orgId=" + orgId +
                ", orgType=" + orgType +
                ", orgName='" + orgName + '\'' +
                ", cardNo=" + cardNo +
                ", telephone='" + telephone + '\'' +
                ", balance=" + balance +
                ", bindCustomerStatus='" + bindCustomerStatus + '\'' +
                ", serSignRecordId='" + serSignRecordId + '\'' +
                ", signStatus='" + signStatus + '\'' +
                ", signCustomerId='" + signCustomerId + '\'' +
                '}';
    }
}