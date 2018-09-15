package ehoul.study.FindMaxIMUMSubarray;

public class FindMaximunSubarray {
	   
	public static int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high){
		
		int [] result = new int[3];
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int maxleft = mid;
		for (int i = mid; i >= low; i--) {
			sum += A[i];
			if (sum > leftSum) {
				leftSum = sum;
				maxleft = i;
			}
		}
		
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		int maxright = mid+1;
		for (int i = mid+1; i <= high; i++) {
		    sum += A[i];
		    if (sum > rightSum) {
				rightSum = sum;
				maxright = i;
			}
		}
		result[0] = maxleft;
		result[1] = maxright;
		result[2] = leftSum+rightSum;
		return result;
	}
       public static int[] findMaximumSubarray(int[] A, int low, int high){
    	   
	    	int[] result      = new int[3];
	    	int[] resultleft  = new int[3];
	    	int[] resultright = new int[3];
	    	int[] resultcross = new int[3];
	    	if (high == low) {
				result[0] = low;
				result[1] = high;
				result[2] = A[low];
			}else {
				int mid = (low + high)/2;
				resultleft  = findMaximumSubarray(A,low,mid);
				resultright = findMaximumSubarray(A,mid+1,high);
				resultcross = findMaxCrossingSubarray(A,low,mid,high);
			}
	    	if (resultleft[2] >= resultcross[2]) {
				if (resultleft[2] >= resultright[2]) {
					result = resultleft;
				}else if (resultcross[2] >= resultright[2]) {
					result = resultcross;
				}else {
					result = resultright;
				}
			}else {
				if (resultcross[2] >= resultright[2]) {
					result = resultcross;
				}else {
					result = resultright;
				}
			}
			return result;
    	   
       }
       
       public static void main(String [] args) {
    	   int[] Test = {1,-1,-2,1,3,4,-2,-9,5,1};
		  int[] r = findMaximumSubarray(Test,0,9);
		  for (int i = r[0]; i <= r[1]; i++) {
			  System.out.println(Test[i]);
		  }
		  
	}
}
