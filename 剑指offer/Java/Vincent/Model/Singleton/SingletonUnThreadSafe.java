package Model.Singleton;

/**
 * 饱汉模式（懒汉）--- 非线程安全
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 * 缺点：非线程安全。
 * **/

public class SingletonUnThreadSafe {
	private static SingletonUnThreadSafe singleton = null;
	public SingletonUnThreadSafe() {
		// TODO Auto-generated constructor stub
	}
	
	public static SingletonUnThreadSafe getInstance() {
		if (singleton == null) {
			singleton = new SingletonUnThreadSafe();
		}
		return singleton;
	}
}
