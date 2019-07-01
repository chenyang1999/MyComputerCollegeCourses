package com.moment.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    public InitListener() {
    }

    public void contextInitialized(ServletContextEvent evnet)  { 
    	evnet.getServletContext().setAttribute("path", evnet.getServletContext().getContextPath());
    }

    public void contextDestroyed(ServletContextEvent evnet)  { 
    }
	
}
