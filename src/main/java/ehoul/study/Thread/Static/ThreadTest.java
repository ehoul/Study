package ehoul.study.Thread.Static;

public class ThreadTest {
     public static void main(String [] args) throws InterruptedException {
		new Thread(new TreadStatic()).start();
		Thread.sleep(3000);
		new Thread(new TreadStatic()).start();
	}
}
