package com.mengweijin.mwjwork.sample.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mengweijin.mwjwork.framework.util.lambda.LambdaWrapper;
import com.mengweijin.mwjwork.jpa.service.BaseServiceImpl;
import com.mengweijin.mwjwork.sample.system.entity.User;
import com.mengweijin.mwjwork.sample.system.enums.Role;
import com.mengweijin.mwjwork.sample.system.repository.UserRepository;
import com.mengweijin.mwjwork.sample.system.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-29 21:46
 **/
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public List<User> findAllByQueryDsl(User user) {
        return userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();

            if (StrUtil.isNotEmpty(user.getName())) {
                Path<String> name = root.get(LambdaWrapper.getFieldName(User::getName));
                list.add(criteriaBuilder.equal(name, user.getName()));
            }

            Path<Integer> age = root.get(LambdaWrapper.getFieldName(User::getAge));
            list.add(criteriaBuilder.isNull(age));

            if (user.getRole() != null) {
                Path<Role> path = root.get(LambdaWrapper.getFieldName(User::getRole));
                list.add(criteriaBuilder.equal(path, user.getRole()));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        });
    }

    @Override
    public List<Object> findStudentAddress(String userName, String cityName) {
        String sql = "select u.*, a.country, a.province, a.city, a.house_number " +
                "from user u, address a " +
                "where u.address_id = a.id and u.name = :name and a.city = :city";
        Map<String, Serializable> paramMap = new HashMap<>(2);
        paramMap.put("name", userName);
        paramMap.put("city", cityName);
        return userRepository.findByNativeSQL(sql, paramMap);
    }

}
