package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.music.model.AbsolutePitchFactory;
import cs3500.music.model.MusicPieceFactory;
import cs3500.music.model.MusicPieceModel;
import cs3500.music.model.Note;
import cs3500.music.model.NoteFactory;
import cs3500.music.model.RelativePitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test all methods in MusicPieceModelImpl
 */
public class MusicPieceModelImplTest {
  Note noteG3_0 = NoteFactory.newNote(0, 7, 3, RelativePitch.G, 0, 0);
  Note noteG3_8 = NoteFactory.newNote(8, 7, 3, RelativePitch.G, 0, 0);
  Note noteG3_16 = NoteFactory.newNote(16, 7, 3, RelativePitch.G, 0, 0);
  Note noteG3_24 = NoteFactory.newNote(24, 2, 3, RelativePitch.G, 0, 0);
  Note noteG3_32 = NoteFactory.newNote(32, 8, 3, RelativePitch.G, 0, 0);
  Note noteG3_40 = NoteFactory.newNote(40, 8, 3, RelativePitch.G, 0, 0);
  Note noteG3_48 = NoteFactory.newNote(48, 8, 3, RelativePitch.G, 0, 0);
  Note noteG4_26 = NoteFactory.newNote(26, 2, 4, RelativePitch.G, 0, 0);
  Note noteG4_28 = NoteFactory.newNote(28, 4, 4, RelativePitch.G, 0, 0);
  Note noteE3_56 = NoteFactory.newNote(56, 8, 3, RelativePitch.E, 0, 0);

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
  List<Note> listNoteD = new ArrayList<>(Arrays.asList(noteD4_2, noteD4_16, noteD4_6, noteD4_18,
          noteD4_20, noteD4_34, noteD4_38, noteD4_18, noteD4_48, noteD4_50, noteD4_54));
  List<Note> listNoteE = new ArrayList<>(Arrays.asList(noteE4_0, noteE4_8, noteE4_10, noteE4_12,
          noteE4_24, noteE4_32, noteE4_40, noteE4_42, noteE4_44, noteE4_46, noteE4_52));
  List<Note> listNoteC = new ArrayList<>(Arrays.asList(noteC4_4, noteC4_36, noteC4_56));
  List<Note> listNoteG = new ArrayList<>(Arrays.asList(noteG3_0, noteG3_8, noteG3_16,
          noteG3_24, noteG3_32, noteG3_40, noteG3_48, noteE3_56));
  List<Note> listAllNotes = new ArrayList<>(Arrays.asList(noteD4_2, noteD4_16, noteD4_6, noteD4_18,
          noteD4_20, noteD4_34, noteD4_38, noteD4_18, noteD4_48, noteD4_50, noteD4_54, noteE4_0,
          noteE4_8, noteE4_10, noteE4_12, noteE4_24, noteE4_32, noteE4_40, noteE4_42, noteE4_44,
          noteE4_46, noteE4_52, noteC4_4, noteC4_36, noteC4_56, noteG3_0, noteG3_8, noteG3_16,
          noteG3_24, noteG3_24, noteG3_32, noteG3_40, noteG3_48, noteE3_56, noteG4_26, noteG4_28));

  MusicPieceModel modelD = MusicPieceFactory.newPieceFromList(listNoteD, 1000);
  MusicPieceModel model2 = MusicPieceFactory.newPieceFromList(new ArrayList<>(), 7000);
  MusicPieceModel modelC = MusicPieceFactory.newPieceFromList(listNoteC, 8000);
  MusicPieceModel model4 = MusicPieceFactory.newPieceFromList(listAllNotes, 9000);
  MusicPieceModel modelG = MusicPieceFactory.newPieceFromList(listNoteG, 7777);
  MusicPieceModel modelE = MusicPieceFactory.newPieceFromList(listNoteE, 8888);

  @Test
  public void testAddNote() {
    model2.addNote(noteC4_4);
    model2.addNote(noteC4_36);
    model2.addNote(noteC4_56);
    assertEquals(model2.getNotes(), modelC.getNotes());
  }

  @Test
  public void testAddNoteListG() {
    listNoteG.forEach(model2::addNote);
    assertEquals(modelG.getNotes(), model2.getNotes());
  }

  @Test
  public void testAddNoteListD() {
    listNoteD.forEach(model2::addNote);
    assertEquals(modelD.getNotes(), model2.getNotes());
  }

