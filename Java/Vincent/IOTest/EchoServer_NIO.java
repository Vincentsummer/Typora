package IOTest;

/**
 * NIO 服务端 Test
 * 首先创建了一个ServerSocketChannel，并且通过其configureBlocking()方法将其设置为非阻塞模式，
 * 设置为这种模式之后，其accept()等方法就是非阻塞的。然后创建一个Selector多路复用器，并且
 * 将ServerSocketChannel注册到该多路复用器上。接着通过多路复用器的select()方法阻塞当前线程，等待
 * 注册的Channel事件触发。在触发之后通过遍历SelectionKey对象来进行不同事件的处理。
 * **/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServer_NIO extends EchoServer{

	@Override
	public void testServer() throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		// 设置服务端channel为非阻塞模式，设置为这种模式之后，其accept()等方法就是非阻塞的。
		server.configureBlocking(false);
		server.bind(new InetSocketAddress(8080));
		
		Selector selector = Selector.open();	// 开启一个多路复用器
		// 对于服务端而言，其一开始只需要监听服务器的Accept事件，该事件表示服务器成功接收到一个客户端连接
		server.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true) {
			/**
			 * select()方法会一直等待所有注册的channel发生其所感兴趣的事件。这里分为两种情况：
			 * 1. 对于刚开始启动时，这里只会收到服务器接收到客户端连接的Accept事件，由于服务器接收到客户端
			 * 连接并不一定代表客户端有数据发送过来，因而这里会将客户端Channel注册到Selector上，同时监听服
			 * 务端和客户端的事件；
			 * 2. 对于客户端而言，在其注册到Selector上之后，就会监听其read和write事件，从而进行数据读写。需要
			 * 注意的是，如果没有可用事件，这里select()方法会一直阻塞，直到有感兴趣的事件到达。
			 * **/
			int size = selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
		        // 这里需要 remove，否则下次循环该事件就会被重复处理。Selector不会对已经产生的事件进行移除。
				iterator.remove();
				if (key.isAcceptable()) {
					accept(key, selector);  // 处理接收客户端请求事件
				}
				else if (key.isReadable()) {
					read(key, selector);	// 处理从客户端channel读取事件
				}
				else if (key.isWritable()) {
					write(key);	// 处理往客户端channel写入数据事件
				}
			}
		}
	}
	
	private void accept(SelectionKey key, Selector selector) throws IOException {
		// 由于只有ServerSocketChannel才会有accept事件，因而可强转为ServerSocketChannel
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = server.accept();	// 获取客户端连接
		socketChannel.configureBlocking(false);	// 设置客户端channel为非阻塞模式
		socketChannel.register(selector, SelectionKey.OP_READ);	// 注册客户端channel到selector上
	}
	
	 // 在客户端连接建立之后，客户端channel就会发送数据到服务端，从而产生read事件
	private void read(SelectionKey key, Selector selector) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);		// 读取客户端channel数据
		handle(buffer);  // 处理读取的客户端数据
		channel.register(selector, SelectionKey.OP_WRITE);	// 读取完成之后注册write事件
	}
	
	 // 当客户端数据处理完成之后，就会注册写入事件，此时可以往客户端写入数据
	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		response(channel);
	}
}
