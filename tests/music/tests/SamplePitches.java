package cs3500.music.tests;

import cs3500.music.model.*;

/**
 * Holds sample notes for use in testing
 */
public class SamplePitches {


  public static final AbsolutePitch C0 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 0);
  public static final AbsolutePitch A4 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.A, 4);
  public static final AbsolutePitch C4 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.C, 4);
  public static final AbsolutePitch FSharp4 =
          AbsolutePitchFactory.newAbsolutePitch(RelativePitch.FSharp, 4);
  public static final AbsolutePitch G4 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 4);
  public static final AbsolutePitch G3 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.G, 3);
  public static final AbsolutePitch E4 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.E, 4);
  public static final AbsolutePitch D4 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 4);
  public static final AbsolutePitch D3 = AbsolutePitchFactory.newAbsolutePitch(RelativePitch.D, 3);
  public static final AbsolutePitch FSharp10 =
          AbsolutePitchFactory.newAbsolutePitch(RelativePitch.FSharp, 10);
  public static final AbsolutePitch FSharp9 =
          AbsolutePitchFactory.newAbsolutePitch(RelativePitch.FSharp, 9);
  public static final AbsolutePitch GSharp4 =
          AbsolutePitchFactory.newAbsolutePitch(RelativePitch.GSharp, 4);

  public static MusicPieceModel LightlyRow4Measures() {
    return MusicPieceFactory
            .newEmptyPiece(400000)
            .addNote(NoteFactory.newNote(0, 1, SamplePitches.A4, 0, 0))
            .addNote(NoteFactory.newNote(1, 1, SamplePitches.FSharp4, 0, 0))
            .addNote(NoteFactory.newNote(2, 2, SamplePitches.FSharp4, 0, 0))
            .addNote(NoteFactory.newNote(4, 1, SamplePitches.G4, 0, 0))
            .addNote(NoteFactory.newNote(5, 1, SamplePitches.E4, 0, 0))
            .addNote(NoteFactory.newNote(6, 2, SamplePitches.E4, 0, 0))
            .addNote(NoteFactory.newNote(8, 1, SamplePitches.D4, 0, 0))
            .addNote(NoteFactory.newNote(9, 1, SamplePitches.E4, 0, 0))
            .addNote(NoteFactory.newNote(10, 1, SamplePitches.FSharp4, 0, 0))
            .addNote(NoteFactory.newNote(11, 1, SamplePitches.G4, 0, 0))
            .addNote(NoteFactory.newNote(12, 1, SamplePitches.A4, 0, 0))
            .addNote(NoteFactory.newNote(13, 1, SamplePitches.A4, 0, 0))
            .addNote(NoteFactory.newNote(14, 2, SamplePitches.A4, 0, 0));
  }

}
