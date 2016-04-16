package cs3500.music.model;

import java.util.Objects;

/**
 * An implementation of the Note interface
 */
class NoteImpl implements Note {

  private final int startTime, duration;
  private final AbsolutePitch pitch;
  private final int instrument, volume;


  /**
   * Constructor for a {@link Note}
   *
   * @param startTime  The starttime location of where this note start
   * @param duration   duration measure the duration of a note
   * @param relPitch   the pitch of the node
   * @param instrument the instrument of the note
   * @param volume     the volume of the note
   */
  NoteImpl(int startTime, int duration, int octave, RelativePitch relPitch,
           int instrument, int volume) {
    this(startTime, duration, new AbsolutePitchImpl(relPitch, octave), instrument, volume);
  }

  /**
   * Constructor for a {@link Note}
   *
   * @param startTime  The starttime location of where this note start
   * @param duration   duration measure the duration of a note
   * @param pitch      the pitch of the node
   * @param instrument the instrument of the note
   * @param volume     the volume of the note
   */
  NoteImpl(int startTime, int duration, AbsolutePitch pitch, int instrument, int volume) {
    if (startTime < 0 || duration < 1) {
      throw new IllegalArgumentException("Not a valid Note");
    }
    this.startTime = startTime;
    this.duration = duration;
    this.pitch = Objects.requireNonNull(pitch);
    this.instrument = instrument;
    this.volume = volume;
  }

  /**
   * @return the value of the pitch at certain octave
   */
  @Override
  public AbsolutePitch getPitch() {
    return this.pitch;
  }

  /**
   * change the start time of the note throw illegal argument if the fixed start time is less than
   * 0
   *
   * @param i given the number of change
   */
  @Override
  public Note addStartTime(int i) {
    return new NoteImpl(startTime + i, duration, pitch, instrument, volume);
  }

  /**
   * change the duration of a note throw illegal argument if the fixed duration is less than 1
   *
   * @param i given the number of change
   */
  @Override
  public Note addNoteDuration(int i) {
    if ((this.duration + i) < 1) {
      throw new IllegalArgumentException("Duration is less than 1!");
    } else {
      return new NoteImpl(startTime, duration + i, pitch, instrument, volume);
    }
  }

  /**
   * change the pitch location of a note throw illegal argument if the fixed pitchlocation is not
   * valid moves note up or down
   *
   * @param halfsteps given the number of change
   */
  @Override
  public Note changePitch(int halfsteps) {
    return new NoteImpl(startTime, duration, pitch.changePitch(halfsteps), instrument, volume);
  }

  /**
   * @return the startTime of the note
   */
  @Override
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * @return the beats of the note
   */
  @Override
  public int getDuration() {
    return this.duration;
  }

  /**
   * @return the pitch location
   */
  @Override
  public int getPitchLocation() {
    return this.pitch.getIntValue();
  }

  /**
   * @return the instrument of the note
   */
  @Override
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * @return the Volume of the note
   */
  @Override
  public int getVolume() {
    return this.volume;
  }


  /**
   * Determines whether a given object is the same as this
   *
   * @param o the object to be compared to
   * @return whether the given object is the same as this
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Note)) {
      return false;
    } else {
      return ((Note) o).getStartTime() == this.startTime &&
              ((Note) o).getDuration() == this.duration &&
              ((Note) o).getPitch().equals(pitch) &&
              ((Note) o).getInstrument() == (instrument) &&
              ((Note) o).getVolume() == (volume);
    }
  }

  /**
   * Generates a hash code for this {@link Note}
   *
   * @return the hash code for this {@link Note}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.startTime, this.duration, this.pitch, this.instrument, this.volume);
  }


}
