import java.util.Comparator;

/**
 * Sort using Quicksort.
 *
 * @author Wenfei Lin
 */

public class Quicksort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new Quicksort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  Quicksort() {
  } // Quicksort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    quickSort(values, order, 0, values.length);
  } // sort(T[], Comparator<? super T>

  private static <T> void quickSort(T[] values, Comparator<? super T> compare, int lb, int ub) {
    // Subarrays of one element or fewer are sorted.
    if (ub - lb <= 1) {
      return;
    } else {
      int mid = partition(values, compare, lb, ub);
      quickSort(values, compare, lb, mid);
      quickSort(values, compare, mid + 1, ub);
    } // if/else
  } // quickSort(T[], Comparator, int, int)

  /**
 * Select a pivot and partition the subarray from [lb .. ub) into 
 * the following form.
 *
 * <pre>
 * ---+-----------------+-+----------------+---
 *    | values <= pivot |p| values > pivot |
 * ---+-----------------+-+----------------+---
 *    |                 |                  |
 *    lb                pivotLoc           ub
 * </pre>
 *
 * @return pivotLoc.
 */
private static <T> int partition(T[] arr, Comparator<? super T> order, int lb, int ub) {
  int pivot = medianOfThree(lb, ub);
  int small = lb + 1;
  int large = ub;
  
  swap(arr, lb, pivot);

  while (small < large) {
    if (order.compare(arr[small], arr[lb]) <= 0) {
      small++;
    } else if (order.compare(arr[large - 1], arr[lb]) > 0) {
      large--;
    } else {
      swap(arr, small, --large);
      small++;
    }
  }

  swap(arr, lb, small - 1);
  return small - 1;
} // partition
/**
 * Swap two elements in an array (copied from Sam's code)
 */
private static <T> void swap(T[] vals, int i, int j) {
  T tmp = vals[i];
  vals[i] = vals[j];
  vals[j] = tmp;
} // swap(T[], int, int)

private static int medianOfThree(int left, int right) {
  int middle = left + (right - left) / 2;
  
  if (left > middle && left > right) {
    // left is largest, so determine the next largest
    if (middle >= right) {
      return middle;
    } else {
      return right;
    }
  }

  if (middle > left && middle > right) {
    // middle is largest, so determine the next largest
    if (left >= right) {
      return left;
    } else {
      return right;
    }
  }

  if (right > middle && right > left) {
    // right is largest, so determine the next largest
    if (middle >= left) {
      return middle;
    } else {
      return left;
    }
  }

  return -1;
} // medianOfThree(int[], int, int)

} // class Quicksort
