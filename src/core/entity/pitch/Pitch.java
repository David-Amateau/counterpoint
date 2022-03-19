package core.entity.pitch;

public class Pitch implements Comparable<Pitch>{

  private final PitchClass pitchClass;
  private final int octave;
  private final int noteNumber;

  public Pitch(PitchClass pitchClass, int octave) {
    this.pitchClass = pitchClass;
    this.octave = octave;
    this.noteNumber = findNoteNumber(pitchClass, octave);
  }

  //TODO TEST
  private int findNoteNumber(PitchClass pitchClass, int octave) {
    // 1. Adjust pitch number so pitch class number and note number are in line
      // Example octave = 0 pitchClass = "A" pitchClassNumber = 9
      // Output should be 0
      // Example octave = 1 pitchClass = "C" pitchClassNumber = 0
      // Output should be 3
      // Example octave = 1 pitchClass = "A" pitchClassNumber = 9
      // Output should be 12
      // Example octave = 1 pitchClass = "C" pitchClassNumber = 0
      // Output should be 15
    int noteNumber = pitchClass.getPitchNumber();
    if (noteNumber < 0) throw new IllegalArgumentException("Valid Pitch Numbers are 0-11");

    if (pitchClass.getPitchLetter().getLetter() < 'C') {
      noteNumber -= 9;
      if (octave == 0) return noteNumber;
    } else {
      noteNumber += 3;
    }

    return noteNumber * octave;
  }

  public boolean isHigherThan(Pitch that) {
    return this.noteNumber > that.noteNumber;
  }

  public boolean isLowerThan(Pitch that) {
    return this.noteNumber < that.noteNumber;
  }

  /**
   * @return The distance in semitones between pitches.
   */
  @Override
  public int compareTo(Pitch that) {
    return this.noteNumber - that.noteNumber;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Pitch)) {
      return false;
    }

    Pitch pitch = (Pitch) that;

    if (this.getOctave() != pitch.getOctave()) {
      return false;
    }
    if (this.getNoteNumber() != pitch.getNoteNumber()) {
      return false;
    }
    return this.getPitchClass().equals(pitch.getPitchClass());
  }

  @Override
  public int hashCode() {
    int result = getPitchClass().hashCode();
    result = 31 * result + getOctave();
    result = 31 * result + getNoteNumber();
    return result;
  }

  public PitchClass getPitchClass() {
    return pitchClass;
  }
  public int getOctave() {
    return octave;
  }
  public int getNoteNumber() {
    return noteNumber;
  }
//  public Pitch(PitchClass pitchClass, int noteNumber) {
//    this.pitchClass = pitchClass;
//    this.noteNumber = noteNumber;
//    this.octave = findOctave(noteNumber);
//  }
//
//  private int findOctave(int noteNumber) {
//    // A0 = 0, C1 = 3, A1 = 12, C2 = 15
//    if (noteNumber < 3) return 0;
//
//    int octave = 1;
//    int maxNoteInOctave = 14;
//
//    while (noteNumber > maxNoteInOctave) {
//      maxNoteInOctave += 12;
//      octave++;
//    }
//    return octave;
//  }
}
