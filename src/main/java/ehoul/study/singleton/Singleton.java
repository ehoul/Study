package ehoul.study.singleton;

public class Singleton {
    private static Singleton instance = null;
    
    private Singleton () {
		
	}
    private static  synchronized void syncInit() {
		if (null == instance) {
			instance = new Singleton();
		}
	}
    public static Singleton getSingleton() {
		if (null == instance) {
			syncInit();
		}
		return instance;
	}
    
    public Object readResolve() {
		return instance;
		
	}
}
