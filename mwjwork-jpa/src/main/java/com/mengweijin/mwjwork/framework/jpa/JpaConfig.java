package com.mengweijin.mwjwork.framework.jpa;

import com.mengweijin.mwjwork.framework.jpa.repository.SimpleBaseJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Meng Wei Jin
 * @description
 * @EnableJpaRepositories 一定要配置basePackages，或者在启动类上配置
 * @date Create in 2019-08-12 19:58
 **/
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = SimpleBaseJpaRepository.class, basePackages = {"com.mengweijin"})
public class JpaConfig {

    /**
     * 自动填充 @CreatedBy， @LastModifiedBy
     * @return
     */
    @Bean
    public AuditorAware getAuditorAware(){
        return new AuditorAwareImpl();
    }
}
