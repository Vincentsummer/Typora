package IOTest;

/**
 * 同步阻塞IO服务端 Test
 * **/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.jupiter.api.Test;

public class EchoServer_SBIO extends EchoServer{
	@Test
	public void testServer() throws IOException {
		// 开启一个ServerSocketChannel, 监听TCP连接
		ServerSocketChannel server = ServerSocketChannel.open();
		// 将服务器绑定到8080端口
		server.bind(new InetSocketAddress(8080));
		// 不断接收客户端的请求，并且进行处理
		while(true) {
			// 接收客户端请求，如果没有请求到来，则阻塞
			SocketChannel channel = server.accept();
			
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			channel.read(buffer);		// 读取客户端请求Channel中的数据
			handle(buffer);			// 处理客户端请求channel的数据
			response(channel); 	// 往客户端channel中写入数据，以返回响应
		}
	}
} 
