package IOTest;

/**
 * NIO 客户端 Test
 * 客户端的处理模式与服务端基本类似，只是客户端首先监听的是Connect事件；在连接成功后切换为监听写入事件，
 * 以写入数据发送到服务端；在发送完成后，又会切换为监听读取事件，以等待服务器发送数据并且进行处理。
 * **/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoClient_NIO extends EchoClient{

	@Override
	public void testClient() throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		// 连接服务器，注意将其放在注册代码之后，否则就无法触发注册的连接事件，因为注册时已连接完成。
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
		while(true) {
			/**
			 * 阻塞等待客户端channel事件的触发，这里需要注意的是，对于刚注册的SocketChannel，其只对
			 * Connect事件进行了监听，因而只会触发Connect事件，而connect之后才会注册write事件，因为
			 * 对于客户端而言，其注册成功之后就会往服务端发送数据，因而注册的是write事件。在write数据完
			 * 成之后，就会将其切换为监听read事件，等待服务器的响应并且进行处理。
			 * **/
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();	// 与服务端一样，需要将已处理的事件进行移除。
				if (key.isConnectable()) {
					connect(key, selector);	// 处理连接完成事件
				}
				else if (key.isWritable()) {
					write(key, selector); 	// 处理写入事件
				}
				else if (key.isReadable()) {
					read(key);		// 处理读取事件
				}
			}
		}
	}
	
	// 处理连接事件，在连接完成之后重新注册写入事件以准备写入数据
	private void connect(SelectionKey key, Selector selector) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		channel.register(selector, SelectionKey.OP_WRITE);	// 注册写入事件
		channel.finishConnect();
	}
	
	private void write(SelectionKey key, Selector selector) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		request(channel);	// 向SocketChannel中写入数据
		channel.register(selector, SelectionKey.OP_READ); 	// 写入完成后切换为监听读取事件等待服务器响应
	}
	
	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		handleResponse(channel);		// 处理服务器响应的数据
		channel.close();
	}
}
