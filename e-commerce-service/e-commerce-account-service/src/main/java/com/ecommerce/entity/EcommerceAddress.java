package com.ecommerce.entity;

import com.ecommerce.account.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <h1> 用户地址表实体内定义 user address table entity </h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_ecommerce_address")
public class EcommerceAddress {

    /** 自增主键 Generated identity key */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** user id */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 用户名  user name */
    @Column(name = "username", nullable = false)
    private String username;

    /** 电话 phone number*/
    @Column(name = "phone", nullable = false)
    private String phone;

    /** 省 province */
    @Column(name = "province", nullable = false)
    private String province;

    /** 市 city */
    @Column(name = "city", nullable = false)
    private String city;

    /** 详细地址 detailed address*/
    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    /** 创建时间 created time*/
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 更新时间 updated time*/
    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * <h2>根据 userId + AddressItem 得到 EcommerceAddress obj
     *  Use userId and AddressItem to get EcommerceAddress object
     * </h2>
     * */
    public static EcommerceAddress to(Long userId, AddressInfo.AddressItem addressItem) {

        EcommerceAddress ecommerceAddress = new EcommerceAddress();

        ecommerceAddress.setUserId(userId);
        ecommerceAddress.setUsername(addressItem.getUsername());
        ecommerceAddress.setPhone(addressItem.getPhone());
        ecommerceAddress.setProvince(addressItem.getProvince());
        ecommerceAddress.setCity(addressItem.getCity());
        ecommerceAddress.setAddressDetail(addressItem.getAddressDetail());

        return ecommerceAddress;
    }

    /**
     * <h2>将 EcommerceAddress 对象转成 AddressInfo</h2>
     * */
    public AddressInfo.AddressItem toAddressItem() {

        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();

        addressItem.setId(this.id);
        addressItem.setUsername(this.username);
        addressItem.setPhone(this.phone);
        addressItem.setProvince(this.province);
        addressItem.setCity(this.city);
        addressItem.setAddressDetail(this.addressDetail);
        addressItem.setCreateTime(this.createTime);
        addressItem.setUpdateTime(this.updateTime);

        return addressItem;
    }
}
