package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Represents a Generic Music Model
 */
class MusicPieceModelImpl implements MusicPieceModel {

  private final NoteComparator compareNotes = new NoteComparator();
  // key is the beat index
  //INVARIANT: beat lists are sorted in order as defined by NoteComparator
  private final TreeMap<Integer, List<Note>> notes = new TreeMap<>();
  /**
   * control the lowest and higest notevalue as well as the lastbeat in a Music Model all of the
   * fields can not be negative numbers notevalue: 0 <= notevalue <= 119
   */
  private AbsolutePitch lowestPitch, highestPitch;
  //INVARIANT: tempo >= 1000
  //microseconds per beat
  private int tempo;

  /**
   * Constructor for Generic Music Model Editor
   *
   * @throws IllegalArgumentException if the tempo is < 1000
   */
  MusicPieceModelImpl(Collection<Note> listOfNotes, int tempo) {
    if (tempo < 1000) {
      throw new IllegalArgumentException("Tempo below playable range");
    }
    this.lowestPitch = AbsolutePitchFactory.MAX_PITCH;
    this.highestPitch = AbsolutePitchFactory.MIN_PITCH;
    this.tempo = tempo;
    listOfNotes.forEach(this::addNote);
  }

  /**
   * update the lowestPitch, highestPitch and lastBeat
   *
   * @param note update the music model based on the given note
   */
  private void updateModelValue(Note note) {
    AbsolutePitch newPitch = note.getPitch();
    if (this.lowestPitch.compareTo(newPitch) > 0) {
      this.lowestPitch = newPitch;
    }
    if (this.highestPitch.compareTo(newPitch) < 0) {
      this.highestPitch = newPitch;
    }
  }

  /**
   * Adds a {@link Note} to a editor model and update the modelvalue
   *
   * @param note the given note to be added
   */
  @Override
  public MusicPieceModel addNote(Note note) {
    int noteLen = note.getStartTime() + note.getDuration();
    //add the note to each beat on which it is still playing
    for (int beat = note.getStartTime(); beat < noteLen; beat++) {
      this.addNoteAt(note, beat);
    }
    this.updateModelValue(note);
    return this;
  }

  /**
   * Add the note at the given beat without updating the bounds. Maintains the list ordering
   * invariant. Used to add notes to beats where they are suspended.
   *
   * @param note the note to add
   * @param beat the beat to add it at
   */
  private void addNoteAt(Note note, int beat) {
    if (this.notes.containsKey(beat)) {
      List<Note> beatNotes = this.notes.get(beat);
      int index = 0;
      while (index < beatNotes.size()
              && compareNotes.compare(beatNotes.get(index), note) < 0) {
        index++;
      }
      beatNotes.add(index, note);
    } else {
      List<Note> beatNotes = new ArrayList<>();
      beatNotes.add(note);
      notes.put(beat, beatNotes);
    }
  }

  /**
   * @param note the given note to be removed if there is no such note exists throw an exception
   */
  @Override
  public MusicPieceModel removeNote(Note note) {
    if (!notes.containsKey(note.getStartTime())) {
      throw new IllegalArgumentException("No such note exists!");
    } else if (!notes.get(note.getStartTime()).contains(note)) {
      throw new IllegalArgumentException("No such note exists!");
    } else {
      //removes the note from each beat it exists in
      int endTime = note.getStartTime() + note.getDuration();
      for (int i = note.getStartTime(); i < endTime; i++) {
        if (notes.containsKey(i)) {
          notes.get(i).remove(note);
        }
      }

      //if we removed the highest pitch, we have to recalculate it
      if (note.getPitch().compareTo(this.highestPitch()) == 0) {
        this.highestPitch = AbsolutePitchFactory.MIN_PITCH;
      }

      //if we removed the lowest pitch, we have to recalculate it
      if (note.getPitch().compareTo(this.lowestPitch()) == 0) {
        this.lowestPitch = AbsolutePitchFactory.MAX_PITCH;
      }

      for (List<Note> listnote : this.notes.values()) {
        listnote.forEach(this::updateModelValue);
      }
      //if we remove all the notes in a beat, remove that beat
      while (!notes.isEmpty() && notes.get(notes.lastKey()).isEmpty()) {
        notes.remove(notes.lastKey());
      }
    }
    return this;
  }

  @Override
  public MusicPieceModel copy() {
    return new MusicPieceModelImpl(this.getNotes(), this.tempo);
  }

  /**
   * add another viewModel to this and play simultaneously
   *
   * @param piece play this viewModel of music with another viewModel of music
   */
  @Override
  public MusicPieceModel playSimultaneously(MusicPieceModel piece) {
    return this.insert(piece, 0);
  }

