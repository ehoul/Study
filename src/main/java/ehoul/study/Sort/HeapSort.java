package ehoul.study.Sort;

import java.util.Arrays;

public class HeapSort {
	  private static int HeapSzie;
      public static <T extends Comparable<? super T>> void heapSort(T[] A) {
    	  buildMaxHeap(A);
    	  for (int i = A.length-1; i > 0; i--) {
    		T temp = A[i];
  			A[i] = A[0];
  			A[0] = temp;
  			HeapSzie --;
  			maxHeapify(A,0);
		}
	}
      /**
       * build max heap for A
     * @param A
     */
    public static <T extends Comparable<? super T>> void buildMaxHeap(T[] A) {
    	  HeapSzie = A.length;
    	  for (int i = HeapSzie/2-1; i >= 0; i--) {
			maxHeapify(A,i);
		}
  	}
      /**
       * 
     * @param A
     * @param i
     */
    public static <T extends Comparable<? super T>> void maxHeapify(T[] A,int i) {
    	  int l = 2*i+1;
    	  int r = 2*i+2;
    	  int largest;
    	  if (l < HeapSzie&&(A[l].compareTo(A[i]) > 0)) {
			largest = l;
		}else {
			largest = i;
		}
    	  if (r < HeapSzie&&(A[r].compareTo(A[largest]) > 0)) {
			largest = r;
		}
    	  if (largest != i) {
			T temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			maxHeapify(A, largest);
		}
      }
    /**
     * return max of A
     * @param A
     * @return
     */
    public static <T extends Comparable<? super T>> T heapMaximum(T[] A) {
    	return A[0];
    }
    /**
     * remove max and return max
     * @param A
     * @return
     * @throws Exception
     */
    public static <T extends Comparable<? super T>> T heapExtractMax(T[] A) throws Exception {
    	if (HeapSzie < 1) {
			return null;
		}
    	T max = A[0];
    	A[0] = A[HeapSzie-1];
    	HeapSzie = HeapSzie -1;
    	maxHeapify(A, 0);
		return max;
    }
    public static <T extends Comparable<? super T>> void heapIncreaseKey(T[] A, int i, T key){
    	if (A[i].compareTo(key) > 0) {
			System.out.println("new key is smaller than current key");
		}
    	A[i] = key;
    	while (i > 0&&(A[i].compareTo(A[(i+1)/2-1])) > 0) {
			T temp = A[i];
			A[i] = A[(i+1)/2-1];
			A[(i+1)/2-1] = temp;
			i = (i+1)/2-1;
		}
    }
      public static void main(String [] args) throws Exception {
    	  Integer[] array = { Integer.valueOf(6), Integer.valueOf(8),
  				Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(9),
  				Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(90),
  				Integer.valueOf(310), Integer.valueOf(67),
  				Integer.valueOf(300), Integer.valueOf(78) };
          buildMaxHeap(array);
          System.out.println(Arrays.toString(array));
          heapIncreaseKey(array,2,311);
//    	  heapSort(array);
    	  System.out.println(Arrays.toString(array));
	}
}
