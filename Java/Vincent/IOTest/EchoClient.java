package IOTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public abstract class EchoClient {
	public abstract void testClient() throws IOException, InterruptedException;
	
	void request(SocketChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap("Hello, I'm client. ".getBytes());
		// 将客户端请求数据写入到Channel中
		channel.write(buffer);
	}
	
	void handleResponse(SocketChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 读取服务端响应数据
		channel.read(buffer);
		buffer.flip();	// 切换ByteBuffer模式
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		System.out.println("Client receives message: ");
		System.out.println(new String(bytes, StandardCharsets.UTF_8));
	}
}
