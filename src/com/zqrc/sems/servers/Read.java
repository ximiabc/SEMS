package com.zqrc.sems.servers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 数据流的读入
 * @author 李志飞
 *
 */
public class Read implements Runnable{
	private Socket socket;
	private Thread thread;
//	写入流相关
	private InputStream in;
//	private BufferedReader reader;
	private DataInputStream din;
	/**
	 * 数据读入流
	 * @param socket
	 */
	public Read(Socket socket) {
		if(socket!=null){
			this.socket=socket;
			thread=new Thread(this);
			thread.start();
		}else{
			System.out.println("Socket is Null!!!");
		}
	}

	/**
	 * 线程运行核心
	 */
	public void run() {
		try {
			in=socket.getInputStream();
//			reader=new BufferedReader(new InputStreamReader(in));
			din = new DataInputStream(in);
			String str="";
			System.out.println("=======");
//			while(!(str=reader.readLine()).equals("exit")){
//				System.out.println(str);//显示数据
//			}
			
			while (true) {//创建一个流套接字并将其连接到指定主机上的指定端口号  
					byte[] buffer= new byte[din.available()];
					if(buffer.length != 0){
						din.read(buffer);// 读取缓冲区
						System.out.println(buffer.length+"："+new String(buffer));
					}
			}
		} catch (IOException e) {
			closed();
		}
	}
	
	/**
	 * 关闭所有
	 */
	public void closed() {
		try {
//			reader.close();
			din.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
