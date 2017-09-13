package com.yf.pet.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Description: 宠物项目用户模块启动类</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/12
 */
public class PetUserApplication {
    private static final Logger log = LoggerFactory.getLogger(PetUserApplication.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        synchronized (PetUserApplication.class) {
            while (true) {
                try {
                    log.warn("启动宠物用户服务");
                    PetUserApplication.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
