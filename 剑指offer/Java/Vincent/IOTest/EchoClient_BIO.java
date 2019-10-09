package IOTest;
/**
 * 同步/异步 阻塞IO客户端  Test
 * **/
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import org.junit.jupiter.api.Test;

public class EchoClient_BIO extends EchoClient{
	private int idx;
	public EchoClient_BIO(int idx) {
		this.idx = idx;
	}
	@Test
	public void testClient() throws IOException {
		// 开启一个客户端SocketChannel
		SocketChannel channel = SocketChannel.open();
		// 连接服务器ip和端口
		channel.connect(new InetSocketAddress("127.0.0.1", 8080));
		
		request(channel);		// 发送客户端请求
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handleResponse(channel);  // 处理服务端响应
	}
}
 