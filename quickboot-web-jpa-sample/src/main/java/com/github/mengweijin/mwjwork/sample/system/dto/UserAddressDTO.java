package com.github.mengweijin.mwjwork.sample.system.dto;

import com.github.mengweijin.mwjwork.jpa.BaseEntity;
import com.github.mengweijin.mwjwork.sample.system.entity.Address;
import com.github.mengweijin.mwjwork.sample.system.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mengweijin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAddressDTO extends BaseEntity {

    private String name;

    private Integer age;

    private Role role;

    private Long addressId;

    private Address address;

}
