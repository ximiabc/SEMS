package com.zqrc.sems.servers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 服务监听器
 * @author 李志飞
 *
 */
public class ServerListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		SocketServer.closed();
	}

	public void contextInitialized(ServletContextEvent sce) {
		SocketServer.getInstance();
	}

	
	
}
