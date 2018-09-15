package ehoul.study;

public class QuickSort {
     public  void quickSort(int[] arr,int start, int end) {
    	 int low = start;
    	 int high = end;
    	 int povit = arr[low];
    	 while (low < high) {
			while (low < high&&arr[high] >= povit) {
				high--;				
			}
			if (low < high) {
				int temp = arr[high];
				arr[high] = arr[low];
				arr[low] = temp;
				low++;
			}
			
			while (low < high&&arr[low] <= povit) {
				low++;				
			}
			if (low < high) {
				int temp = arr[high];
				arr[high] = arr[low];
				arr[low] = temp;
				high--;
			}	
		}
//    	 System.out.print("low="+(low+1)+"high="+(high+1)+"povit="+povit+"\n");
    	 if (low > start) {
			quickSort(arr, start, low-1);
		}
    	 if (high < end) {
			quickSort(arr, low+1, end);
		}
	}
     
    public static void main(String[] args) {
    	int[] test = {3,2,5,1,6,4,2,1,7,9,8,11,0};
    	QuickSort quickSort = new QuickSort();
    	quickSort.quickSort(test,0,12);
    	for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
}
