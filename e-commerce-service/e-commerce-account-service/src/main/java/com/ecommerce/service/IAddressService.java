package com.ecommerce.service;

import com.ecommerce.account.AddressInfo;
import com.ecommerce.common.TableId;

/**
 * <h1>用户地址相关服务接口定义</h1>
 * */
public interface IAddressService {

    /**
     * <h2>创建用户地址信息 create user address info</h2>
     * */
    TableId createAddressInfo(AddressInfo addressInfo);

    /**
     * <h2>获取当前登录的用户地址信息 get the current log-in user's address info</h2>
     * 可以直接从 accessContext 中直接获取当前用户信息
     * */
    AddressInfo getCurrentAddressInfo();

    /**
     * <h2>通过 id 获取用户地址信息, id 是 EcommerceAddress 表的主键
     * get address info by user id, while id is the primary key of EcommerceAddress
     * </h2>
     * */
    AddressInfo getAddressInfoById(Long id);

    /**
     * <h2>通过 TableId 批量获取用户地址信息</h2>
     * */
    AddressInfo getAddressInfoByTableId(TableId tableId);
}
