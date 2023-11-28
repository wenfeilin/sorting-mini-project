import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using merge sort.
 *
 * @author Albert, Wenfei Lin
 */

public class MergeSort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new MergeSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  MergeSort() {
  } // MergeSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array using the merge sort algorithm.
   * 
   * @param values The array being sorted according to the comparator.
   * @param order The comparator that will determine the order values 
   *              will be sorted in.
   * @post The array will be sorted completely according to the comparator.
   */
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    mergeSort(values, order, 0, values.length);
  } // sort(T[], Comparator<? super T>)

  /**
   * Recursively divides the array being sorted into subarrays that will be 
   * sorted then merged (combined) back together.
   * 
   * @param values The original array being sorted.
   * @param order The comparator that determines the order the array will 
   *              be sorted in.
   * @param lo The lowest index (inclusive) of the array.
   * @param hi The highest index (exclusive) of the array.
   * @post The array will be sorted completely (depending on lo and hi) 
   *       according to the comparator.
   */
  private static <T> void mergeSort(T[] values, Comparator<? super T> order, 
                                    int lo, int hi) {
    if (hi - lo <= 1) {
      return;
    } else {
      int mid = lo + (hi - lo) / 2;

      mergeSort(values, order, lo, mid); 
      mergeSort(values, order, mid, hi);
  
      merge(values, lo, mid, hi, order);
    } // if
  } // mergeSort(T[], Comparator<? super T>, int, int)

  /**
   * Merge the values from positions [lo..mid) and [mid..hi) back into
   * the same part of the array.
   *
   * @param vals The original array being sorted.
   * @param lo The lowest index (inclusive) of the subarray.
   * @param mid The middle index of the subarray.
   * @param hi The highest index (exclusive) of the subarray.
   * @param order The comparator that determines the order the array will 
   *              be sorted in.
   * @pre Each subarray is sorted accorting to comparator.
   * @post The subarray from lo (inclusive) to hi (exlusive) in vals will 
   *       be sorted according to the comparator order.
   */
  private static <T> void merge(T[] vals, int lo, int mid, int hi, 
                                Comparator<? super T> order) {
    T[] newVals = Arrays.copyOfRange(vals, lo, hi);
    int left = lo;
    int right = mid;
    int newValsIndex = 0;

    while (left != mid && right != hi) { 
      // Continue as long as both subarrays have not been completely 
      // traversed through:
      // Compare the elements of each subarray to each other
      if (order.compare(vals[left], vals[right]) <= 0) {
        // If the element in the left subarray comes before the element 
        // in the right array (as specified by the order comparator), 
        // add the element from the left subarray to the newVals array
        newVals[newValsIndex++] = vals[left++];
      } else {
        // If the element in the right subarray comes before the element 
        // in the left array (as specified by the order comparator), 
        // add the element from the right subarray to the newVals array
        newVals[newValsIndex++] = vals[right++];
      } // if
    } // while

    // Once one subarray has been fully traversed through, that means 
    // the other subarray has not been fully traversed through yet
    if (left == mid) { 
      // If the left subarray was fully looked at, copy the rest of 
      // the right subarray into the newVals array (because everything 
      // is in order from then on)
      for (; right != hi; newValsIndex++, right++) {
        newVals[newValsIndex] = vals[right];
      } // for
    } else { 
      for (; left != mid; newValsIndex++, left++) {
      // If the right subarray was fully looked at, copy the rest of
      // the left subarray into the newVals array (because everything 
      // is in order from then on)
        newVals[newValsIndex] = vals[left];
      } // for 
    } // if

    // Copy the now in order version of the two combined subarrays (newVals) 
    // back into the original array
    newValsIndex = 0;
    for (; lo != hi; lo++, newValsIndex++) {
      vals[lo] = newVals[newValsIndex];
    } // for
  } // merge(T[], int, int, int, Comparator<? super T>)
} // class MergeSort