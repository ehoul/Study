package ehoul.study.Sort;

import java.util.Arrays;
import java.util.Random;

import org.apache.poi.ss.formula.functions.T;

public class QuickSort {
      public static <T extends Comparable <? super T>> void quickSort(T[] A, int p, int r){
    	  if (p < r) {
			int q = partition(A,p,r);
			quickSort(A, p, q-1);
			quickSort(A, q+1, r);
		}
      }
      public static <T extends Comparable<? super T>> int partition(T[] A, int p, int r){
    	  T x = A[r];
    	  int i = p-1;
    	  for (int j = p; j < r; j++) {
			if (x.compareTo(A[j]) >= 0) {
				i = i+1;
				T temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
    	  T t = A[i+1];
    	  A[i+1] = A[r];
    	  A[r] = t;
    	  return i+1;
      }
      public static <T extends Comparable<? super T>> int randomizedPartition(T[] A, int p, int r){
    	  Random random = new Random();
    	  int i = random.nextInt(r-p+1)+p;
    	  T temp = A[r];
    	  A[i] = A[r];
    	  A[r] = temp;
    	  return partition(A, p, r);
      }
  	public static void main(String[] args) {
		Integer[] array = { Integer.valueOf(6), Integer.valueOf(8),
				Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(9),
				Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(90),
				Integer.valueOf(310), Integer.valueOf(67),
				Integer.valueOf(300), Integer.valueOf(78) };
		QuickSort.quickSort(array, 0, 11);
		System.out.println(Arrays.toString(array));
	}
}
