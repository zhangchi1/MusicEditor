package cs3500.music.model;

/**
 * A factory for making note instances
 */
public class NoteFactory {
  
  /**
   * create a new note
   *
   * @return a new Note
   */
  public static Note newNote(int startTime,
                             int duration,
                             AbsolutePitch pitch,
                             int instrument,
                             int volume) {
    return new NoteImpl(startTime, duration, pitch, instrument, volume);
  }

  /**
   * create a new note
   *
   * @return a new Note
   */
  public static Note newNote(int startTime,
                             int duration,
                             int octave,
                             RelativePitch relPitch,
                             int instrument,
                             int volume) {
    return new NoteImpl(startTime, duration, octave, relPitch, instrument, volume);
  }
}
