package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;
import java.util.List;


/**
 * 库存数据bean
 * @author Administrator
 *
 */
public class SerOrderStoreBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SerOrderInfo serOrderInfo;
	
	private List<SerOrderInfoDetailBean> serOrderInfoDetailBeanList;
	
	public SerOrderInfo getSerOrderInfo() {
		return serOrderInfo;
	}
	public void setSerOrderInfo(SerOrderInfo serOrderInfo) {
		this.serOrderInfo = serOrderInfo;
	}
	public List<SerOrderInfoDetailBean> getSerOrderInfoDetailBeanList() {
		return serOrderInfoDetailBeanList;
	}
	public void setSerOrderInfoDetailBeanList(
			List<SerOrderInfoDetailBean> serOrderInfoDetailBeanList) {
		this.serOrderInfoDetailBeanList = serOrderInfoDetailBeanList;
	}
	

}