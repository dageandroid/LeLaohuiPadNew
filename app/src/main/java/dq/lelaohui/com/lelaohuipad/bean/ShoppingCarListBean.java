package dq.lelaohui.com.lelaohuipad.bean;

import android.text.TextUtils;

import java.io.Serializable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;

/**
 * 购物车商品显示bean
 * @author ZTF
 *
 */
public class ShoppingCarListBean  implements Serializable{
	public String proName;
	public double proPrice;
	public int proNum;
	public int proId;
	public  int id;
	public int posion;
	public String uniqueKey;
	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}

	public String getProDetail() {
		return proDetail;
	}

	public String proDetail;
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public int getProNum() {
		return proNum;
	}
	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public BaseBean getBean() {
		return bean;
	}

	public void setBean(BaseBean bean) {
		this.bean = bean;
	}

	private BaseBean bean=null;
	public ShoppingCarListBean(){

	}
	public ShoppingCarListBean(String proName, double proPrice, int proNum,int proId) {
		this.proName=proName;
		this.proPrice=proPrice;
		this.proNum=proNum;
		this.proId=proId;
	}
	public String getKey(){
		if (bean != null&&!TextUtils.isEmpty(bean.getUnineqKey())) {
			uniqueKey= bean.getUnineqKey();
		}else{
			uniqueKey=this.proName+"_"+proId+"_"+proPrice;
		}
		return uniqueKey;
	}
	@Override
	public boolean equals(Object obj) {
		ShoppingCarListBean carListBean= (ShoppingCarListBean) obj;
		boolean isequals=getKey().equals(carListBean.getKey());
		System.out.println(isequals);
		return isequals;
	}

	@Override
	public String toString() {
		return "ShoppingCarListBean{" +
				"proName='" + proName + '\'' +
				", proPrice=" + proPrice +
				", proNum=" + proNum +
				", proId=" + proId +
				", id=" + id +
				", posion=" + posion +
				", proDetail='" + proDetail + '\'' +
				", bean=" + bean +
				'}';
	}
}
