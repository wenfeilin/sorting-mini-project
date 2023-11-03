import java.util.Comparator;

/**
 * Sort using a modified version of TimSort generated from ChatGPT.
 *
 * @author ChatGPT, Wenfei Lin
 */

public class LinWenfeiSort implements Sorter {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  // The size of the smaller chunks that TimSort divides the array into
  private static final int MIN_MERGE = 32;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new LinWenfeiSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  LinWenfeiSort() {
  } // LinWenfeiSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array using a somewhat modified TimSort (mergeSort + insertionSort).
   * 
   * @param values The array being sorted.
   * @param order The comparator that determines what order the elements in the array
   *              will be sorted in.
   * @post The array will be sorted in the order delineated by the comparator.
   */
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    int arrLength = values.length;

    // Sort the small-sized chunks (determined by MIN_MERGE) of the array using 
    // insertion sort:
    for (int i = 0; i < arrLength; i += MIN_MERGE) {
        insertionSort(values, order, i, Math.min((i + MIN_MERGE - 1), (arrLength - 1)));
    }

    // Merge sorted chunks (using the merge from mergeSort)... keep merging
    // until whole array is merged back together
    for (int size = MIN_MERGE; size < arrLength; size = size * 2) {
        for (int left = 0; left < arrLength; left += 2 * size) {
            int mid = Math.min(arrLength, left + size);
            int right = Math.min(arrLength, left + 2 * size);
            
            // Check if the two chunks are already sorted
            if (order.compare(values[mid - 1], values[mid]) <= 0) {
                continue;  // Skip the merge if not needed
            }

            merge(values, order, left, mid, right);
        }
    }
  } // sort(T[], Comparator<? super T>)

  /**
   * Sort an array using the insertion sort algorithm.
   * 
   * @param values The array being sorted.
   * @param order The comparator that determines what order the elements in the array 
   *              will be sorted in.
   * @param left The leftmost index of the array 
   * @param right The rightmost index of the array
   * @post The array will be sorted in the order delineated by the comparator.
   */
  private <T> void insertionSort(T[] values, Comparator<? super T> order, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
      T key = values[i];  // Reuse a single key variable
      int j = i - 1;
      
      // Store the comparison result in a variable for efficiency
      int comparisonResult;
      
      while (j >= left && (comparisonResult = order.compare(values[j], key)) > 0) {
          values[j + 1] = values[j];
          j--;
      }
      
      values[j + 1] = key;
    }
  } // insertionSort(T[], Comparator<? super T>, int, int)

  /**
   * Sort with the algorithm of merge from mergeSort (modified for TimSort).
   * 
   * @param values The array being sorted.
   * @param order The comparator that determines what order the elements in the array 
   *              will be sorted in.
   * @param left The leftmost index of the subarray being considered
   * @param mid The middle index of the subarray being considered
   * @param right The rightmost index of the subarray being considered
   * @post The subarray with its boundaries defined by left and right in the original 
   *       array is sorted.
   */
  private <T> void merge(T[] values, Comparator<? super T> order, int left, int mid, int right) {
    // Calculate the sizes of the left and right subarrays
    int leftSize = mid - left;
    int rightSize = right - mid;
    
    // Create an array to store the merged elements
    Object[] merged = new Object[leftSize + rightSize];

    int i = 0, j = 0, k = 0;
    while (i < leftSize && j < rightSize) {
      // Compare elements from the left and right subarrays
      if (order.compare(values[left + i], values[mid + j]) <= 0) {
        // If the element from the left subarray is smaller or equal, 
        // copy it to the merged array
        merged[k++] = values[left + i++];
      } else {
        // If the element from the right subarray is smaller, 
        // copy it to the merged array
        merged[k++] = values[mid + j++];
      }
    }

    // Copy any remaining elements from the left subarray to the merged array
    while (i < leftSize) {
      merged[k++] = values[left + i++];
    }

    // Copy any remaining elements from the right subarray to the merged array
    while (j < rightSize) {
      merged[k++] = values[mid + j++];
    }

    // Copy the merged elements back to the original array
    System.arraycopy(merged, 0, values, left, leftSize + rightSize);
  } // merge(T[], Comparator<? super T>, int, int, int)
} // class LinWenfeiSort
