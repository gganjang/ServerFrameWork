package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client4Test {

	public static void main(String[] args) {
		String addr = "localhost";
		int port = 4321;
		
		try {
			Socket socket = new Socket(addr, port);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			Scanner sc = new Scanner(System.in);
			while(sc.hasNext()){
				String msg = sc.nextLine();
				if(msg.equals("EXIT")){
					out.write("EXIT");
					out.flush();
					socket.close();
					break;
				}
				else{
					out.write(msg+"\n");
					out.flush();
				}
			}
			sc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
