package cs3500.music.model;

/**
 * Used to create AbsolutePitch instances
 */
public class AbsolutePitchFactory {
  /**
   * The highest valid octave
   */
  public static final int MAX_OCTAVE = 12;
  /**
   * The highest possible pitch
   */
  public static final AbsolutePitch MAX_PITCH = newAbsolutePitch(RelativePitch.B,
          MAX_OCTAVE);
  public static final int MIN_OCTAVE = -1;
  public static final AbsolutePitch MIN_PITCH = newAbsolutePitch(RelativePitch.C, MIN_OCTAVE);

  /**
   * Creates a new AbsolutePitch with the given relativePitch and octave
   *
   * @param relativePitch the relativePitch of the new object
   * @param octave        the octave of the new object
   * @return the new AbsolutePitch
   */
  public static AbsolutePitch newAbsolutePitch(RelativePitch relativePitch, int octave) {
    return new AbsolutePitchImpl(relativePitch, octave);
  }

  /**
   * Creates a new AbsolutePitch calculated from the given pitch integer
   *
   * @param pitch an integer that represents an octave and {@link RelativePitch}
   * @return the new AbsolutePitch
   */
  public static AbsolutePitch newAbsolutePitch(int pitch) {
    return new AbsolutePitchImpl(pitch);
  }
}
