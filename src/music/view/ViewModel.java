package cs3500.music.view;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import cs3500.music.controller.BeatController;
import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.Note;
import cs3500.music.model.PlayMarker;

/**
 * A ViewModel holds all state that a view might request. This includes the piece
 * that the view is supposed to render. This indirection both serves to isolate
 * the view from any program state and prevents it from modifying the model.
 */
public interface ViewModel extends BeatListener {

  /**
   * Should delegate to an internal {@link cs3500.music.model.MusicPieceModel}
   * @return the length of the viewModel
   */
  int getLength();

  /**
   * Should delegate to an internal {@link cs3500.music.model.MusicPieceModel}
   * @return the highestPitch of the viewModel
   */
  AbsolutePitch highestPitch();

  /**
   * Should delegate to an internal {@link cs3500.music.model.MusicPieceModel}
   * @return the lowestPitch of the viewModel
   */
  AbsolutePitch lowestPitch();

  /**
   * Should delegate to an internal {@link cs3500.music.model.MusicPieceModel}
   * @return a list of all notes at the given beat, ordered by pitch and start time.
   */
  List<Note> getNotesAt(int i);

  /**
   * @return the first beat that the view should render
   */
  int getViewStart();

  /**
   * Should delegate to an internal {@link cs3500.music.model.MusicPieceModel}
   * @return the currentBeat of the inner model
   */
  int getCurrentBeat();

  /**
   * Adds update callbacks to the viewmodel so that it can request that
   * the view updates when data changes.
   * @param playUpdate a method to call when the current note changes
   * @param viewUpdate a method to call when a view property changes
   */
  void registerUpdates(Consumer<BeatController> playUpdate, Runnable viewUpdate);

  /**
   * Should delegate to an internal {@link cs3500.music.model.MusicPieceModel}
   * @return the tempo of the model
   */
  int getTempo();

  /**
   * get the top of the view, the highest pitch that should be rendered
   * @return the top of the view
   */
  int getViewTop();

  /**
   * Gets the last point in the piece selected by the user.
   * The X coordinate is the beat and
   * the Y coordinate is the pitch
   * @return the selected point
   */
  Point getSelectedPoint();

  /**
   * Gets the note currently held by the controller for manipulation.
   * Used for dragging notes.
   * @return the note currently held by the controller
   */
  Note getHeldNote();


  /**
   * Gets the play marker at the given beat, if it exists
   * @param beat
   * @return an list that contains the play marker, if any
   */
  Optional<PlayMarker> getPlayMarkerAt(int beat);
}
