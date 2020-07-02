package com.github.mengweijin.quickboot.framework.web;

import com.github.mengweijin.quickboot.framework.util.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;

/**
 * @author Meng Wei Jin
 * @description
 *
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册视图控制器
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/home").setViewName("index");
    }

    /**
     * 注册参数解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(SpringUtils.getBean(PagerArgumentResolver.class));
    }

    /**
     * I18n国际化
     * <p>
     * SessionLocaleResolver保存客户的Locale到HttpSession对象中，并且支持获取和修改。
     * 它提供了在cookie中保存Locale状态的一个很好的替代方案。
     * 与CookieLocaleResolver一样，如果在会话中没有找到Locale，该类将回退调用HttpServletRequest的getLocale()方法。
     * <p>
     * 建议不要设置默认语言，如果这里设置了默认语言，则无法根据浏览器语言来实现国际化，会始终为默认语言，国际化不生效。
     * 如果这里没有设置默认语言，页面访问时（thymeleaf）url参数需要添加 lang=zh_CN或lang=en_US这样的参数来实现国际化
     * thymeleaf中取国际化资源：<a th:text="#{key}"></a>
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言,
        //slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
