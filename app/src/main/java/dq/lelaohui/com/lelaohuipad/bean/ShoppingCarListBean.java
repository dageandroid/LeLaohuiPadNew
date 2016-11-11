package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;

import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.BaseBean;
import dq.lovemusic.thinkpad.lelaohuidatabaselibrary.bean.SerInitProPack;

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
	public ShoppingCarListBean(BaseBean bean){
		this.bean=bean;
	}
	public ShoppingCarListBean(String proName, double proPrice, int proNum,int proId) {
		this.proName=proName;
		this.proPrice=proPrice;
		this.proNum=proNum;
		this.proId=proId;
	}
	public ShoppingCarListBean(SerInitProPack serInitProPack) {
		 proId=serInitProPack.getId().intValue();
		 proName=serInitProPack.getPackName();
		 proPrice=serInitProPack.getPrice();
		setBean(serInitProPack);
		proNum=1;
	}
	public String getKey(){
		return this.proName+"_"+proId+"_"+proPrice;
	}
	@Override
	public boolean equals(Object obj) {
		ShoppingCarListBean carListBean= (ShoppingCarListBean) obj;

		if(this.id==carListBean.id&&this.proName.equals(carListBean.getProName())&&this.proPrice==carListBean.proPrice){
			return true;
		}
		return false;
	}
}
