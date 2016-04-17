package cs3500.music.tests;

import org.junit.Assert;
import org.junit.Test;

import cs3500.music.model.AbsolutePitchFactory;
import cs3500.music.model.MusicPieceFactory;
import cs3500.music.model.MusicPieceModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteFactory;
import cs3500.music.model.RelativePitch;

import static org.junit.Assert.assertEquals;

/**
 * Tests the AbstractMusicPiece abstract class
 */
public class MusicPieceModelFactoryTest {


  /**
   * Test that an empty piece has length 0
   */
  @Test
  public void testEmptyLengthIsZero() {
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(8000);
    assertEquals(0, m.getLength());
  }

  /**
   * Test that an empty piece has length 0 after a valid call to removeNoteAtPitch
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyRemoveIsError() {
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(8000);
    assertEquals(0, m.getLength());
    m.removeNote(NoteFactory.newNote(20, 1,
            AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, 4), 0, 100));
  }

  /**
   * Test that an empty piece has length 0 after a valid call to moveNoteAtPitch
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyChageStartTimeIsError() {
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(9000);
    assertEquals(0, m.getLength());
    m.changeNoteStartTime(NoteFactory.newNote(2, 30,
            AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, 4), 0, 100), 12);
  }

  /**
   * Test that an empty piece has length 0 after a valid call to transposeNoteAtPitch
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyLengthChangePitchIsError() {
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(9000);
    assertEquals(0, m.getLength());
    m.changePitch(NoteFactory.newNote(2, 4,
            AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, 4), 0, 100), 10);
  }


  /**
   * Test that inserting a piece at a negative beat results in an error
   */
  @Test(expected = IllegalArgumentException.class)
  public void testErrorOnNegativeInsert() {
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(9000);
    m.insert(MusicPieceFactory.newEmptyPiece(9000), -1);
  }

  /**
   * Test that moving a note to a negative beat results in an error
   */
  @Test(expected = IllegalArgumentException.class)
  public void testErrorOnNegativeMove() {
    Note note = NoteFactory.newNote(2, 3, SamplePitches.A4, 0, 100);
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(9000).addNote(note);
    m.changeNoteStartTime(note, -3);
  }


  /**
   * Test that moving a note at a negative beat results in an error
   */
  @Test(expected = IllegalArgumentException.class)
  public void testErrorOnNegativeTranspose() {
    Note note = NoteFactory.newNote(2, 2, SamplePitches.C0.changePitch(-12), 0, 100);
    MusicPieceModel m = MusicPieceFactory.newEmptyPiece(9000).addNote(note);
    m.changePitch(note, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTempoBelowMin() {
    MusicPieceFactory.newEmptyPiece(90);
  }

  /**
   * Test that transposing a metronome moves each note
   */
  @Test
  public void testTransposeMetronome() {
    MusicPieceModel m = MusicPieceFactory
            .newMetronome(SamplePitches.FSharp10, 3, 5, 8000)
            .changePitch(-12);
    for (int i = 0; i < 5; i++) {
      for (Note n : m.getNotesAt(i)) {
        assertEquals(SamplePitches.FSharp9, n.getPitch());
      }
    }
  }

  /**
   * Test that the first note added to an empty piece becomes the lowest
   */
  @Test
  public void testFirstNoteIsLowest() {
    MusicPieceModel m = MusicPieceFactory
            .newEmptyPiece(9000)
            .addNote(NoteFactory.newNote(6, 3, SamplePitches.D3, 0, 100));
    Assert.assertEquals(SamplePitches.D3, m.lowestPitch());
  }

  /**
   * Test that the first note added to an empty piece becomes the highest
   */
  @Test
  public void testFirstNoteIsHighest() {
    MusicPieceModel m = MusicPieceFactory
            .newEmptyPiece(9000)
            .addNote(NoteFactory.newNote(6, 3, SamplePitches.D3, 0, 100));
    Assert.assertEquals(SamplePitches.D3, m.highestPitch());
  }

  /**
   * Test that adding a high note to a metronome changes the highest pitch
   */
  @Test
  public void testAddHigherNote() {
    MusicPieceModel m = MusicPieceFactory
            .newMetronome(SamplePitches.A4, 5, 10, 8000)
            .addNote(NoteFactory.newNote(3, 3, SamplePitches.FSharp9, 0, 100));
    Assert.assertEquals(SamplePitches.FSharp9, m.highestPitch());
  }

  /**
   * Test that adding a low note to a metronome changes the highest pitch
   */
  @Test
  public void testAddLowerNote() {
    MusicPieceModel m = MusicPieceFactory
            .newMetronome(SamplePitches.A4, 3, 11, 8000)
            .addNote(NoteFactory.newNote(6, 3, SamplePitches.D3, 0, 100));
    Assert.assertEquals(SamplePitches.D3, m.lowestPitch());
  }


}