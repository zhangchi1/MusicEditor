package cs3500.music.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.AbsolutePitchRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the AbsolutPitchRange class
 */
public class AbsolutePitchRangeTest {

  /**
   * Tests that a lower bound above an upper bound makes an empty range
   */
  @Test
  public void testSwappedBounds() {
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.A4,
            SamplePitches.D3).iterator();
    assertFalse(range.hasNext());
  }


  /**
   * Tests that a range with equal bounds contains one element
   */
  @Test
  public void testEqualBounds() {
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.A4,
            SamplePitches.A4).iterator();
    assertEquals(true, range.hasNext());
    range.next();
    assertEquals(false, range.hasNext());
  }


  /**
   * Tests that an empty range errors on next()
   */
  @Test(expected = NoSuchElementException.class)
  public void testEmptyHasNoNext() {
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.A4,
            SamplePitches.D3).iterator();
    assertFalse(range.hasNext());
    range.next();
  }

  /**
   * Tests a range of length 1 has 2 elements
   */
  @Test
  public void testRange1() {
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.A4,
            SamplePitches.A4.changePitch(1)).iterator();
    assertTrue(range.hasNext());
    range.next();
    assertEquals(true, range.hasNext());
    range.next();
    assertEquals(false, range.hasNext());
  }

  /**
   * Tests a range of length 3 has 3 elements
   */
  @Test
  public void testRange2() {
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.D3,
            SamplePitches.D3.changePitch(2)).iterator();
    assertTrue(range.hasNext());
    range.next();
    assertTrue(range.hasNext());
    range.next();
    assertTrue(range.hasNext());
    range.next();
    assertFalse(range.hasNext());
  }

  /**
   * Tests that the first element is the first input
   */
  @Test
  public void testFirstElemRange() {
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.D3,
            SamplePitches.FSharp9.changePitch(10)).iterator();
    assertTrue(range.hasNext());
    Assert.assertEquals(SamplePitches.D3, range.next());
  }

  /**
   * Tests that the first element is less than the second
   */
  @Test
  public void testFirstElemLessThanSecond() {
    AbsolutePitch test = SamplePitches.FSharp9.changePitch(10);
    Iterator<AbsolutePitch> range = new AbsolutePitchRange(SamplePitches.FSharp9,
            SamplePitches.FSharp9.changePitch(10)).iterator();
    assertTrue(test.compareTo(SamplePitches.FSharp9) > 0);
    assertTrue(range.hasNext());
    AbsolutePitch p1 = range.next();
    assertTrue(range.hasNext());
    AbsolutePitch p2 = range.next();
    assertTrue(p1.compareTo(p2) < 0);
  }

  /**
   * Tests that all elements are in ascending order
   */
  @Test
  public void testElemsIncreasing() {
    AbsolutePitchRange range = new AbsolutePitchRange(SamplePitches.FSharp9,
            SamplePitches.FSharp9.changePitch(10));
    List<AbsolutePitch> oldPitches = new ArrayList<>();
    for (AbsolutePitch p : range) {
      for (AbsolutePitch p2 : oldPitches) {
        assertTrue(p.compareTo(p2) > 0);
      }
      oldPitches.add(p);
    }
  }


}