  @Test
  public void testAddNoteListE() {
    listNoteE.forEach(model2::addNote);
    assertEquals(modelE.getNotes(), model2.getNotes());
  }

  @Test
  public void testAddNoteAllNotes() {
    listAllNotes.forEach(model2::addNote);
    assertEquals(model4.getNotes(), model2.getNotes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalAddNote() {
    Note note1 = NoteFactory.newNote(-1, 1, 1, RelativePitch.G, 0, 0);
    Note note2 = NoteFactory.newNote(-1, 0, 1, RelativePitch.G, 0, 0);
    Note note3 = NoteFactory.newNote(-1, 1, 11, RelativePitch.G, 0, 0);
    model2.addNote(note1);
    model4.addNote(note2);
    modelC.addNote(note3);
  }

  //test for remove note
  @Test
  public void testRemoveAllNotes() {
    listNoteC.forEach(modelC::removeNote);
    listNoteE.forEach(modelE::removeNote);
    listNoteD.forEach(modelD::removeNote);
    listNoteG.forEach(modelG::removeNote);
    assertEquals(new ArrayList<Note>(), modelC.getNotes());
    assertEquals(new ArrayList<Note>(), modelE.getNotes());
    assertEquals(new ArrayList<Note>(), modelD.getNotes());
    assertEquals(new ArrayList<Note>(), modelG.getNotes());
  }

  @Test
  public void testRemoveNotes() {
    modelC.removeNote(noteC4_36);
    assertEquals(false, modelC.getNotes().contains(noteC4_36));
    modelD.removeNote(noteD4_6);
    modelD.removeNote(noteD4_34);
    modelD.removeNote(noteD4_50);
    assertEquals(false, modelD.getNotes().contains(noteD4_6));
    assertEquals(false, modelD.getNotes().contains(noteD4_34));
    assertEquals(false, modelD.getNotes().contains(noteD4_50));
    modelG.removeNote(noteG3_0);
    modelG.removeNote(noteG3_8);
    modelG.removeNote(noteG3_16);
    modelG.removeNote(noteG3_24);
    modelG.removeNote(noteG3_40);

    //tests that adding notes back works
    modelG.addNote(noteG3_24);
    modelG.addNote(noteC4_36);
    assertEquals(true, modelG.getNotes().containsAll(Arrays.asList(noteG3_24, noteC4_36)));

    new ArrayList<>(Arrays.asList(noteG3_0, noteG3_8, noteG3_16,
            noteG3_24, noteG3_32, noteG3_40, noteG3_48, noteE3_56));
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalRemove() {
    model2.removeNote(noteC4_36);
    model2.removeNote(noteD4_50);
    model2.removeNote(noteG3_24);
    modelG.removeNote(noteD4_50);
    modelG.removeNote(noteC4_36);
    modelD.removeNote(noteG3_24);
    modelC.removeNote(noteC4_36);
    modelC.removeNote(noteC4_4);
    modelC.removeNote(noteC4_56);
    modelC.removeNote(noteC4_36);
  }

  //test ChangeNoteStartTime method
  @Test
  public void testChangeNoteStartTime() {
    modelD.changeNoteStartTime(noteD4_50, 5);
    assertTrue(modelD.getNotesAt(55).contains(noteD4_50.addStartTime(5)));
    modelG.changeNoteStartTime(noteG3_40, -5);
    assertTrue(modelG.getNotesAt(36).contains(noteG3_40.addStartTime(-5)));
    modelC.changeNoteStartTime(noteC4_36, -36);
    assertTrue(modelC.getNotesAt(0).contains(noteC4_36.addStartTime(-36)));
    modelE.changeNoteStartTime(noteE4_10, 10);
    assertTrue(modelE.getNotesAt(20).contains(noteE4_10.addStartTime(10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeNoteStartTime() {
    modelG.changeNoteStartTime(noteG3_40, -41);
    modelC.changeNoteStartTime(noteC4_36, -40);
    modelE.changeNoteStartTime(noteE3_56, -100);
    modelD.changeNoteStartTime(noteD4_50, -51);

  }

  @Test
  public void testChangeNoteDuration() {
    modelD.changeNoteDuration(noteD4_50, 4);
    assertEquals(true, modelD.getNotesAt(50).contains(noteD4_50.addNoteDuration(4)));
    modelC.changeNoteDuration(noteC4_36, 2);
    assertEquals(true, modelC.getNotesAt(36).contains(noteC4_36.addNoteDuration(2)));
    modelG.changeNoteDuration(noteG3_40, 16);
    assertEquals(true, modelG.getNotesAt(40).contains(noteG3_40.addNoteDuration(16)));
    modelE.changeNoteDuration(noteE4_10, 10);
    assertEquals(true, modelE.getNotesAt(10).contains(noteE4_10.addNoteDuration(10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeNoteDuration() {
    modelG.changeNoteDuration(noteG3_40, -8);
    modelE.changeNoteDuration(noteE4_8, -3);
    modelC.changeNoteDuration(noteC4_56, -10);
  }

  //Test for ChangePitchLocation
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangePitch() {
    modelE.changePitch(noteE4_8, 121);
    modelG.changePitch(noteG3_40, -44);
    model2.changePitch(noteD4_50, 122);
    modelC.changePitch(noteC4_56, 120);
    model4.changePitch(noteD4_6, -50);
  }

  // C C♯ D D♯ E F F♯ G G♯ A A♯ B
  @Test
  public void testChangePitch() {
    model4.changePitch(noteD4_6, 2);
    assertEquals(true, model4.getNotesAt(6).contains(noteD4_6.changePitch(2)));
    modelC.changePitch(noteC4_56, 9);
    assertEquals(true, modelC.getNotesAt(56).contains(noteC4_56.changePitch(9)));
    modelG.changePitch(noteG3_40, 14);
    assertEquals(true, modelG.getNotesAt(40).contains(noteG3_40.changePitch(14)));
  }

  @Test
  public void testChangePitchWithPlaySim() {
    modelC.changePitch(noteC4_36, 33);
    assertEquals(true, modelC.getNotesAt(36).contains(noteC4_36.changePitch(33)));
    modelC.playSimultaneously(modelE);
    assertEquals(64, modelC.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, 6), modelC.highestPitch());
    modelG.changeNoteStartTime(noteG3_40, 2);
    assertTrue(modelG.getNotesAt(42).contains(noteG3_40.addStartTime(2)));
  }

  /**
   * Test getNotesAt
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGetNotesAt() {
    model4.getNotesAt(-1);
    modelG.getNotesAt(-2);
  }

  @Test
  public void testGetNotesAt() {
    assertEquals(new ArrayList<>(Arrays.asList(noteG3_0)), modelG.getNotesAt(0));
    assertTrue(model4.getNotesAt(1).containsAll(Arrays.asList(noteE4_0, noteG3_0)));
    assertEquals(2, model4.getNotesAt(1).size());
    assertTrue(model4.getNotesAt(2).containsAll(Arrays.asList(noteG3_0, noteD4_2)));
    assertEquals(2, model4.getNotesAt(2).size());
  }

  @Test
  public void testPlaySimultaneouslySameModel() {
    model4.playSimultaneously(model4);
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4), model4.highestPitch());
    assertEquals(64, model4.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), model4.lowestPitch());
    modelC.playSimultaneously(modelC);
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.lowestPitch());
    assertEquals(64, modelC.getLength());
  }

  @Test
  public void testPlaySimultaneouslyDifferentModels() {
    modelC.playSimultaneously(modelD);
    assertEquals(64, modelC.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4), modelC.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.lowestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.lowestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.highestPitch());
    assertEquals(54, modelE.getLength());
    modelE.playSimultaneously(modelG);   //play modelE and modelG simultaneously
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), modelE.lowestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.highestPitch());
    assertEquals(64, modelE.getLength());
  }

  //Test for playConsecutively

  @Test
  public void testPlayConsecutivelySameModel() {
    assertEquals(64, modelC.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.lowestPitch());
    modelC.playConsecutively(modelC);
    assertEquals(128, modelC.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.lowestPitch());

    assertEquals(54, modelE.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.lowestPitch());
    modelE.playConsecutively(modelE);
    assertEquals(108, modelE.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.lowestPitch());
  }

  @Test
  public void testPlayConsecutivelyDifferentModels() {
    assertEquals(64, modelC.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.lowestPitch());
    modelC.playConsecutively(modelG);  //play modelC and modelG consecutively
    assertEquals(64, modelG.getLength());
    assertEquals(128, modelC.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4), modelC.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), modelC.lowestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), modelG.lowestPitch());

  }

  @Test
  public void testPlayConsecutiveModelDandG() {
    assertEquals(56, modelD.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4), modelD.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4), modelD.lowestPitch());
    modelD.playConsecutively(modelG); //play modelE and model4 consecutively
    assertEquals(64, modelG.getLength());
    assertEquals(120, modelD.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4), modelD.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), modelD.lowestPitch());
  }

  @Test
  public void testPlayConsecutivelyModel4andE() {
    assertEquals(64, model4.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4), model4.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), model4.lowestPitch());
    model4.playConsecutively(modelE); //play modelE and model4 consecutively
    assertEquals(54, modelE.getLength());
    assertEquals(118, model4.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4), model4.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), model4.lowestPitch());
  }

  @Test
  public void testPlayTwoWaysTogether() {
    assertEquals(54, modelE.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4), modelE.lowestPitch());
    modelE.playSimultaneously(model4);
    assertEquals(64, modelE.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4), modelE.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), modelE.lowestPitch());
    modelE.playConsecutively(modelC);
    assertEquals(64, modelC.getLength());
    assertEquals(128, modelE.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4), modelE.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), modelE.lowestPitch());
  }

  @Test
  public void testNoNotesModel() {
    model2.playSimultaneously(model4);
    assertEquals(64, model2.getLength());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4), model2.highestPitch());
    assertEquals(AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 3), model2.lowestPitch());
  }

  @Test
  public void testExtraNotes() {
    Note note1 = NoteFactory.newNote(70, 3, 3, RelativePitch.A, 0, 0);
    modelG.addNote(note1);
    assertEquals(73, modelG.getLength());
  }

  @Test
  public void testCopy() {
    Note note1 = NoteFactory.newNote(70, 3, 3, RelativePitch.A, 0, 0);
    modelG.addNote(note1);
    assertEquals(modelG.addNote(note1).getLength(), modelG.copy().getLength());
    assertEquals(modelG.addNote(note1).getNotes(), modelG.copy().getNotes());
  }

  @Test
  public void testInsert() {
    assertEquals(modelG.insert(modelE, 0), modelG.playSimultaneously(modelE));
    assertEquals(modelG.insert(modelE, modelG.getLength()), modelG.playConsecutively(modelE));
  }

  /**
   * Test build method
   */
  @Test
  public void testBuild() {
    assertEquals(modelE, modelE.build());
    assertEquals(modelC, modelC.build());
    assertEquals(modelG, modelG.build());
    assertEquals(model2, model2.build());
    assertEquals(model4, model4.build());
  }

  @Test
  public void testSetTempo() {
    assertEquals(4000, model4.setTempo(4000).getTempo());
  }

  @Test
  public void testAddNoteNoteG3() {
    //(int start, int end, int instrument, int pitch, int volume)
    model2.addNote(0, 7, 0, 55, 0);
    assertEquals(new ArrayList<>(Arrays.asList(noteG3_0)), model2.getNotes());
  }

  @Test
  public void testAddNoteGetNoteC4() {
    model2.addNote(4, 6, 0, 60, 0);
    //startTime, duration, pitch, instrument, volume
    Note noteC4_4 = NoteFactory.newNote(4, 2, 4, RelativePitch.C, 0, 0);
    assertEquals(new ArrayList<>(Arrays.asList(noteC4_4)), model2.getNotes());
  }

  @Test
  public void testAddNoteGetNoteD4() {
    model2.addNote(2, 4, 0, 62, 0);

    assertEquals(new ArrayList<>(Arrays.asList(noteD4_2)), model2.getNotes());
  }

  @Test
  public void testAddTempoCheckTempo() {
    model2.setTempo(7000);
    assertEquals(7000, model2.getTempo());
    model4.setTempo(8000);
    assertEquals(8000, model4.getTempo());
    assertEquals(7777, modelG.getTempo());
    modelD.setTempo(9999);
    assertEquals(9999, modelD.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempo999() {
    modelG.setTempo(999);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempo0() {
    modelG.setTempo(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTempoNegative1() {
    modelG.setTempo(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveNoteAtValidBeat() {
    MusicPieceFactory.newEmptyPiece(1000)
            .addNote(NoteFactory.newNote(0, 3, 5, RelativePitch.A, 1, 1))
            .removeNote(NoteFactory.newNote(0, 2, 5, RelativePitch.B, 1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeInvalidNote() {
    MusicPieceFactory.newEmptyPiece(1000)
            .addNote(NoteFactory.newNote(0, 3, 5, RelativePitch.A, 1, 1))
            .changeNoteDuration(NoteFactory.newNote(0, 2, 5, RelativePitch.B, 1, 1), 3);
  }


}
