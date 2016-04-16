package cs3500.music.model;

import java.util.Collections;
import java.util.List;

/**
 * A factory for creating new MusicPieceModel objects
 */
public class MusicPieceFactory {

  // users cannot construct an instance of this class
  // since its only contents are static methods
  private MusicPieceFactory() {

  }

  /**
   * Creates a new empty viewModel
   *
   * @return the new viewModel
   */
  public static MusicPieceModel newEmptyPiece(int tempo) {
    return new MusicPieceModelImpl(Collections.emptyList(), tempo);
  }

  /**
   * Creates a new music viewModel with a list of notes
   *
   * @return the new viewModel
   */
  public static MusicPieceModel newPieceFromList(List<Note> notes, int tempo) {
    return new MusicPieceModelImpl(notes, tempo);
  }

  /**
   * Creates a new metronome that repeats a given note a certain number of times
   *
   * @param pitch       the pitch of the tick
   * @param duration    the duration of the tick
   * @param repeatCount the number of times to repeat the note
   * @return the new viewModel
   */
  public static MusicPieceModel newMetronome(AbsolutePitch pitch, int duration, int repeatCount,
                                             int tempo) {
    MusicPieceModel piece = new MusicPieceModelImpl(Collections.emptyList(), tempo);
    for (int i = 0; i < repeatCount; i++) {
      piece.addNote(NoteFactory.newNote(i * duration, duration, pitch, 0, 100));
    }
    return piece;
  }

}
