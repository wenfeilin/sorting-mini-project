import java.util.Comparator;

/**
 * Sort using Quicksort.
 *
 * @author Reed, Wenfei Lin
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

  /**
   * Sort an array using the quick sort algorithm.
   * 
   * @param values The array being sorted.
   * @param order The comparator that determines what order the elements 
   *              in the array will be sorted in.
   * @post The array will be sorted completely according to the comparator.
   */
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    quickSort(values, order, 0, values.length);
  } // sort(T[], Comparator<? super T>)

  /**
   * Recursively divides the array being sorted into subarrays that
   * will be sorted.
   * 
   * @param values The original array being sorted.
   * @param compare The comparator that determines what order the elements 
   *                in the array will be sorted in.
   * @param lb The lower bound (inclusive) of the subarray being considered.
   * @param ub The upper bound (exclusive) of the subarray being considered.
   * @post The array will be sorted completely (depending on lb and ub) 
   *       according to the comparator.
   */
  private static <T> void quickSort(T[] values, Comparator<? super T> compare, 
                                    int lb, int ub) {
    // Subarrays of one element or fewer are sorted.
    if (ub - lb <= 1) {
      return;
    } else { 
      // Otherwise, keep partitioning the array recursively into smaller 
      // subarrays that will then be sorted
      int pivot = partition(values, compare, lb, ub);
      quickSort(values, compare, lb, pivot);
      quickSort(values, compare, pivot + 1, ub);
    } // if/else
  } // quickSort(T[], Comparator<? super T>, int, int)

  /**
   * Select a pivot, partition the subarray from [lb .. ub), and sort 
   * that subarray.
   * 
   * @param arr The original array being sorted.
   * @param order The comparator that determines what order the elements in 
   *              the array will be sorted in.
   * @param lb The lower bound of the subarray (inclusive).
   * @param ub The upper bound of the subarray (exclusive).
   * @return The pivot location (index).
   * @pre lb and ub are valid indices of the subarray in consideration 
   *      (from 0 to arr.length - 1)
   */
  private static <T> int partition(T[] arr, Comparator<? super T> order, 
                                   int lb, int ub) {
    // Determine the pivot by taking the median of the lower bound, 
    // upper bound, and the index that is the middle of the lower and 
    // upper bounds
    int pivot = medianOfThree(lb, ub);

    // Swaps the element at the pivot location to the front of the subarray
    Utils.swap(arr, lb, pivot);

    // These will track the elements at the opposite ends of the subarray 
    // Excluding the element at the pivot location (which is at the very front)
    int small = lb + 1; 
    int large = ub;

    // Note: Elements smaller than or equal to the pivot stay on the left side 
    // of the subarray while elements greater than the pivot stay on the right 
    // side of the array
    while (small < large) { // Continue while 
      if (order.compare(arr[small], arr[lb]) <= 0) {
        // If the element on the left side of the subarray is less than or 
        // equal to the pivot, the former stays on the left side, so move 
        // the barrier location, small, to the right by 1
        small++;
      } else if (order.compare(arr[large - 1], arr[lb]) > 0) { 
        // large - 1 b/c ub is exclusive:
        // If the element on the right side of the subarray is greater than 
        // the pivot, the former stays on the right side, so move the barrier 
        // location, large, to the left by 1
        large--;
      } else {
        // Otherwise, the element on the left side of the subarray is greater 
        // than the pivot and the element on the right side of the subarray is 
        // less than or equal to the pivot, so the two elements should be 
        // swapped to end up in the correct sections of the subarray.
        // The barriers for both small and large are moved over, so small moves
        // to the right by 1 after the swap, and large moves to the left by 1 
        // before the swap 
        Utils.swap(arr, small, --large);
        small++;
      } // if
    } // while

    // Finally, swap the pivot to the position right after the last smaller 
    // element so the pivot can separate the sections of elements smaller 
    // than it and greater than it
    Utils.swap(arr, lb, small - 1);
    // Return the pivot's index
    return small - 1;
  } // partition(T[], Comparator<? super T>, int, int)

  /**
   * Calculates the median of three values.
   * 
   * @param left One of the numbers being considered in the median.
   * @param right The second number being considered in the median.
   * @return The median of left, right, and the middle valeu of left and right.
   */
  private static int medianOfThree(int left, int right) {
    // find the middle of the left and right
    int middle = left + (right - left) / 2; 
    
    if (left > middle && left > right) {
      // left is largest, so determine the next largest
      if (middle >= right) {
        // middle is median (as it's the second largest)
        return middle; 
      } else {
        // right is median (as it's the second largest)
        return right; 
      } // if
    } // if

    if (middle > left && middle > right) {
      // middle is largest, so determine the next largest
      if (left >= right) {
        // left is median (as it's the second largest)
        return left; 
      } else { 
        // right is median (as it's the second largest)
        return right; 
      } // if
    } // if

    if (right > middle && right > left) {
      // right is largest, so determine the next largest
      if (middle >= left) {
        // middle is median (as it's the second largest)
        return middle; 
      } else {
        // left is median (as it's the second largest)
        return left; 
      } // if
    } // if

    // If something goes wrong...
    return -1;
  } // medianOfThree(int, int)
} // class Quicksort