package IOTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class EchoServer_AIO extends EchoServer{
	/**
	 * 在AIO编程中，发出一个事件（accept read write等）之后要指定事件处理类（回调函数），
	 * AIO中的事件处理类是CompletionHandler<V,A>，这个接口定义了如下两个方法，分别在异步操作成功和失败时被回调。
	 * void completed(V result, A attachment);
	 * void failed(Throwable exc, A attachment);
	 * **/
	@Override
	public void testServer() throws IOException, InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		// 创建一个异步的ServerSocketChannel，然后绑定8080端口，并且处理其accept事件
		AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
		server.bind(new InetSocketAddress(8080));
		server.accept(server, new AcceptCompletionHandler());
		latch.await();
	}
}


class AcceptCompletionHandler implements 
	CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>{

	// 通过Attachement的方式进行对象的传递，将原始Channel传递到各个异步回调函数使用。
	@Override
	public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
		attachment.accept(attachment, this);  // 在处理了accept事件之后，继续递归监听下一个accept事件。
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		result.read(buffer, buffer, new ReadCompletionHandler(result)); 	// 处理客户端的数据
	}

	@Override
	public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
		exc.printStackTrace();
		
	}
}
class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
	private AsynchronousSocketChannel channel;
	
	public ReadCompletionHandler(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);  // 读取客户端发送的数据
		try {
			System.out.println("Server receives message: ");
			System.out.println(new String(body, StandardCharsets.UTF_8));	// 打印客户端数据
			doWrite();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doWrite() {
		ByteBuffer buffer = ByteBuffer.wrap(("Hello, I'm server. It's: " + new Date()).getBytes());
		// 异步的写入服务端的数据，这里也是通过一个CompletionHandler来异步的写入数据
		channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if (attachment.hasRemaining()) {
					channel.write(attachment, attachment, this);  // 将数据写入到服务器channel中
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				try {
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
