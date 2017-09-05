/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

/**
 * 产品类型枚举
 * 
 * @author Infi
 */
public enum ProductType {

	/**
	 * 头盔
	 */
	COROS("c"),

	/**
	 * 手环
	 */
	BAND("b"),

	/**
	 * 宠物
	 */
	PET("p");

	private final String productType;

	private ProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return productType;
	}
}
