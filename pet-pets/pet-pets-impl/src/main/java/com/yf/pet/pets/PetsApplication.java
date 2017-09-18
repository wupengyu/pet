package com.yf.pet.pets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Description:</p>
 * <pre></pre>
 * <p>Company: 远峰科技</p>
 *
 * @author wupengyu
 * @date 2017/9/16
 */
public class PetsApplication {

    private static final Logger log = LoggerFactory.getLogger(PetsApplication.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        synchronized (PetsApplication.class) {
            while (true) {
                try {
                    log.warn("启动宠物服务");
                    PetsApplication.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
