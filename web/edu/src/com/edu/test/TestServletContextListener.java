package com.edu.test;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;


public class TestServletContextListener implements ServletContextListener {
	public TestServletContextListener() {
		System.out.println("TestServletContextListener 按眉 积己");
	}

	public void contextInitialized(ServletContextEvent e) {
		System.out.println("ServletContext 按眉 檬扁拳");
	}

	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("ServletContext 按眉 秦力");
	}
}
