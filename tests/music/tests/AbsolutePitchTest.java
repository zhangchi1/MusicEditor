package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.AbsolutePitchFactory;
import cs3500.music.model.RelativePitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the AbsolutePitch class
 */
public class AbsolutePitchTest {

  /**
   * Test creating a pitch with a negative octave errors
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeOctave() {
    AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, -2);
  }

  /**
   * Test creating a pitch with a 0 octave does not error
   */
  @Test
  public void test0Octave() {
    AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, 0);
  }

  /**
   * Test creating a pitch above the max octave errors
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAboveMaxOctave() {
    AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, AbsolutePitchFactory.MAX_OCTAVE + 1);
  }

  /**
   * Test creating a pitch with a null relative pitch
   */
  @Test(expected = NullPointerException.class)
  public void tesNullPitch() {
    AbsolutePitchFactory.newAbsolutePitch(null, 4);
  }

  /**
   * Test that ordering is based on octaves before relative pitches
   */
  @Test
  public void testOctavePrimaryOrder() {
    for (RelativePitch p1 : RelativePitch.values()) {
      for (int i = 1; i < AbsolutePitchFactory.MAX_OCTAVE; i++) {
        AbsolutePitch pitch1 = AbsolutePitchFactory.newAbsolutePitch(p1, i);
        for (RelativePitch p2 : RelativePitch.values()) {
          assertTrue(pitch1.compareTo(AbsolutePitchFactory.newAbsolutePitch(p2, i + 1)) < 0);
        }
      }
    }
  }

  /**
   * Test that getName concatenates the number onto the relative name
   */
  @Test
  public void testGetName() {
    for (RelativePitch p : RelativePitch.values()) {
      for (int i = 1; i <= AbsolutePitchFactory.MAX_OCTAVE; i++) {
        assertEquals(p.getName() + Integer.toString(i),
                AbsolutePitchFactory.newAbsolutePitch(p, i).getName());
      }
    }
  }

  /**
   * Test that equality is extensional
   */
  @Test
  public void testEquals() {
    for (RelativePitch p : RelativePitch.values()) {
      for (int i = 1; i <= AbsolutePitchFactory.MAX_OCTAVE; i++) {
        AbsolutePitch pitch = AbsolutePitchFactory.newAbsolutePitch(p, i);
        for (RelativePitch p2 : RelativePitch.values()) {
          for (int j = 1; j <= AbsolutePitchFactory.MAX_OCTAVE; j++) {
            assertEquals(p.equals(p2) && i == j,
                    pitch.equals(AbsolutePitchFactory.newAbsolutePitch(p2, j)));
          }
        }
      }
    }
  }

  /**
   * Test that equality implies equal hashes
   */
  @Test
  public void testEqualsImpliesHash() {
    for (RelativePitch p : RelativePitch.values()) {
      for (int i = 1; i <= AbsolutePitchFactory.MAX_OCTAVE; i++) {
        AbsolutePitch pitch = AbsolutePitchFactory.newAbsolutePitch(p, i);
        for (RelativePitch p2 : RelativePitch.values()) {
          for (int j = 1; j <= AbsolutePitchFactory.MAX_OCTAVE; j++) {
            AbsolutePitch pitch2 = AbsolutePitchFactory.newAbsolutePitch(p2, j);
            if (pitch.equals(pitch2)) {
              assertTrue(pitch.hashCode() == pitch2.hashCode());
            }
          }
        }
      }
    }
  }

  /**
   * Tests that changePitch transposes the relative pitch appropriately
   */
  @Test
  public void testTransposeAgreesWithRelative() {
    for (RelativePitch p : RelativePitch.values()) {
      for (int i = 1; i < AbsolutePitchFactory.MAX_OCTAVE - 2; i++) {
        AbsolutePitch pitch = AbsolutePitchFactory.newAbsolutePitch(p, i);
        for (int j = 0; j < RelativePitch.PITCH_COUNT; j++) {
          assertEquals(pitch.changePitch(j).getRelativePitch().getName(),
                  pitch.getRelativePitch().changePitch(j).getName());
        }
      }
    }
  }

  /**
   * Tests that changePitch within an octave stays within that octave
   */
  @Test
  public void testTransposeStaysWithinOctave() {
    for (int i = 1; i < AbsolutePitchFactory.MAX_OCTAVE; i++) {
      AbsolutePitch pitch = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, i);
      for (int j = 0; j < RelativePitch.PITCH_COUNT; j++) {
        assertEquals(pitch.getOctave(), pitch.changePitch(j).getOctave());
      }

    }
  }

  /**
   * Tests that changePitch within an octave stays within that octave
   */
  @Test
  public void testTransposeBy12MovesOctave() {
    for (int i = 1; i < AbsolutePitchFactory.MAX_OCTAVE; i++) {
      AbsolutePitch pitch1 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, i);
      AbsolutePitch pitch2 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.FSharp, i);
      for (int j = 0; j < AbsolutePitchFactory.MAX_OCTAVE - i; j++) {
        assertEquals(pitch1.getOctave() + j, pitch1.changePitch(j * 12).getOctave());
        assertEquals(pitch2.getOctave() + j, pitch2.changePitch(j * 12).getOctave());
      }

    }
  }

  /**
   * Tests that changePitch within an octave stays within that octave
   */
  @Test
  public void testTransposeByMinus12MovesOctave() {
    for (int i = 1; i < AbsolutePitchFactory.MAX_OCTAVE; i++) {
      AbsolutePitch pitch1 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, i);
      AbsolutePitch pitch2 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.FSharp, i);
      for (int j = 1; j < i; j++) {
        assertEquals(pitch1.getOctave() - j, pitch1.changePitch(j * -12).getOctave());
        assertEquals(pitch2.getOctave() - j, pitch2.changePitch(j * -12).getOctave());
      }

    }
  }

  /**
   * Tests that transposing up between octaves results in a "greater than" relationship
   */
  @Test
  public void nextTest() {
    AbsolutePitch test = SamplePitches.FSharp9.changePitch(10);

    assertEquals(RelativePitch.E, test.getRelativePitch());
    assertEquals(10, test.getOctave());
    assertTrue(test.compareTo(SamplePitches.FSharp9) > 0);
  }

  @Test
  public void getIntValueTest() {
    AbsolutePitch test = SamplePitches.FSharp9.changePitch(10);
    AbsolutePitch pitchc4 = SamplePitches.C4;
    assertEquals(RelativePitch.E, test.getRelativePitch());
    assertEquals(10, test.getOctave());
    assertEquals("E10", test.getName());
    assertEquals(136, test.getIntValue());
    assertEquals(60, pitchc4.getIntValue());
    assertTrue(test.compareTo(SamplePitches.FSharp9) > 0);
  }

}