package ehoul.study.Thread.Static;

public class TreadStatic implements Runnable{
    private static String suffix = "0";
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+":"+suffix);
		suffix = "1";
		System.out.println(Thread.currentThread().getName()+":"+suffix);
		try {
			Thread.sleep(2000);
			suffix = "2";
			System.out.println(Thread.currentThread().getName()+":"+suffix);
			Thread.sleep(2000);
			suffix = "3";
			System.out.println(Thread.currentThread().getName()+":"+suffix);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
      
}
