package cs3500.music.view;

import java.util.Objects;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.controller.BeatController;
import cs3500.music.model.Note;

/**
 * A skeleton for MIDI playback. Plays back pieces over MIDI. Rounds tempo  down to the milisecond.
 */
class MidiViewImpl implements MusicOutputDevice {


  private final Synthesizer synth;
  private final int channelCount;
  private final Receiver receiver;

  private ViewModel viewModel;

  /**
   * Constructor for MidiViewImpl
   */
  MidiViewImpl(Synthesizer synth, ViewModel viewModel) {
    this.viewModel = Objects.requireNonNull(viewModel);
    viewModel.registerUpdates(this::run, ()-> {});
    Receiver newRec = null;
    try {
      newRec = synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = synth;
    this.channelCount = this.synth.getChannels().length;
    this.receiver = newRec;

  }

  /**
   *
   * play A Note in the MIDI View
   * @param note the note to play
   */
  private void playNote(Note note, BeatController timer) {
    try {
      receiver.send(new ShortMessage(ShortMessage.PROGRAM_CHANGE,
              note.getInstrument()%channelCount, note.getInstrument(), 0), -1);
      receiver.send(new ShortMessage(ShortMessage.NOTE_ON,
              note.getInstrument()%channelCount, note.getPitch().getIntValue(),
              note.getVolume()), -1/*timestamp*/);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    //adds a callback that stops the note
    timer.addBeatListener(new NoteStopper(note), BeatController.Priority.FIRST);
  }

  /**
   * Stop at a given Note in the MIDI View
   *
   * @param note stop play at the given note
   */
  private void stopNote(Note note) {
    try {
      receiver.send(new ShortMessage(ShortMessage.NOTE_OFF,
              note.getInstrument()%channelCount, note.getPitch().getIntValue(),
              note.getVolume()), -1/*timestamp*/);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void renderModel(BeatController timer) {
    timer.addBeatListener(this.viewModel, BeatController.Priority.LAST);
  }

  @Override
  public void initialize() {
    try {
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * run the Midi view based on the timer
   * @param timer the given timer
   */
  private void run(BeatController timer) {
    if (viewModel.getCurrentBeat() < viewModel.getLength()) {
      for (Note note : viewModel.getNotesAt(viewModel.getCurrentBeat())) {
        if (note.getStartTime() == viewModel.getCurrentBeat()) {
          playNote(note, timer);
        }
      }
    }
  }

  /**
   * Each NoteStopper represents a callback that stops the note when necessary
   */
  private class NoteStopper implements BeatListener {

    private Note note;
    private final int stopTime;

    /**
     * Creates a new callback that stops the note at the appropriate time
     *
     * @param note the note to stop playing
     */
    private NoteStopper(Note note) {
      this.note = note;
      stopTime = note.getStartTime() + note.getDuration();
    }

    @Override
    public void run(BeatController timer, int currentBeat) {
      if (currentBeat >= stopTime) {
        stopNote(note);
        timer.removeBeatListener(this, BeatController.Priority.FIRST);
      }
    }

    @Override
    public void stop() {
      stopNote(note);
    }
  }


}