package dq.lelaohui.com.lelaohuipad.bean;
/**
 * 购物车商品显示bean
 * @author ZTF
 *
 */
public class ShoppingCarListBean {
	private String proName;
	private double proPrice;
	private int proNum;
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
	public ShoppingCarListBean(String proName, double proPrice, int proNum) {
		this.proName=proName;
		this.proPrice=proPrice;
		this.proNum=proNum;
	}

}
