package dq.lelaohui.com.lelaohuipad.bean;

import java.io.Serializable;
import java.util.List;

public class SerOrderInfoDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SerOrderInfoDetail serOrderInfoDetail;
	private SerProPackageOrder serProPackageOrder;// 此对象客户端不需要传数据
	private List<SerProPackDetailOrder> serProPackDetailOrderList;// 客户端不需要传数据

	public SerOrderInfoDetail getSerOrderInfoDetail() {
		return serOrderInfoDetail;
	}

	public void setSerOrderInfoDetail(SerOrderInfoDetail serOrderInfoDetail) {
		this.serOrderInfoDetail = serOrderInfoDetail;
	}

	public SerProPackageOrder getSerProPackageOrder() {
		return serProPackageOrder;
	}

	public void setSerProPackageOrder(SerProPackageOrder serProPackageOrder) {
		this.serProPackageOrder = serProPackageOrder;
	}

	public List<SerProPackDetailOrder> getSerProPackDetailOrderList() {
		return serProPackDetailOrderList;
	}

	public void setSerProPackDetailOrderList(
			List<SerProPackDetailOrder> serProPackDetailOrderList) {
		this.serProPackDetailOrderList = serProPackDetailOrderList;
	}
}
