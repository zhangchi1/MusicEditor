package cs3500.music.model;

/**
 * A class representing the set of pitches understood by the program
 */
public interface AbsolutePitch extends Comparable<AbsolutePitch> {

  /**
   * Transposes the given pitch up or down by the given number of half steps
   *
   * @param halfSteps the number of halfsteps to move the pitch by. A positive number raises the
   *                  pitch while a negative number lowers it
   * @return the new pitch
   */
  AbsolutePitch changePitch(int halfSteps);


  /**
   * @return the printable name of this pitch
   */
  String getName();

  /**
   * @return an integer representation of this pitch
   */
  int getIntValue();

  /**
   * @return an integer of the pitch
   */
  int getOctave();

  /**
   * @return an enum of the relativepitch
   */
  RelativePitch getRelativePitch();

}
