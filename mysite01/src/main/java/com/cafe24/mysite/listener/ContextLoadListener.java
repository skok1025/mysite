package com.cafe24.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextLoadListener
 *
 */
//@WebListener
public class ContextLoadListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String contextConfigLocation = servletContextEvent.
											getServletContext().getInitParameter("contextConfigLocation");
		
		System.out.println("Container Starts... : "+contextConfigLocation);

	}

}
