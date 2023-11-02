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
   * @param order The comparator that determines what order the elements in the array 
   *              will be sorted in.
   * @post The array will be sorted in the order delineated by the comparator .
   */
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    if (values.length <= 1) {
      return; // One element arrays are already sorted
    } else {
      int barrier = 1; // B/c first element of arrays are already sorted
      int swapTracker;

      // Compare now:
      while (barrier != values.length) { // loop through entire array 
        if (order.compare(values[barrier], values[barrier - 1]) <= -1) { 
          // If element should be swapped, 
          // keep track of element that will now be swapped (with the previous element)
          swapTracker = barrier; 

          while (swapTracker != 0 && (order.compare(values[swapTracker], 
                                                    values[swapTracker - 1]) <= -1)) { 
            // Keep swapping with previous element as long as tracked element is not smaller than 
            // the previous element (until there runs out of room or it ends up at index 0 
            // (sorted))

            // Swapping occurs with previous element of tracked element
            T temp = values[swapTracker - 1];
            values[swapTracker - 1] = values[swapTracker];
            values[swapTracker--] = temp;
          }

          barrier++; // Look at next element in unsorted part of array
        } else { // Element is currently in right position (no swapping)
          barrier++; // Look at next element in unsorted part of array
        }
      }
    }
  } // sort(T[], Comparator<? super T>)
} // class InsertionSort
