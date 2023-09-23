package com.maoyan.loveblindbox;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoveBlindBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveBlindBoxApplication.class, args);
        System.out.println("启动成功，SaToken的配置如下：" + SaManager.getConfig());
    }

}
