package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import cs3500.music.controller.BeatController;
import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.MusicPieceModel;
import cs3500.music.model.Note;
import cs3500.music.model.PlayMarker;

/**
 * Create a ViewModelImpl
 */
class ViewModelImpl implements MutableViewModel {
  /**
   * Fields
   */
  private MusicPieceModel pieceModel;
  private int viewTop, viewStart, currentBeat;
  private Point selectedPoint;
  private final List<Consumer<BeatController>> playUpdates;
  private final int NOTE_MOVEMENT_RESOLUTION = 32;
  private final List<Runnable> viewUpdates;
  private Note heldNote;

  /**
   * Constructor for ViewModelImpl
   *
   * @param pieceModel the given MusicPieceModel
   */
  ViewModelImpl(MusicPieceModel pieceModel) {
    this.pieceModel = Objects.requireNonNull(pieceModel);
    this.playUpdates = new ArrayList<>();
    this.viewUpdates = new ArrayList<>();
    this.heldNote = null;
  }


  @Override
  public void setCurrentBeat(int currentBeat) {
    this.currentBeat = currentBeat;
    if (this.currentBeat > this.getLength()) {
      this.currentBeat = this.getLength();
    } else if ((this.getCurrentBeat() % NOTE_MOVEMENT_RESOLUTION) == 0
              || this.getCurrentBeat() < this.getViewStart()) {
      this.setViewStart(currentBeat / NOTE_MOVEMENT_RESOLUTION * NOTE_MOVEMENT_RESOLUTION);
    }
  }

  @Override
  public void setViewStart(int viewStart) {
    this.viewStart = viewStart;
    this.updateView();
  }

  @Override
  public void setViewTop(int value) {
    this.viewTop = value;
    this.updateView();
  }

  /**
   * update the view
   */
  private void updateView() {
    for (Runnable u : viewUpdates) {
      u.run();
    }
  }

  @Override
  public void setSelectedPoint(Point p) {
    this.selectedPoint = p;
    this.updateView();
  }

  @Override
  public void removeNote(Note n) {
    pieceModel.removeNote(n);
    this.updateView();
  }

  @Override
  public void changeNoteDuration(Note n, int i) {
    this.pieceModel.changeNoteDuration(n, i);
    this.updateView();
  }

  @Override
  public void addNote(Note n) {
    this.pieceModel.addNote(n);
    this.updateView();
  }

  @Override
  public void setHeldNote(Note n) {
    this.heldNote = n;
  }

  @Override
  public void reset() {
    pieceModel.reset();
  }

  @Override
  public int getLength() {
    return this.pieceModel.getLength();
  }

  @Override
  public AbsolutePitch highestPitch() {
    return this.pieceModel.highestPitch();
  }

  @Override
  public AbsolutePitch lowestPitch() {
    return this.pieceModel.lowestPitch();
  }

  @Override
  public List<Note> getNotesAt(int i) {
    return this.pieceModel.getNotesAt(i);
  }

  @Override
  public int getViewStart() {
    return this.viewStart;
  }

  @Override
  public int getCurrentBeat() {
    return this.currentBeat;
  }

  @Override
  public void registerUpdates(Consumer<BeatController> playUpdate, Runnable viewUpdate) {
    this.playUpdates.add(playUpdate);
    this.viewUpdates.add(viewUpdate);
  }

  @Override
  public int getTempo() {
    return this.pieceModel.getTempo();
  }

  @Override
  public int getViewTop() {
    return this.viewTop;
  }

  @Override
  public Point getSelectedPoint() {
    return this.selectedPoint;
  }

  @Override
  public Note getHeldNote() {
    return this.heldNote;
  }

  @Override
  public Optional<PlayMarker> getPlayMarkerAt(int beat) {
    return pieceModel.getPlayMarkerAt(beat);
  }

  @Override
  public void run(BeatController timer, int beat) {
    this.currentBeat = beat;
    for (Consumer<BeatController> u : playUpdates) {
      u.accept(timer);
    }
  }

  @Override
  public void stop() {

  }

  @Override
  public int getNextRepeatLocation() {
    return this.pieceModel.getNextRepeatLocation(this.currentBeat);
  }

  @Override
  public void addRepeat(int x) {
    this.pieceModel.addRepeat(x);
    this.updateView();
  }

  @Override
  public void addSetPlayFrom(int x) {
    this.pieceModel.addSetPlayFrom(x);
    this.updateView();
  }


  @Override
  public void addSkipAfterOne(int x) {
    this.pieceModel.addSkipAfterOne(x);
    this.updateView();
  }

  @Override
  public void removeRepeat(int x) {
    this.pieceModel.removeRepeat(x);
    this.updateView();
  }

  @Override
  public void removeSkipAfterOne(int x) {
    this.pieceModel.removeSkipAfterOne(x);
    this.updateView();
  }

  @Override
  public void removeSetPlayFrom(int x) {
    this.pieceModel.removeSetPlayFrom(x);
    this.updateView();
  }

}
