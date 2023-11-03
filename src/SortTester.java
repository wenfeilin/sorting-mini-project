import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects.
 *
 * @author Wenfei Lin
 */
public class SortTester {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  Sorter sorter;

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  @Test
  public void orderedStringTest() {
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // orderedStringTest

  @Test
  public void reverseOrderedStringTest() {
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // orderedStringTest

  @Test
  public void noElementsTest() {
    String[] original = {};
    String[] expected = {};
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // noElementsTest

  @Test
  public void oneElementTest() {
    String[] original = { "alpha" };
    String[] expected = { "alpha" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // oneElementTest

  @Test
  public void twoElementsTest() {
    String[] original = { "brave", "alpha" };
    String[] expected = { "alpha", "brave" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // twoElementsTest
  
  @Test
  public void onlyTwoUnsortedElementsAtEndTest() {
    String[] original = { "alpha", "bravo", "charlie", "foxtrot", "delta" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // onlyTwoUnsortedElementsAtEndTest

  @Test
  public void onlyTwoUnsortedElementsAtFrontTest() {
    String[] original = { "bravo", "alpha", "charlie", "delta", "foxtrot" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // onlyTwoUnsortedElementsAtFrontTest

  @Test
  public void onlyTwoUnsortedElementsAtOppositeEndsTest() {
    String[] original = { "foxtrot", "bravo", "charlie", "delta", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // onlyTwoUnsortedElementsAtOppositeEndsTest

  @Test
  public void twoRepeatedElementsTest() {
    String[] original = { "alpha", "bravo", "charlie", "alpha", "foxtrot" };
    String[] expected = { "alpha", "alpha", "bravo", "charlie", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // twoRepeatedElementsTest

  @Test
  public void halvesSortedTest() {
    String[] original = { "delta", "foxtrot", "alpha", "bravo", "charlie" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // halvesSortedTest

  @Test
  public void sameElements() {
    String[] original = { "delta", "delta", "delta", "delta" };
    String[] expected = { "delta", "delta", "delta", "delta" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // sameElements
} // class SortTester
