package dq.lelaohui.com.lelaohuipad.bean;

/**
 * Created by ZTF on 2016/11/11.
 */

public class ServerCartBean extends ShoppingCarListBean {
    public ServerCartBean(String proName, double proPrice, int proNum, int proId) {
        super(proName, proPrice, proNum, proId);
    }
    public ServerCartBean(String proName, double proPrice, int proNum,int proId,String proDetail) {
        this(proName,proPrice,proNum,proId);
        this.setProDetail(proDetail);
    }

}
