package com.zqrc.sems.servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务
 * @author 李志飞
 *
 */
public class SocketServer {
	private static volatile SocketServer socketServer;
	private static Read read;
	private static ServerSocket server;
	//	private ArrayList<Socket>sockets=new ArrayList<Socket>();

	/**
	 * 单例监听器
	 * @return
	 */
	public static SocketServer getInstance(){
		if(socketServer==null){
			 synchronized (SocketServer.class){
				 if(socketServer==null){
					 socketServer=new SocketServer();
				 }
			 }
		}
		return socketServer;
	}
	
	private SocketServer() {
		try {
			server=new ServerSocket(8888);
			System.out.println("服务开启。。。");
			//加入socket
			while(true){
				Socket socket=server.accept();
				System.out.println("IP："+socket.getInetAddress()+" 已连接");
				read=new Read(socket);
			}

		} catch (Exception e) {
			closed();
		}
	}
	
	public static void closed() {
		try {
			read.closed();
			server.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		new SocketServer();
//	}
}
