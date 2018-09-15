package ehoul.study.Sort;

import java.util.Arrays;

import org.apache.poi.ss.formula.functions.T;

public class Sort{
	
	/**
	 * Insert Sort
	 * @param a
	 */
	public static <T extends Comparable<? super T>> void insertSort(T[] a){
		int j;
		for (int i = 0; i < a.length; i++) {
			T temp = a[i];
			for (j = i; (j > 0 &&temp.compareTo(a[j - 1]) < 0); j--) {
				a[j] = a[j-1];
			}
			a[j] = temp;
		}
	}
	/**
	 * 二分插入
	 * @param a
	 */
	public static <T extends Comparable <? super T>> void binaryInsertionSort(T[] a)
	{
		for(int p = 1; p < a.length; p++)
		{
			T temp = a[p];
			int left = 0;
			int right = p - 1;
			int middle;
			
			while(left <= right)
			{
				middle = (left + right) / 2;
				if (temp.compareTo(a[middle]) < 0)
				{   
					right = middle - 1;
				}
				else {
					left = middle + 1;
				}
			}
			
			for(int j = p;j > left; j--)
			{   
				a[j] = a[j - 1];
			}
			a[left] = temp;
		}
	}

	public static void main(String[] args) {
		Integer[] array = { Integer.valueOf(6), Integer.valueOf(8),
				Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(9),
				Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(90),
				Integer.valueOf(310), Integer.valueOf(67),
				Integer.valueOf(300), Integer.valueOf(78) };
		Sort.binaryInsertionSort(array);
		System.out.println(Arrays.toString(array));
	}
}
