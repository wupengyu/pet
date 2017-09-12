/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.pet.common.cache;

/**
 * redis缓存前缀
 *
 * @author Infi
 */
public class YFRedisCachePrefix {

    /**
     * 获取redis缓存前缀
     *
     * @param productType  产品类型
     * @param productModel 业务模块
     * @return redis缓存前缀
     */
    public static String getProductAndMode(ProductType productType, ProductModel productModel) {
        return productType.toString().concat(":").concat(productModel.toString());
    }
}
