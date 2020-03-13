package com.mengweijin.mwjwork.sample.system.entity;

import com.mengweijin.mwjwork.framework.jpa.BaseEntity;
import com.mengweijin.mwjwork.sample.system.enums.UserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Meng Wei Jin
 * @description
 * @date Create in 2019-07-27 12:49
 **/

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends BaseEntity {

    @NotBlank
    @Column(length = 30)
    private String name;

    @Column(length = 3)
    private Integer age;

    @Enumerated(value = EnumType.STRING)
    @Column
    private UserType userType;
}
