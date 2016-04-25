package cs3500.music.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import cs3500.music.controller.BeatController;
import cs3500.music.model.AbsolutePitch;
import cs3500.music.model.AbsolutePitchRange;
import cs3500.music.model.Note;

/**
 * An output device that prints a text representation of music
 */
class MusicPrinter implements MusicOutputDevice {

  private Appendable output;
  private List<Note> toPlay;
  private AbsolutePitchRange pitchRange;
  private ViewModel viewModel;
  private int beat;
  private int maxBeatCountLen;

  /**
   * Creates a new music printer with the specified range
   *
   * @param output the place to print the text
   */
  public MusicPrinter(Appendable output, ViewModel viewModel) {
    this.output = output;
    this.pitchRange = null;
    toPlay = new LinkedList<>();
    this.viewModel = Objects.requireNonNull(viewModel);
    beat = 0;
    maxBeatCountLen = 0;
  }

  @Override
  public void renderModel(BeatController timer) throws IOException {
    this.pitchRange = new AbsolutePitchRange(viewModel.lowestPitch(),
            viewModel.highestPitch());
    this.beat = 0;
    this.maxBeatCountLen = Integer.toString(viewModel.getLength()).length();
    for (int i = 0; i < maxBeatCountLen; i++) {
      output.append(' ');
    }
    for (AbsolutePitch pitch : pitchRange) {
      String pitchName = pitch.getName();
      switch (pitchName.length()) {
        case 4:
          output.append(' ')
                  .append(pitchName);
          break;
        case 3:
          output.append(' ')
                  .append(pitchName)
                  .append(' ');
          break;
        default:
          output.append("  ")
                  .append(pitchName)
                  .append(' ');
      }
    }
    output.append('\n');

    for (int i = 0; i < viewModel.getLength(); i++) {
      toPlay = new ArrayList<>(viewModel.getNotesAt(i));
      tick();
    }
  }

  @Override
  public void initialize() {

  }

  /**
   * Prints "X", "|" or " " for each pitch at the current beat based on whether there is a new note,
   * suspended note, or no note at that beat.
   */
  private void tick() {
    try {
      output.append(paddedInt(beat));
      for (AbsolutePitch pitch : pitchRange) {
        if (!toPlay.isEmpty()
                && toPlay.get(0).getPitch().equals(pitch)) {
          if (beat == toPlay.get(0).getStartTime()) {
            output.append("  X  ");
          } else {
            output.append("  |  ");
          }
          //remove all notes at this pitch
          while (!toPlay.isEmpty() && toPlay.get(0).getPitch().equals(pitch)) {
            toPlay.remove(0);
          }
        } else {
          output.append("     ");
        }
      }
      toPlay.clear();
      output.append('\n');
      beat++;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Pads the string representation of the beat to the appropriate length
   * @param beat the int to convert and pad
   * @return the padded string representing the beat
   */
  private String paddedInt(int beat) {
    String beatStr = Integer.toString(beat);
    StringBuilder beatBuilder = new StringBuilder();
    for (int i = beatStr.length(); i < maxBeatCountLen; i++) {
      beatBuilder.append(' ');
    }
    beatBuilder.append(beatStr);
    return beatBuilder.toString();
  }
}
