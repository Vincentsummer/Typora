package IOTest;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread server = new Thread(new serverThread());
		int n = 150;
		server.start();
		Thread[] pool = new Thread[n];
		for (int i = 0; i < n; ++i) {
			pool[i] = new Thread(new clientThread(i+1));
			pool[i].start();
		}
	}
}

class serverThread implements Runnable{
	EchoServer echoServer = new EchoServer_NIO();
	@Override
	public void run() {
		try {
			echoServer.testServer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class clientThread implements Runnable{
	private EchoClient_BIO echoClient;
	public clientThread(int idx) {
		echoClient = new EchoClient_BIO(idx);
	}

	@Override
	public void run() {
		try {
			echoClient.testClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}