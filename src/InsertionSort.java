import java.util.Comparator;

/**
 * Sort using insertion sort.
 *
 * @author Wenfei Lin
 */

public class InsertionSort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new InsertionSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  InsertionSort() {
  } // InsertionSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array using the insertion sort algorithm.
   * 
   * @param values The array being sorted.
   * @param order The comparator that determines what order the elements in 
   *              the array will be sorted in.
   * @post The array will be sorted in the order delineated by the comparator.
   */
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    if (values.length <= 1) {
      // One element arrays are already sorted
      return; 
    } else {
      // B/c first element of arrays are already sorted
      int barrier = 1; 

      while (barrier != values.length) { 
        insert(values, order, barrier);
        barrier++;
      } // while
    } // if
  } // sort(T[], Comparator<? super T>)

  /**
   * Inserts the current element at the barrier into the sorted part of 
   * the array at its correct position. 
   * 
   * @param values The array being sorted.
   * @param order The comparator that determines what order the elements in 
   *              the array will be sorted in.
   * @param barrier The rightmost index of the (so-far) sorted part of 
   *                the array values.
   */
  private <T> void insert(T[] values, Comparator<? super T> order, 
                          int barrier) {
    if (order.compare(values[barrier], values[barrier - 1]) <= -1) { 
      // If element should be swapped, keep track of element that 
      // will now be swapped (with the previous element)
      int swapTracker;
      swapTracker = barrier; 

      while (swapTracker != 0 && (order.compare(
              values[swapTracker], values[swapTracker - 1]) <= -1)) { 
        // Keep swapping with previous element as long as tracked element
        // is not smaller than the previous element (until there runs out
        // of room or it ends up at index 0 (sorted))

        // Swapping occurs with previous element of tracked element
        Utils.swap(values, swapTracker - 1, swapTracker);
        swapTracker--;
      } // while
    } // if
  } // insert()
} // class InsertionSort