package cs3500.music.tests;

import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.MusicPieceFactory;
import cs3500.music.model.MusicPieceModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteFactory;
import cs3500.music.model.RelativePitch;
import cs3500.music.view.MutableViewModel;
import cs3500.music.view.ViewModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the implementation of the MutableViewModel and ViewModel interfaces
 */
public class MutableViewModelTest {

  /**
   * Tests that making a new view model does not error
   */
  @Test
  public void testMakeModel() {
    MutableViewModel.newViewModel(MusicPieceFactory.newEmptyPiece(1000));
  }

  /**
   * Tests that making a new view model with a null argument errors
   */
  @Test(expected = NullPointerException.class)
  public void testMakeNullModel() {
    MutableViewModel.newViewModel(null);
  }

  /**
   * Tests the default values of the getter methods
   */
  @Test
  public void testModelDefaults() {
    ViewModel m = MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());
    assertEquals(0, m.getCurrentBeat());
    assertEquals(0, m.getViewStart());
    assertEquals(0, m.getViewTop());
    assertEquals(null, m.getHeldNote());
    assertEquals(null, m.getSelectedPoint());
  }

  /**
   * Tests that methods delegate to piece
   */
  @Test
  public void testModelDelegates() {
    MusicPieceModel p = SamplePitches.LightlyRow4Measures();
    ViewModel m = MutableViewModel.newViewModel(p);
    assertEquals(p.getTempo(), m.getTempo());
    assertEquals(p.getLength(), m.getLength());
    assertEquals(p.highestPitch(), m.highestPitch());
    assertEquals(p.lowestPitch(), m.lowestPitch());
    for (int i = 0; i < p.getLength(); i++) {
      assertEquals(p.getNotesAt(i), m.getNotesAt(i));
    }

  }

  /**
   * Tests the default values of the getter methods
   */
  @Test
  public void testModelSetters() {
    Note testNote = NoteFactory.newNote(1, 2, 3, RelativePitch.A, 4, 5);
    MutableViewModel m = MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());
    //defaults
    assertEquals(0, m.getCurrentBeat());
    assertEquals(0, m.getViewStart());
    assertEquals(0, m.getViewTop());
    assertEquals(null, m.getHeldNote());
    assertEquals(null, m.getSelectedPoint());

    //set
    m.setCurrentBeat(20);
    m.setViewStart(10);
    m.setViewTop(11);
    m.setHeldNote(testNote);
    m.setSelectedPoint(new Point(4, 5));

    assertEquals(16, m.getCurrentBeat());
    assertEquals(10, m.getViewStart());
    assertEquals(11, m.getViewTop());
    assertEquals(testNote, m.getHeldNote());
    assertEquals(new Point(4, 5), m.getSelectedPoint());

  }

  /**
   * Tests that addNote() adds a note to the piece
   */
  @Test
  public void testAddNote() {
    Note testNote = NoteFactory.newNote(4, 20, 5, RelativePitch.FSharp, 1, 30);
    MusicPieceModel p = MusicPieceFactory.newEmptyPiece(203000);
    MutableViewModel m = MutableViewModel.newViewModel(p);
    m.addNote(testNote);

    //test viewmodel mutates
    assertEquals(testNote, m.getNotesAt(4).get(0));
    assertEquals(testNote, m.getNotesAt(8).get(0));
    assertEquals(testNote, m.getNotesAt(16).get(0));
    assertEquals(testNote, m.getNotesAt(19).get(0));

    //test piece mutates
    assertEquals(testNote, p.getNotesAt(4).get(0));
    assertEquals(testNote, p.getNotesAt(8).get(0));
    assertEquals(testNote, p.getNotesAt(16).get(0));
    assertEquals(testNote, p.getNotesAt(19).get(0));

  }

  /**
   * Tests that removeNote() removes a note from the piece
   */
  @Test
  public void testRemoveNote() {
    Note testNote = NoteFactory.newNote(3, 19, 4, RelativePitch.FSharp, 1, 60);
    MusicPieceModel p = MusicPieceFactory.newEmptyPiece(20600);
    MutableViewModel m = MutableViewModel.newViewModel(p);
    m.addNote(testNote);

    //test viewmodel mutates
    assertEquals(testNote, m.getNotesAt(4).get(0));
    assertEquals(testNote, m.getNotesAt(8).get(0));
    assertEquals(testNote, m.getNotesAt(16).get(0));
    assertEquals(testNote, m.getNotesAt(19).get(0));

    //test piece mutates
    assertEquals(testNote, p.getNotesAt(4).get(0));
    assertEquals(testNote, p.getNotesAt(8).get(0));
    assertEquals(testNote, p.getNotesAt(16).get(0));
    assertEquals(testNote, p.getNotesAt(19).get(0));

    m.removeNote(testNote);

    //test viewmodel mutates
    assertEquals(0, m.getNotesAt(4).size());
    assertEquals(0, m.getNotesAt(8).size());
    assertEquals(0, m.getNotesAt(16).size());
    assertEquals(0, m.getNotesAt(19).size());

    //test piece mutates
    assertEquals(0, p.getNotesAt(4).size());
    assertEquals(0, p.getNotesAt(8).size());
    assertEquals(0, p.getNotesAt(16).size());
    assertEquals(0, p.getNotesAt(19).size());

  }

  /**
   * Tests that addNote() adds a note to the piece
   */
  @Test
  public void testChangeDuration() {
    Note testNote = NoteFactory.newNote(1, 20, 5, RelativePitch.C, 1, 30);
    MusicPieceModel p = MusicPieceFactory.newEmptyPiece(56000);
    MutableViewModel m = MutableViewModel.newViewModel(p);
    m.addNote(testNote);
    m.changeNoteDuration(testNote, 5);

    Note resNote = testNote.addNoteDuration(5);

    //test viewmodel mutates
    assertEquals(resNote, m.getNotesAt(4).get(0));
    assertEquals(resNote, m.getNotesAt(8).get(0));
    assertEquals(resNote, m.getNotesAt(16).get(0));
    assertEquals(resNote, m.getNotesAt(19).get(0));

    //test piece mutates
    assertEquals(resNote, p.getNotesAt(4).get(0));
    assertEquals(resNote, p.getNotesAt(8).get(0));
    assertEquals(resNote, p.getNotesAt(16).get(0));
    assertEquals(resNote, p.getNotesAt(19).get(0));

  }

  /**
   * Tests the viewmodel calls its updates
   */
  @Test
  public void testRegisterUpdates() {
    Note testNote = NoteFactory.newNote(4, 20, 5, RelativePitch.FSharp, 1, 30);
    MutableViewModel m = MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());
    final List<String> calls = new ArrayList<>();
    m.registerUpdates((timer) -> calls.add("beat"),
            () -> calls.add("update"));

    //events
    m.setCurrentBeat(0);
    m.setCurrentBeat(1);
    m.run(null, 5);
    m.setViewStart(2);
    m.run(null, 6);
    m.setViewTop(5);
    m.addNote(testNote);


    assertEquals(Arrays.asList("beat", "update", "beat", "update", "update"), calls);
  }

  /**
   * Tests the viewmodel calls its updates
   */
  @Test
  public void testMultipleUpdates() {
    MutableViewModel m = MutableViewModel.newViewModel(SamplePitches.LightlyRow4Measures());
    final List<String> calls = new ArrayList<>();
    m.registerUpdates((timer) -> calls.add("beat1"),
            () -> calls.add("update1"));
    m.registerUpdates((timer) -> calls.add("beat2"),
            () -> calls.add("update2"));

    //events
    m.run(null, 5);
    m.setViewStart(2);

    assertEquals(Arrays.asList("beat1", "beat2", "update1", "update2"), calls);
  }

}