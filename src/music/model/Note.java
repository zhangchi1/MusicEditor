package cs3500.music.model;

/**
 * Represents an music note
 */
public interface Note {


  /**
   * @return the value of the pitch at certain octave
   */
  AbsolutePitch getPitch();

  /**
   * change the start time of the note throw illegal argument if the fixed start time is less than
   * 0
   *
   * @param i given the number of change
   */
  Note addStartTime(int i);

  /**
   * change the duration of a note throw illegal argument if the fixed duration is less than 1
   *
   * @param i given the number of change
   */
  Note addNoteDuration(int i);

  /**
   * change the pitch location of a note throw illegal argument if the fixed pitchlocation is not
   * valid moves note up or down
   *
   * @param halfsteps given the number of change
   */
  Note changePitch(int halfsteps);


  /**
   * @return the startTime of the note
   */
  int getStartTime();

  /**
   * @return the beats of the note
   */
  int getDuration();

  /**
   * @return the pitch location
   */
  int getPitchLocation();

  /**
   * @return the instrument of the note
   */
  int getInstrument();

  /**
   * @return the Volume of the note
   */
  int getVolume();

}
