package com.yf.pet.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>Description: 宠物项目用户模块启动类</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/12
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class PetUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetUserApplication.class, args);
    }
}