  /**
   * get the all the notes in a list
   *
   * @return the hashmap for notes
   */
  @Override
  public List<Note> getNotes() {
    List<Note> listNotes = new ArrayList<>();
    for (Integer beat : notes.keySet()) {
      for (Note n : this.getNotesAt(beat)) {
        if (n.getStartTime() == beat) {
          listNotes.add(n);
        }
      }
    }
    return listNotes;
  }

  /**
   * add another viewModel to this and play consecutively
   *
   * @param piece play this viewModel of music with another viewModel of music the given music model
   *              play after this music model
   */
  @Override
  public MusicPieceModel playConsecutively(MusicPieceModel piece) {
    return this.insert(piece, this.getLength());
  }

  /**
   * {@inheritDoc}
   *
   * @param piece {@inheritDoc}
   * @param beat  {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public MusicPieceModel insert(MusicPieceModel piece, int beat) {
    if (beat < 0) {
      throw new IllegalArgumentException("cannot have a negative beat.");
    }
    piece.getNotes().forEach((n) -> addNote(n.addStartTime(beat)));
    return this;
  }

  /**
   * change the start time of a note
   *
   * @param note the given note
   * @param i    number of changing start time
   */
  @Override
  public MusicPieceModel changeNoteStartTime(Note note, int i) {
    if (!contains(note)) {
      throw new IllegalArgumentException("No such note exists!");
    } else {
      Note newNote = note.addStartTime(i);
      this.removeNote(note);
      this.addNote(newNote);

    }
    return this;
  }

  /**
   * Checks that this viewModel contains the given note
   *
   * @param note the note to check
   * @return true if the note is in the viewModel, false otherwise
   */
  private boolean contains(Note note) {
    return notes.containsKey(note.getStartTime()) &&
            notes.get(note.getStartTime()).contains(note);
  }

  /**
   * change the duration of a note
   *
   * @param note the given note
   * @param i    number of changing the duration
   */
  @Override
  public MusicPieceModel changeNoteDuration(Note note, int i) {
    if (!contains(note)) {
      throw new IllegalArgumentException("No such note exists!");
    } else {
      Note newNote = note.addNoteDuration(i);
      this.removeNote(note);
      this.addNote(newNote);

    }
    return this;
  }

  @Override
  public int getLength() {
    if (this.notes.size() == 0) {
      return 0;
    } else {
      return this.notes.lastKey() + 1;
    }
  }

  @Override
  public AbsolutePitch lowestPitch() {
    return this.lowestPitch;
  }

  @Override
  public AbsolutePitch highestPitch() {
    return this.highestPitch;
  }

  /**
   * change the note's location
   *
   * @param note the given note
   * @param i    number of changing the Note pitch location
   */
  @Override
  public MusicPieceModel changePitch(Note note, int i) {
    if (!contains(note)) {
      throw new IllegalArgumentException("No such note exists!");
    } else {
      Note newNote = note.changePitch(i);
      this.removeNote(note);
      this.addNote(newNote);
    }
    return this;
  }

  @Override
  public MusicPieceModel changePitch(int halfsteps) {
    //test that the desired change does not overflow
    // or underflow the available pitch range
    this.lowestPitch.changePitch(halfsteps);
    this.highestPitch.changePitch(halfsteps);
    for (Note note : this.getNotes()) {
      this.changePitch(note, halfsteps);
    }
    return this;
  }

  /**
   * Returns an unmodifiable list containing all of the notes at the given beat
   *
   * @param beat the given beat
   * @return the list of notes at a given beat
   * @throws IllegalArgumentException if beat < 0
   */
  @Override
  public List<Note> getNotesAt(int beat) {
    if (beat < 0) {
      throw new IllegalArgumentException("Not a valid beat!");
    } else if (notes.get(beat) != null) {
      return Collections.unmodifiableList(notes.get(beat));
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public MusicPieceModel build() {
    return this;
  }

  @Override
  public MusicPieceModel setTempo(int tempo) {
    if (tempo < 1000) {
      throw new IllegalArgumentException("Tempo below playable range");
    }
    this.tempo = tempo;
    return this;
  }

  @Override
  public MusicPieceModel addNote(int start, int end,
                                 int instrument, int pitch, int volume) {
    return this.addNote(NoteFactory.newNote(start, end - start,
            AbsolutePitchFactory.newAbsolutePitch(pitch), instrument, volume));
  }


  @Override
  public int getTempo() {
    return this.tempo;
  }
}



