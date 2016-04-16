package cs3500.music.model;

/**
 * Represents a relative pitch
 */
public enum RelativePitch {
  C("C"), CSharp("C#"), D("D"), DSharp("D#"), E("E"), F("F"), FSharp("F#"),
  G("G"), GSharp("G#"), A("A"), ASharp("A#"), B("B");

  //maintain the values internally to avoid duplication
  private static final RelativePitch[] pitches = RelativePitch.values();
  public static final int PITCH_COUNT = pitches.length;

  private final String name;

  /**
   * Constructs a new relative pitch with the given name
   *
   * @param name the printable name of the pitch
   */
  RelativePitch(String name) {
    this.name = name;
  }

  /**
   * @return the printable name of this pitch
   */
  public String getName() {
    return name;
  }

  /**
   * Returns this pitch transposed by the given number of half steps
   */
  public RelativePitch changePitch(int halfSteps) {
    return pitches[Math.floorMod(this.ordinal() + halfSteps, pitches.length)];
  }
}
