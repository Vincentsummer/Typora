package Model.Singleton;

/**
 * 饿汉模式
 *  优点：饿汉模式天生是线程安全的，使用时没有延迟。
 *  缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
 * **/

public class Singleton {
	// 直接在这里创建类实例，只会创建一次
	private static Singleton singleton = new Singleton();
	private Singleton() {}
	
	public static Singleton getInstance() {
		return singleton;
	}
}
