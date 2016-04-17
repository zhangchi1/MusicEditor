package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.NoteFactory;
import cs3500.music.model.RelativePitch;

import static org.junit.Assert.assertEquals;

/**
 * Created by chizhang on 3/2/16.
 */
public class NoteImplTest {
  //Note(startTime, duration, octave, pitch)
  Note noteG3_0 = NoteFactory.newNote(0, 7, 3, RelativePitch.G, 0, 0);
  Note noteG3_8 = NoteFactory.newNote(8, 7, 3, RelativePitch.G, 0, 0);
  Note noteG3_16 = NoteFactory.newNote(16, 7, 3, RelativePitch.G, 0, 0);
  Note noteG3_24 = NoteFactory.newNote(24, 2, 3, RelativePitch.G, 0, 0);
  Note noteG3_32 = NoteFactory.newNote(32, 8, 3, RelativePitch.G, 0, 0);
  Note noteG3_40 = NoteFactory.newNote(40, 8, 3, RelativePitch.G, 0, 0);
  Note noteG3_48 = NoteFactory.newNote(48, 8, 3, RelativePitch.G, 0, 0);
  Note noteG3_56 = NoteFactory.newNote(56, 8, 3, RelativePitch.E, 0, 0);

  Note noteE4_0 = NoteFactory.newNote(0, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_8 = NoteFactory.newNote(8, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_10 = NoteFactory.newNote(10, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_12 = NoteFactory.newNote(12, 3, 4, RelativePitch.E, 0, 0);
  Note noteE4_24 = NoteFactory.newNote(24, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_32 = NoteFactory.newNote(32, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_40 = NoteFactory.newNote(40, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_42 = NoteFactory.newNote(42, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_44 = NoteFactory.newNote(44, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_46 = NoteFactory.newNote(46, 2, 4, RelativePitch.E, 0, 0);
  Note noteE4_52 = NoteFactory.newNote(52, 2, 4, RelativePitch.E, 0, 0);

  Note noteD4_2 = NoteFactory.newNote(2, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_6 = NoteFactory.newNote(6, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_16 = NoteFactory.newNote(16, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_18 = NoteFactory.newNote(18, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_20 = NoteFactory.newNote(20, 4, 4, RelativePitch.D, 0, 0);
  Note noteD4_34 = NoteFactory.newNote(34, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_38 = NoteFactory.newNote(38, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_48 = NoteFactory.newNote(48, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_50 = NoteFactory.newNote(50, 2, 4, RelativePitch.D, 0, 0);
  Note noteD4_54 = NoteFactory.newNote(54, 2, 4, RelativePitch.D, 0, 0);

  Note noteC4_4 = NoteFactory.newNote(4, 2, 4, RelativePitch.C, 0, 0);
  Note noteC4_36 = NoteFactory.newNote(36, 2, 4, RelativePitch.C, 0, 0);
  Note noteC4_56 = NoteFactory.newNote(56, 8, 4, RelativePitch.C, 0, 0);

  //Order: C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;
  @Test
  public void testGetPitch() {
    assertEquals(SamplePitches.G3, noteG3_0.getPitch());
    assertEquals(SamplePitches.G3, noteG3_8.getPitch());
    assertEquals(SamplePitches.G3, noteG3_24.getPitch());
    assertEquals(SamplePitches.E4, noteE4_0.getPitch());
    assertEquals(SamplePitches.E4, noteE4_8.getPitch());
    assertEquals(SamplePitches.E4, noteE4_24.getPitch());
    assertEquals(SamplePitches.D4, noteD4_2.getPitch());
    assertEquals(SamplePitches.D4, noteD4_18.getPitch());
    assertEquals(SamplePitches.D4, noteD4_20.getPitch());
    assertEquals(SamplePitches.C4, noteC4_4.getPitch());
    assertEquals(SamplePitches.C4, noteC4_36.getPitch());
  }

  @Test
  public void testgetPitchLocation() {
    assertEquals(60, noteC4_4.getPitchLocation());
    assertEquals(60, noteC4_56.getPitchLocation());
    assertEquals(62, noteD4_2.getPitchLocation());
    assertEquals(62, noteD4_16.getPitchLocation());
    assertEquals(64, noteE4_0.getPitchLocation());
    assertEquals(64, noteE4_12.getPitchLocation());
    assertEquals(64, noteE4_32.getPitchLocation());
  }

  @Test
  public void testgetinstrument() {
    Note noteC4_56 = NoteFactory.newNote(56, 8, 4, RelativePitch.C, 1, 0);
    assertEquals(0, noteC4_4.getInstrument());
    assertEquals(0, noteD4_2.getInstrument());
    assertEquals(0, noteD4_16.getInstrument());
    assertEquals(0, noteE4_0.getInstrument());
    assertEquals(0, noteE4_12.getInstrument());
    assertEquals(0, noteE4_32.getInstrument());
    assertEquals(1, noteC4_56.getInstrument());
  }

  @Test
  public void testgetVolume() {
    Note noteD4_6 = NoteFactory.newNote(6, 2, 4, RelativePitch.D, 0, 100);
    assertEquals(0, noteC4_4.getVolume());
    assertEquals(0, noteC4_56.getVolume());
    assertEquals(0, noteD4_2.getVolume());
    assertEquals(0, noteD4_16.getVolume());
    assertEquals(0, noteE4_0.getVolume());
    assertEquals(0, noteE4_12.getVolume());
    assertEquals(0, noteE4_32.getVolume());
    assertEquals(0, noteG3_56.getVolume());
    assertEquals(0, noteG3_0.getVolume());
    assertEquals(100, noteD4_6.getVolume());
  }


  @Test
  public void testAddStartTime() {
    assertEquals(1, noteG3_0.addStartTime(1).getStartTime());
    assertEquals(3, noteG3_16.addStartTime(-13).getStartTime());
    assertEquals(58, noteG3_56.addStartTime(2).getStartTime());
    assertEquals(0, noteG3_32.addStartTime(-32).getStartTime());
    assertEquals(66, noteC4_56.addStartTime(10).getStartTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddStartTime() {
    noteE4_10.addStartTime(-11);
    noteE4_24.addStartTime(-25);
    noteD4_50.addStartTime(-55);
    noteD4_48.addStartTime(-50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalNote() {
    Note note1 = NoteFactory.newNote(-1, 1, 1, RelativePitch.G, 0, 0);
    Note note2 = NoteFactory.newNote(-1, 0, 1, RelativePitch.G, 0, 0);
    Note note3 = NoteFactory.newNote(-1, 1, 11, RelativePitch.G, 0, 0);
    Note note4 = NoteFactory.newNote(-1, 1, -1, RelativePitch.G, 0, 0);
    Note note5 = NoteFactory.newNote(1, -1, 2, RelativePitch.G, 0, 0);
    Note note6 = NoteFactory.newNote(1, 1, 10, RelativePitch.G, 0, 0);
    note1.addStartTime(5);
    note2.addStartTime(2);
    assertEquals(note3, note3);
    note4.changePitch(1);
    note5.addNoteDuration(2);
    assertEquals(1, note6.getDuration());

  }

  @Test
  public void testAddNoteDuration() {
    assertEquals(16, noteG3_40.addNoteDuration(8).getDuration());
    assertEquals(1, noteG3_48.addNoteDuration(-7).getDuration());
    assertEquals(4, noteD4_54.addNoteDuration(2).getDuration());
    assertEquals(12, noteE4_12.getStartTime());
    assertEquals(54, noteD4_54.addNoteDuration(2).getStartTime());
    assertEquals(48, noteG3_48.addNoteDuration(-7).getStartTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddNoteDuration() {
    noteE4_44.addNoteDuration(-2);
    noteE4_46.addNoteDuration(-5);
    noteE4_52.addNoteDuration(-3);
  }

  @Test
  public void testChangePitch() {
    assertEquals(SamplePitches.D3, noteD4_16.changePitch(-12).getPitch());
    assertEquals(SamplePitches.GSharp4, noteE4_32.changePitch(4).getPitch());
    assertEquals(SamplePitches.C4, noteD4_6.changePitch(-2).getPitch());
    assertEquals(SamplePitches.E4, noteD4_38.changePitch(2).getPitch());
    assertEquals(SamplePitches.C0, noteE4_12.changePitch(-52).getPitch());
    assertEquals(12, noteE4_12.changePitch(-52).getStartTime());
    assertEquals(2, noteE4_32.changePitch(4).getDuration());
    assertEquals(12, noteE4_12.changePitch(-52).getStartTime());
    assertEquals(32, noteG3_32.changePitch(15).getStartTime());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangePitch() {
    noteE4_12.changePitch(-53);
    noteE4_42.changePitch(70);
    noteD4_34.changePitch(-55);
    noteE4_40.changePitch(-57);
    noteD4_16.changePitch(-66);
  }

  @Test
  public void testEquals() {
    assertEquals(true, noteG3_0.equals(noteG3_0));
    assertEquals(true, noteG3_32.equals(noteG3_32));
    assertEquals(true, noteD4_2.equals(noteD4_2));
    assertEquals(true, noteD4_50.equals(noteD4_50));
    assertEquals(true, noteG3_48.equals(noteG3_48));
    assertEquals(false, noteE4_12.equals(noteG3_32));
    assertEquals(false, noteC4_4.equals(noteD4_2));
    assertEquals(false, noteD4_50.equals(noteG3_0));
    assertEquals(false, noteG3_48.equals(noteG3_0));
  }

  @Test
  public void testNotEquals() {
    assertEquals(false, noteC4_4.equals(null));
    assertEquals(false, noteC4_4.equals(RelativePitch.A));
  }

  @Test
  public void testHashCode() {
    assertEquals(NoteFactory.newNote(50, 2, 4, RelativePitch.D, 0, 0).hashCode(),
        noteD4_50.hashCode());
    assertEquals(NoteFactory.newNote(54, 2, 4, RelativePitch.D, 0, 0).hashCode(),
        noteD4_54.hashCode());
    assertEquals(NoteFactory.newNote(48, 8, 3, RelativePitch.G, 0, 0).hashCode(),
        noteG3_48.hashCode());
    assertEquals(NoteFactory.newNote(24, 2, 4, RelativePitch.E, 0, 0).hashCode(),
        noteE4_24.hashCode());
    assertEquals(NoteFactory.newNote(32, 8, 3, RelativePitch.G, 0, 0).hashCode(),
        noteG3_32.hashCode());
    assertEquals(NoteFactory.newNote(2, 2, 4, RelativePitch.D, 0, 0).hashCode(),
        noteD4_2.hashCode());
    assertEquals(NoteFactory.newNote(6, 2, 4, RelativePitch.D, 0, 0).hashCode(),
        noteD4_6.hashCode());
    assertEquals(NoteFactory.newNote(18, 2, 4, RelativePitch.D, 0, 0).hashCode(),
        noteD4_18.hashCode());

  }


}