package IOTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;


public class EchoClient_AIO extends EchoClient{

	@Override
	public void testClient() throws IOException, InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		// 创建一个客户端AsynchronousSocketChannel
		AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
		// 异步连接服务器，并且将连接后的处理交由ConnectCompletionHandler进行
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080), socketChannel, new ConnectCompletionHandler(latch));
		latch.await();
	}
}

class ConnectCompletionHandler implements CompletionHandler<Void, AsynchronousSocketChannel>{
	private CountDownLatch latch;
	
	public ConnectCompletionHandler(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void completed(Void result, AsynchronousSocketChannel channel) {
		ByteBuffer buffer = ByteBuffer.wrap("Hello, I'm client. ".getBytes());
		// 连接完成后，往Channel中写入数据，以发送给服务器
		channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if (buffer.hasRemaining()) {
					channel.write(buffer, buffer, this);
				}
				else {
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					// 写入完成后，这里异步监听服务器的响应数据
					channel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {

						@Override
						public void completed(Integer result, ByteBuffer attachment) {
							attachment.flip();
							byte[] bytes = new byte[attachment.remaining()];
							readBuffer.get(bytes);	// 读取服务器响应的数据，并且进行处理
							try {
								System.out.println("Client receives message: ");
								System.out.println(new String(bytes, StandardCharsets.UTF_8));
								latch.countDown();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							try {
								channel.close();
								latch.countDown();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				try {
					channel.close();
					latch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
		 exc.printStackTrace();
	}
}