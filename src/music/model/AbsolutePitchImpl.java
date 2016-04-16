package cs3500.music.model;

import java.util.Objects;

/**
 * Provides an implementation of the AbsolutePitch interface
 */
class AbsolutePitchImpl implements AbsolutePitch {


  private final RelativePitch relativePitch;
  private final int octave;

  /**
   * Constructs a new absolute pitch with the given relative pitch and octave
   *
   * @param relativePitch the relative pitch, e.g. C or DSharp
   * @param octave        the octave, an int in the range [0, MAX_OCTAVE]
   * @throws IllegalArgumentException if the octave is out of bounds
   * @throws NullPointerException     if the relative pitch is null
   */
  AbsolutePitchImpl(RelativePitch relativePitch, int octave) {
    if (octave < AbsolutePitchFactory.MIN_OCTAVE || octave > AbsolutePitchFactory.MAX_OCTAVE) {
      throw new IllegalArgumentException("Octave out of accepted bounds: "
              + Integer.toString(octave));
    }
    this.relativePitch = Objects.requireNonNull(relativePitch);
    this.octave = octave;
  }

  /**
   * Creates a new AbsolutePitch calculated from the given pitch integer
   *
   * @param pitch an integer that represents an octave and {@link RelativePitch}
   */
  AbsolutePitchImpl(int pitch) {
    this(RelativePitch.values()[pitch % 12], (pitch / 12 - 1));
  }

  /**
   * Transposes the given pitch up or down by the given number of half steps
   *
   * @param halfSteps the number of halfsteps to move the pitch by. A positive number raises the
   *                  pitch while a negative number lowers it
   * @return the new pitch
   */
  public AbsolutePitch changePitch(int halfSteps) {
    int stepSum = halfSteps + this.relativePitch.ordinal();
    if (stepSum >= 0) {
      return new AbsolutePitchImpl(this.relativePitch.changePitch(halfSteps),
              this.octave + (stepSum / RelativePitch.PITCH_COUNT));
    } else {
      return new AbsolutePitchImpl(this.relativePitch.changePitch(halfSteps),
              this.octave + ((stepSum - RelativePitch.PITCH_COUNT + 1)
                      / RelativePitch.PITCH_COUNT));
    }
  }

  /**
   * @return the printable name of this pitch
   */
  public String getName() {
    return relativePitch.getName() + Integer.toString(octave);
  }


  /**
   * @return an integer representation of this pitch
   */
  public int getIntValue() {
    return this.relativePitch.ordinal() + (this.octave + 1) * 12;
  }

  @Override
  public int getOctave() {
    return this.octave;
  }

  @Override
  public RelativePitch getRelativePitch() {
    return this.relativePitch;
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AbsolutePitch)) {
      return false;
    }
    return ((AbsolutePitch) o).getOctave() == this.octave
            && ((AbsolutePitch) o).getRelativePitch().equals(this.relativePitch);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relativePitch, octave);
  }


  @Override
  public int compareTo(AbsolutePitch o) {
    if (this.octave > o.getOctave()) {
      return 1;
    } else if (this.octave < o.getOctave()) {
      return -1;
    } else {
      return this.relativePitch.compareTo(o.getRelativePitch());
    }
  }
}
