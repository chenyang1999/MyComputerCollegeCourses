package com.moment.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static ApplicationContext ctx;
	
	public static void init(String path){
		ctx=new ClassPathXmlApplicationContext(path);
	}
	
	public static <T> T getBean(Class<T> type){
		return ctx.getBean(type);
	}
	
	public static Object getBean(String id){
		return ctx.getBean(id);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx=applicationContext;
		logger.info("注入webctx上下文:"+ctx);
		
	}
}
