package cs3500.music.tests;

import cs3500.music.model.RelativePitch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cs3500.music.model.RelativePitch.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the methods of the RelativePitch enum
 */
public class RelativePitchTest {

    /**
     * Tests that all less-than compares return less than 0
     */
    @Test
    public void testCompareToLess() {
        List<RelativePitch> earlierPitches = new ArrayList<>();
        for(RelativePitch p : RelativePitch.values()) {
            assertTrue(earlierPitches.parallelStream().allMatch((ep) -> ep.compareTo(p) < 0));
            earlierPitches.add(p);
        }
    }

    /**
     * Tests that all pitches are equal to themselves
     */
    @Test
    public void testCompareToEqual() {
        for(RelativePitch p : RelativePitch.values()) {
            assertEquals(p.compareTo(p), 0);
        }
    }

    /**
     * Tests that all greater-than comparisons work correctly
     */
    @Test
    public void testCompareToGreater() {
        List<RelativePitch> earlierPitches = new ArrayList<>();
        for(RelativePitch p : RelativePitch.values()) {
            assertTrue(earlierPitches.parallelStream().allMatch((ep) -> p.compareTo(ep) > 0));
            earlierPitches.add(p);
        }
    }

    /**
     * These tests check that each note has the correct name
     */
    @Test
    public void testGetNameC() {
        assertEquals("C", C.getName());
    }
    @Test
    public void testGetNameCSharp() {
        assertEquals("C#", CSharp.getName());
    }
    @Test
    public void testGetNameD() {
        assertEquals("D", D.getName());
    }
    @Test
    public void testGetNameDSharp() {
        assertEquals("D#", DSharp.getName());
    }
    @Test
    public void testGetNameE() {
        assertEquals("E", E.getName());
    }
    @Test
    public void testGetNameF() {
        assertEquals("F", F.getName());
    }
    @Test
    public void testGetNameFSharp() {
        assertEquals("F#", FSharp.getName());
    }
    @Test
    public void testGetNameG() {
        assertEquals("G", G.getName());
    }
    @Test
    public void testGetNameGSharp() {
        assertEquals("G#", GSharp.getName());
    }
    @Test
    public void testGetNameA() {
        assertEquals("A", A.getName());
    }
    @Test
    public void testGetNameASharp() {
        assertEquals("A#", ASharp.getName());
    }
    @Test
    public void testGetNameB() {
        assertEquals("B", B.getName());
    }

    /**
     * Tests that transposing by one half step up
     * makes all notes but B (since it wraps around) greater
     */
    @Test
    public void testCompareTransposeHalfStepUp() {
        for (RelativePitch p : RelativePitch.values()) {
            RelativePitch nextPitch = p.changePitch(1);
            if (!p.equals(B)) {
                assertTrue(p.compareTo(nextPitch) < 0);
            } else {
                assertTrue(p.compareTo(nextPitch) > 0);
            }
        }
    }

    /**
     * Tests that transposing by one half step down
     * makes all notes but C (since it wraps around) lesser
     */
    @Test
    public void testCompareTransposeHalfStepDown() {
        for (RelativePitch p : RelativePitch.values()) {
            RelativePitch nextPitch = p.changePitch(-1);
            if (!p.equals(C)) {
                assertTrue(p.compareTo(nextPitch) > 0);
            } else {
                assertTrue(p.compareTo(nextPitch) < 0);
            }
        }
    }

    /**
     * Tests that transposing by 12 half steps produces an equal note
     */
    @Test
    public void testTransposeByOctave() {
        for (RelativePitch p : RelativePitch.values()) {
            assertEquals(p, p.changePitch(12));
        }
    }

    /**
     * Tests that transposing around the circle of fifths reaches all notes
     */
    @Test
    public void testTransposeCircleOfFifths() {
        RelativePitch current = C;
        Set<RelativePitch> reached = new HashSet<>();
        for (int i = 0; i < 12; i++) {
            reached.add(current);
            current = current.changePitch(7);
        }
        assertEquals(C, current);
        assertEquals(12, reached.size());
    }

    /**
     * Tests that transposing by 0 half steps produces an equal note
     */
    @Test
    public void testTransposeIdentity() {
        for (RelativePitch p : RelativePitch.values()) {
            assertEquals(p, p.changePitch(0));
        }
    }

    /**
     * Tests that transposing down by 12 half steps produces an equal note
     */
    @Test
    public void testTransposeDownByOctave() {
        for (RelativePitch p : RelativePitch.values()) {
            assertEquals(p, p.changePitch(-12));
        }
    }

}