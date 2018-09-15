package ehoul.study;

public class DFS {
   static int[] a = new int[4];
   static int[] book = new int[4];
   public static void dfs(int step) {
	   if (step == 4) {
		System.out.println(a[1]+"  "+a[2]+"  "+a[3]);
	}
	for (int i = 1; i <= 3; i++) {
		if (book[i] == 0) {
			a[step] = i;
			book[i] = 1;
			
			dfs(step+1);
			
			book[i] = 0;
		}
        
	}
}
   public static void main(String [] args) {
	   dfs(1);
}
}
