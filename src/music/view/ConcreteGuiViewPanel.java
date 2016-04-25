package cs3500.music.view;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

import javax.swing.*;

import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.AbsolutePitchRange;
import cs3500.music.model.Note;
import cs3500.music.model.PlayMarker;

/**
 * A JPanel that draws a viewModel on the screen
 */
class ConcreteGuiViewPanel extends JPanel {

  private final ViewModel viewModel;
  protected final static int GRIDSIZE = 18;
  protected final static int XOFFSET = 30;
  protected final static int YOFFSET = 15;
  protected final static int MARKER_LINE_WIDTH = 8;


  /**
   * Construct a ConcreteGuiViewPanel
   *
   * @param viewModel the given viewModel
   */
  ConcreteGuiViewPanel(ViewModel viewModel) {
    this.viewModel = Objects.requireNonNull(viewModel);
    this.viewModel.registerUpdates(t -> this.repaint(), this::repaint);

  }


  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    if (viewModel != null) {
      this.printAllNotes(g);
      this.printGrid(g);
      this.printBeatTimeLine(g);
      this.printPitchesLine(g);
      this.printCurrentBeatRedLine(g);
      this.printSelectionBox(g);
      this.printAllPlayMarkers(g);
    }
  }


  @Override
  public Dimension getPreferredSize() {
    if (viewModel != null)
      return new Dimension(viewModel.getLength() * GRIDSIZE + XOFFSET,
              (viewModel.highestPitch().getIntValue()
                      - viewModel.lowestPitch().getIntValue()) * GRIDSIZE + YOFFSET);
    else return super.getPreferredSize();
  }


  /**
   * print the grid of the Gui View where the x coordinate is the beat and the y coordinate is the
   * pitch. columns are drawn every 4 beats.
   *
   * @param g the given graphics
   */
  private void printGrid(Graphics g) {
    int PitchRange = viewModel.highestPitch().getIntValue() -
            viewModel.lowestPitch().getIntValue() + 1;
    g.setColor(Color.black);
    //draw vertical lines
    for (int i = 0; i <= viewModel.getLength(); i++) {
      ((Graphics2D) g).setStroke(new BasicStroke(2.5f));
      if (i == viewModel.getLength() || i % 4 == 0) {
        //(int x1, int y1, int x2, int y2)
        int xPos = Math.max((i - (this.viewModel.getViewStart() % 4)) * GRIDSIZE, 0) + XOFFSET;
        g.drawLine(xPos, 0 + YOFFSET, xPos,
                PitchRange * GRIDSIZE + YOFFSET);
      }
    }
    //draw horizontal lines
    for (int i = 0; i <= PitchRange; i++) {
      ((Graphics2D) g).setStroke(new BasicStroke(2.5f));

      // (int x1, int y1, int x2, int y2)
      g.drawLine(XOFFSET, GRIDSIZE * i + YOFFSET, viewModel.getLength() * GRIDSIZE + XOFFSET,
              YOFFSET + i * GRIDSIZE);
    }
  }

  /**
   * print all visible notes in the viewModel to the screen
   *
   * @param g the given graphics
   */
  private void printAllNotes(Graphics g) {
    for (int i = this.viewModel.getViewStart(); i < this.viewModel.getLength(); i++) {
      for (Note n : viewModel.getNotesAt(i)) {
        if (n.getPitch().changePitch(this.viewModel.getViewTop())
                .compareTo(viewModel.highestPitch()) <= 0
                && (n.getPitch().getIntValue() + this.viewModel.getViewTop()
                >= this.viewModel.lowestPitch().getIntValue())) {
          if (n.getStartTime() == i) {
            this.printBlackNote(n, g);
          } else {
            this.printGreenNote(n, i, g);
          }
        }
      }
    }

  }

  /**
   * Draws a note head in the appropriate grid space.
   *
   * @param note the note to draw
   * @param g    the graphics context of the screen
   */
  private void printBlackNote(Note note, Graphics g) {

    //print note starting point
    g.setColor(Color.black.darker());
    //(int x, int y, int width, int height)
    g.fillRect((note.getStartTime() - this.getNoteOffset()) * GRIDSIZE + XOFFSET,
            (viewModel.highestPitch().getIntValue()
                    - note.getPitch().getIntValue()
                    - viewModel.getViewTop()
            ) * GRIDSIZE + YOFFSET,
            GRIDSIZE, GRIDSIZE);
  }


  /**
   * Draws a suspended note in the appropriate grid space on a specific beat.
   *
   * @param note the note to draw
   * @param beat the beat to draw the note at
   * @param g    the graphics context of the screen
   */
  private void printGreenNote(Note note, int beat, Graphics g) {

    g.setColor(Color.green.darker());
    g.fillRect((beat - this.getNoteOffset()) * GRIDSIZE + XOFFSET,
            (viewModel.highestPitch().getIntValue()
                    - note.getPitch().getIntValue()
                    - viewModel.getViewTop()
            ) * GRIDSIZE + YOFFSET,
            GRIDSIZE, GRIDSIZE);

  }


  /**
   * print the pitches symbols in Gui View
   *
   * @param g the given graphics the pitches symbols vertically
   */
  private void printPitchesLine(Graphics g) {
    g.setColor(Color.black.darker());
    for (AbsolutePitch pitch : new AbsolutePitchRange(viewModel.lowestPitch(),
            viewModel.highestPitch())) {
      try {
        String pitchname = pitch.changePitch(-this.viewModel.getViewTop()).getName();
        g.drawString(pitchname, 0,
                (viewModel.highestPitch().getIntValue() - pitch.getIntValue())
                        * GRIDSIZE + YOFFSET + 14);
      } catch (IllegalArgumentException e) {
        //do nothing, do not draw the note
      }

    }
  }

  /**
   * prints the beat timeline horizontally
   *
   * @param g the given graphics
   */
  private void printBeatTimeLine(Graphics g) {
    g.setColor(Color.black.darker());
    for (int i = this.getNoteOffset(); i <= viewModel.getLength(); i++) {
      if (i % 16 == 0) {
        //(String str, int x, int y)
        g.drawString(Integer.toString(i), (i - this.getNoteOffset()) * GRIDSIZE + XOFFSET, 10);
      }
    }
  }

  /**
   * prints the current red line based on current beat
   *
   * @param g the given graphics
   */
  private void printCurrentBeatRedLine(Graphics g) {
    int PitchRange = viewModel.highestPitch().getIntValue() -
            viewModel.lowestPitch().getIntValue() + 1;
    g.setColor(Color.red.darker());
    //(int x1, int y1, int x2, int y2)
    g.drawLine(this.getRedLineOffset() * GRIDSIZE + XOFFSET,
            YOFFSET,
            this.getRedLineOffset() * GRIDSIZE + XOFFSET,
            PitchRange * GRIDSIZE + YOFFSET);

  }

  /**
   * find the red line position
   *
   * @return the integer of red line offset
   */
  private int getRedLineOffset() {
    return this.viewModel.getCurrentBeat() - this.viewModel.getViewStart();
  }

  /**
   * Draws the selection box on the screen if a visible note is selected.
   *
   * @param g the graphics object for the screen
   */
  private void printSelectionBox(Graphics g) {
    g.setColor(Color.magenta);
    if (viewModel.getSelectedPoint() != null) {
      g.drawRect((viewModel.getSelectedPoint().x - viewModel.getViewStart()) * GRIDSIZE + XOFFSET,
              (viewModel.highestPitch().getIntValue()
                      - viewModel.getSelectedPoint().y
                      - viewModel.getViewTop()) * GRIDSIZE + YOFFSET,
              GRIDSIZE, GRIDSIZE);
    }
  }


  /**
   * find the  position of the view start
   *
   * @return the integer of note offset
   */
  private int getNoteOffset() {
    return this.viewModel.getViewStart();
  }

  /**
   * Print all the visible PlayMarkers
   *
   * @param g the graphics object for the screen
   */
  private void printAllPlayMarkers(Graphics g) {
    for (int i = this.viewModel.getViewStart(); i < this.viewModel.getLength(); i++) {
      Optional<PlayMarker> markerOpt = viewModel.getPlayMarkerAt(i);
      //if there is a marker
      if (markerOpt.isPresent()) {
        //get it and see what kind it is
        PlayMarker marker = markerOpt.get();
        if (marker.isRepeatMarker()) {
          this.printRepeatMarker(i, g);
        }
        if (marker.isSetPlayFromMarker()) {
          this.printPlayFromMarker(i, g);
        }
        if (marker.isSkipAfterOneMarker()) {
          this.printSkipAfterOneMarker(i, g);
        }
      }
    }
  }

  /**
   * Draw the playFromMarker on the screen
   *
   * @param g the graphics object for the screen
   */
  private void printPlayFromMarker(int beat, Graphics g) {
    int PitchRange = viewModel.highestPitch().getIntValue() -
            viewModel.lowestPitch().getIntValue() + 1;
    //draw thin line
    g.setColor(Color.magenta.darker());
    g.fillRect((beat - this.viewModel.getViewStart()) * GRIDSIZE + XOFFSET,
            YOFFSET,
            MARKER_LINE_WIDTH,
            PitchRange * GRIDSIZE);
    //draw thicker
    g.setColor(Color.cyan.darker());
    g.fillRect((beat - this.viewModel.getViewStart()) * GRIDSIZE + XOFFSET - MARKER_LINE_WIDTH/2,
            YOFFSET,
            MARKER_LINE_WIDTH,
            PitchRange * GRIDSIZE);

  }

  /**
   * Draw the SkipAfterOneMarker on the screen
   *
   * @param g the graphics object for the screen
   */
  private void printSkipAfterOneMarker(int beat, Graphics g) {
    int PitchRange = viewModel.highestPitch().getIntValue() -
            viewModel.lowestPitch().getIntValue() + 1;
    //print horizontal lines
    for (int i = YOFFSET; i < PitchRange * GRIDSIZE + YOFFSET; i = i + 10) {
      g.setColor(Color.blue);
      g.drawLine((beat - this.viewModel.getViewStart()) * GRIDSIZE + XOFFSET - MARKER_LINE_WIDTH,
              i,
              (beat - this.viewModel.getViewStart()) * GRIDSIZE + XOFFSET + MARKER_LINE_WIDTH,
              i);
    }
  }

  /**
   * Draw the SkipAfterOneMarker on the screen
   *
   * @param g the graphics object for the screen
   */
  private void printRepeatMarker(int beat, Graphics g) {
    int PitchRange = viewModel.highestPitch().getIntValue() -
            viewModel.lowestPitch().getIntValue() + 1;
    //draw thin line
    g.setColor(Color.magenta.darker());
    g.fillRect((beat - this.viewModel.getViewStart()) * GRIDSIZE + XOFFSET - MARKER_LINE_WIDTH,
            YOFFSET,
            MARKER_LINE_WIDTH,
            PitchRange * GRIDSIZE);
    //draw thicker
    g.setColor(Color.cyan.darker());
    g.fillRect((beat - this.viewModel.getViewStart()) * GRIDSIZE + XOFFSET - MARKER_LINE_WIDTH/2,
            YOFFSET,
            MARKER_LINE_WIDTH,
            PitchRange * GRIDSIZE);
  }

}
