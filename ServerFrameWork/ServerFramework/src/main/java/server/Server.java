package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;



public class Server implements Runnable{
	
	private Logger logger = Logger.getLogger(Server.class);
	private String port;
	
	public Server(String port){
		this.port = port;
	}
	
	public void run() {
		try{
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
			logger.info("Port# " + serverSocket.getLocalPort() + " has been opened..");
			Socket client = serverSocket.accept();
			logger.info("Client " + client.getInetAddress() + " has been connected: ");
			String str = "";
			
			while(true){				
				BufferedReader in = null;
				try{
					if(client != null && !client.isClosed()){
						in = new BufferedReader(new InputStreamReader(client.getInputStream()));
						str = in.readLine();
						if(str != null){
							if(str.equals("EXIT")){
								logger.info(client.getInetAddress() + " has been disconnected");
								client.close();
								client = null;
							}
							else if(str.equals("")){}
							else {
								logger.info("Received: " + str + " [" + client.getInetAddress() + "]");
							}
						}
					} 
				}
				catch (Exception e){
					e.printStackTrace();
					logger.error(e.getMessage());
				} finally{
					serverSocket.close();
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	}

}
