package com.github.mengweijin.quickboot.jpa.idgenerator;

import com.github.mengweijin.quickboot.framework.util.IdUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author Meng Wei Jin
 * @description 自定义ID生成规则
 * @date Create in 2019-07-27 22:32
 **/
public class SnowflakeIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return Long.valueOf(IdUtils.getSnowflakeId());
    }
}
