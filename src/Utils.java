/**
 * A class for general utility methods to be used in sorting.
 * @author Wenfei Lin
 */
public class Utils {
  /**
   * Swap two elements in an array (copied from SamR's code).
   * 
   * @param vals The array where the swapping takes place.
   * @param i The index of one of the elements being swapped.
   * @param j The index of the other element being swapped.
   * @pre i and j are valid indices of the array (from 0 to vals.length - 1).
   * @post The elements originally at indices i and j resepctively are swapped.
   */
  public static <T> void swap(T[] vals, int i, int j) {
    T tmp = vals[i];
    vals[i] = vals[j];
    vals[j] = tmp;
  } // swap(T[], int, int)
} // class Utils