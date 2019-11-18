package Model.Singleton;

/**
 * 饱汉模式(懒汉模式)--线程安全
 * 优点：懒加载启动快，资源占用小，使用时才实例化，但加锁了。
 * 缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。
 * **/

public class SingletonThreadSafe {
	private static SingletonThreadSafe singleton = null;
	private SingletonThreadSafe() {}
	
	public static synchronized SingletonThreadSafe getInstance() {
		if (singleton == null) {
			singleton = new SingletonThreadSafe();
		}
		return singleton;
	}
}
