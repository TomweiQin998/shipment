package com.infosys.shipment.pojo;

import java.io.Serializable;

/**
 * 
 * @author Tomwei_Qin
 *
 */
public class Goods implements Serializable {

	private double totalWeight;// 待运货物总吨数

	private double increateRootQuantity;// 减少根数量

	private double decreaseRootQuantity;// 添加根数量

	private int SplitOrMerge;// 1.拆分装运|2.合并装运

	private int version; // 货运批次

	public Goods() {
	}

	public Goods(double totalWeight, double increateRootQuantity, double decreaseRootQuantity, int splitOrMerge,
			int version) {
		super();
		this.totalWeight = totalWeight;
		this.increateRootQuantity = increateRootQuantity;
		this.decreaseRootQuantity = decreaseRootQuantity;
		SplitOrMerge = splitOrMerge;
		this.version = version;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public double getIncreateRootQuantity() {
		return increateRootQuantity;
	}

	public void setIncreateRootQuantity(double increateRootQuantity) {
		this.increateRootQuantity = increateRootQuantity;
	}

	public double getDecreaseRootQuantity() {
		return decreaseRootQuantity;
	}

	public void setDecreaseRootQuantity(double decreaseRootQuantity) {
		this.decreaseRootQuantity = decreaseRootQuantity;
	}

	public int getSplitOrMerge() {
		return SplitOrMerge;
	}

	public void setSplitOrMerge(int splitOrMerge) {
		SplitOrMerge = splitOrMerge;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	};

}
