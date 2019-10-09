package IOTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public abstract class EchoServer {
	public abstract void testServer() throws IOException, InterruptedException;
	protected void handle(ByteBuffer buffer) {
		buffer.flip();	//写完数据之后需要切换为读取模式
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		System.out.println("Server receives message: ");
		// 打印客户端发送的数据
		System.out.println(new String(bytes, StandardCharsets.UTF_8));
	}
	
	protected void response(SocketChannel channel) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(("Hello, I'm server. It's : " + new Date()).getBytes());
		channel.write(buffer); 	// 往客户端中写入当前数据
		channel.close();
	}
}
