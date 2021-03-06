Java IO的四种模式：
1. 同步BIO
	1). 在服务端中，包括绑定端口，接收客户端连接，处理客户端请求数据，响应客户端等都是在同一个线程中进行的，
		也就是说服务器在同一时刻只能处理一个客户端链接，这极大的限制了服务器的响应效率；
	2). ServerSocketChannel.accept()方法是一个阻塞型的方法，在接收客户端连接时，会受到网络环境的影响，
		对其效率产生很大影响；
	3). SocketChannel.write()方法可能会因为数据的写入效率问题而对服务端线程产生影响，最终影响到服务器的性能；
	
2. 异步BIO
	1). 由于客户端连接都是异步放在线程池中进行处理，因而同一时刻能够接收到的客户端请求数量将严重受限于这里线程池的大小，
		而服务器的线程数量也不是可以无限增大的。这里比较典型的如Tomcat容器，其最初采用的就是这种模式，而其线程池大小则
		默认为200。那么在现在这种对服务器并发性能越来越高的情况下，一个Tomcat容器所能承载的TPS只能达到几千，如果要提升
		服务器整体负载，只能通过负载均衡的方式进行横向扩容；
	2). 在服务器接收客户端请求时，可能由于并发量较大，导致ServerSocketChannel.accept()方法负载过高，而这里却不能通
		过使用线程池的方式来使用多个线程同时接收客户端连接；
	3). 在线程池中线程处理完客户端数据，并且往客户端channel中写入数据时，这是在客户端线程中进行的，而SocketChannel.write()
		方法可能由于网络原因导致一定的阻塞，从而导致线程池线程长时间耗费在等待上，导致服务器响应降低。

3. NIO
	1). 基于操作系统的多路复用模型，只需要监听客户端的各个事件即可，因而使用一个线程即可处理大量的客户端连接，非常适合高并发服
		务器的编排。并且这里如果客户端并发量非常大，那么是可以使用一个线程组来专门处理客户端channel的连接事件，然后将其余的读
		写事件向下分发到到相应的IO线程组里；
	2). 由于是基于事件驱动模型处理相应的IO事件，因而这里对客户端的数据处理是非常高效的。
	
4. AIO
	1). 本质上是使用的IO多路复用模式，因而天然支持高并发模型；
	2). 底层使用了异步IO的模型，因而无需用户使用线程池等工具来分离相关事件的处理，而NIO是需要用户手动创建线程池进行处理的；
	3). 在编码模式上更加符合事件处理这一模型，更加符合用户习惯，降低了用户编码的难度。
