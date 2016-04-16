package cs3500.music.model;

import java.util.List;

import cs3500.music.util.CompositionBuilder;

/**
 * An interface representing a viewModel of music. INVARIANT: there exists at most 1 note starting
 * any given pitch and beat INVARIANT: all durations are non-null and >= 1 This interface follows
 * the 'fluent interface' pattern.
 */
public interface MusicPieceModel extends CompositionBuilder<MusicPieceModel> {

  /**
   * Copies this viewModel
   *
   * @return an identical copy of this viewModel
   */
  MusicPieceModel copy();

  /**
   * Overlays the input over this viewModel and returns this The result is a composite viewModel
   * that will be equivalent to both played simultaneously. This is equivalent to insert(viewModel,
   * 0)
   *
   * @return this
   */
  MusicPieceModel playSimultaneously(MusicPieceModel piece);

  /**
   * Appends the input to the end of this viewModel and returns this. This is equivalent to
   * insert(viewModel, this.getLength())
   *
   * @return this
   */
  MusicPieceModel playConsecutively(MusicPieceModel piece);

  /**
   * Inserts the new viewModel at the given point. Conflicting notes are resolved in favor of the
   * longest note.
   *
   * @return this
   * @throws IllegalArgumentException if beat < 0
   */
  MusicPieceModel insert(MusicPieceModel piece, int beat);

  /**
   * Adds a new note to this viewModel
   *
   * @param note the note to add
   * @return this
   */
  MusicPieceModel addNote(Note note);


  /**
   * Removes the given note from this viewModel
   *
   * @param note the note to remove
   * @return this
   * @throws IllegalArgumentException if the note is not in the viewModel
   */
  MusicPieceModel removeNote(Note note);

  /**
   * change the start time of a note
   *
   * @param note the given note
   * @param i    number of changing start time
   */
  MusicPieceModel changeNoteStartTime(Note note, int i);


  /**
   * change the note's location
   *
   * @param note      the given note
   * @param halfsteps number of changing the Note pitch location
   */
  MusicPieceModel changePitch(Note note, int halfsteps);

  /**
   * Transposes this viewModel by a given number of halfsteps
   *
   * @param halfsteps the number of half steps to changePitch the viewModel by
   * @return this
   */
  MusicPieceModel changePitch(int halfsteps);


  /**
   * change the duration of a note
   *
   * @param note the given note
   * @param i    number of changing the duration
   */
  MusicPieceModel changeNoteDuration(Note note, int i);

  /**
   * @return the number of beats until the last note ends
   */
  int getLength();

  /**
   * @return the lowest pitch in the viewModel
   */
  AbsolutePitch lowestPitch();

  /**
   * @return the highest pitch in the viewModel
   */
  AbsolutePitch highestPitch();


  /**
   * Gets the notes at a given beat
   *
   * @return a mapping from pitches to the duration to play them
   */
  List<Note> getNotesAt(int beat);

  /**
   * Gets a list containing all of the notes in this piece. Note: to get the notes in a particular
   * range of beats, {@link MusicPieceModel getNotesAt} should be used for greater efficiency.
   *
   * @return a list containing all of the notes in this piece
   */
  List<Note> getNotes();


  /**
   * @return the number of microseconds per beat in this viewModel
   */
  int getTempo();

  /**
   * @param tempo The speed, in microseconds per beat
   * @return this
   */
  @Override
  MusicPieceModel setTempo(int tempo);

}
