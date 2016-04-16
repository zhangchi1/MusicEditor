package cs3500.music.model;

import java.util.Comparator;

/**
 * Compares notes first by pitch in natural order, then by starting time with the highest start time
 * first, and finally by duration with the longest duration first
 */
class NoteComparator implements Comparator<Note> {

  @Override
  public int compare(Note o1, Note o2) {
    int comparePitches = o1.getPitch().compareTo(o2.getPitch());
    if (comparePitches != 0) {
      return comparePitches;
    } else {
      int compareStarts = o2.getStartTime() - o1.getStartTime();
      if (compareStarts != 0) {
        return compareStarts;
      } else {
        return o2.getDuration() - o1.getDuration();
      }
    }
  }

}
