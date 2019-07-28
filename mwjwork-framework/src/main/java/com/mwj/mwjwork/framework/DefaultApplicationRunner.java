package com.mwj.mwjwork.framework;

import cn.hutool.system.OsInfo;
import com.mwj.mwjwork.common.constant.Const;
import com.mwj.mwjwork.common.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Meng Wei Jin
 * @description 应用启动初始化
 * 注解@Order如果多个自定义ApplicationRunner，用来标明执行顺序，从小到大加载
 **/
@Component
@Order(1)
@Slf4j
public class DefaultApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(new OsInfo().isWindows()){
            // 从应用程序jar里面的classpath下的files目录，拷贝 7z等文件到应用程序jar包同级目录
            FileUtil.copyJarDirectoryToDirectory("files", Const.PROJECT_PATH);
            log.info("Copy classpath:files to " + Const.PROJECT_PATH + File.separator + "files finished!");
        }
    }

}
