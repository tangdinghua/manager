package com.trip.base.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextTool implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    //static ApplicationContext context = new ClassPathXmlApplicationContext("springmvc.xml");

    private static Logger logger = Logger.getLogger(SpringContextTool.class);

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanname, Class<T> c) {
        T tt = (T) applicationContext.getBean(beanname);
        return tt;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    //
    //public static <T> T getTestBean(String beanname,Class<T> c){
    //// T tt = (T)context.getBean(beanname);
    /// return tt;
//}

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringContextTool.applicationContext != null) {
            logger
                    .warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                            + SpringContextTool.applicationContext);
        }
        SpringContextTool.applicationContext = applicationContext; // NOSONAR
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        logger.debug("清除SpringContextHolder中的ApplicationContext:"
                + applicationContext);
        applicationContext = null;
    }

}