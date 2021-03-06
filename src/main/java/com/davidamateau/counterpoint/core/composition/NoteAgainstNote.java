package com.davidamateau.counterpoint.core.composition;

import static com.davidamateau.counterpoint.core.entity.Utilities.DEFAULT_OCTAVE;

import com.davidamateau.counterpoint.core.entity.interval.Interval;
import com.davidamateau.counterpoint.core.entity.interval.IntervalType;
import com.davidamateau.counterpoint.core.entity.interval.IntervalQuality;
import com.davidamateau.counterpoint.core.entity.key.Key;
import com.davidamateau.counterpoint.core.entity.note.Note;
import com.davidamateau.counterpoint.core.entity.note.Pitch;
import com.davidamateau.counterpoint.core.composition.voice.CantusFirmusVoice;
import com.davidamateau.counterpoint.core.composition.voice.CounterPointVoiceFirstSpecies;
import com.davidamateau.counterpoint.core.entity.note.PitchClass;
import java.util.ArrayList;
import java.util.List;

public class NoteAgainstNote {

  private final Key key;
  private final CantusFirmusVoice cantusFirmusVoice;
  private final CounterPointVoiceFirstSpecies counterPointVoice;
  private final List<Interval> intervals;

  public NoteAgainstNote(Key key) {
    this(
        key,
        new CantusFirmusVoice(key),
        null);
  }

  public NoteAgainstNote(
      Key key,
      CantusFirmusVoice cantusFirmusVoice,
      CounterPointVoiceFirstSpecies counterPointVoice) {
    this.key = key;
    this.cantusFirmusVoice = cantusFirmusVoice;
    this.counterPointVoice = CounterPointVoiceFirstSpecies.createNewCounterpoint(cantusFirmusVoice, DEFAULT_OCTAVE);
    this.intervals = createIntervals();
  }

  public boolean isValidCantusFirstNote() {
    return cantusFirmusVoice.isRootPitch(key);
  }

  public boolean isValidOpeningInterval() {
    if (!isValidCantusFirstNote()) {
      return false;
    }
    return intervals.get(0).getType() == IntervalQuality.PERFECT_CONSONANCE;
  }

  public boolean isValidPenultimateNote() {
    PitchClass penUltimateNote =
        this.cantusFirmusVoice
            .getNotes()
            .get(cantusFirmusVoice.getNotes().size() - 2)
            .getPitchClass();

    return penUltimateNote.equals(key.getSuperTonic())
        || penUltimateNote.equals(key.getLeadingTone());
  }

  public boolean isValidPenultimateInterval() {
    IntervalType penUltimateIntervalType =
        this.intervals.get(this.intervals.size() - 2).getQuality();

    return penUltimateIntervalType.equals(IntervalType.MAJOR_SIXTH)
        || penUltimateIntervalType.equals(IntervalType.MINOR_THIRD);
  }

  public boolean isValidUltimateNote() {
    PitchClass ultimateNote =
        this.cantusFirmusVoice
            .getNotes()
            .get(cantusFirmusVoice.getNotes().size() - 1)
            .getPitchClass();

    return ultimateNote.equals(key.getTonic());
  }

  public boolean isValidUltimateInterval() {
    IntervalType ultimateIntervalType = this.intervals.get(this.intervals.size() - 1).getQuality();

    return ultimateIntervalType.equals(IntervalType.UNISON)
        || ultimateIntervalType.equals(IntervalType.OCTAVE);
  }

  public List<Interval> createIntervals() {
    List<Interval> intervals = new ArrayList<>();
    for (int i = 0; i < cantusFirmusVoice.getNotes().size() - 1; i++) {
      Note cantusNote = cantusFirmusVoice.getNotes().get(i);
      Note counterNote = counterPointVoice.getNotes().get(i);
      intervals.add(new Interval(cantusNote.getPitch(), counterNote.getPitch()));
    }
    return intervals;
  }

  public List<Interval> findIntervals(List<Pitch> cantusFirmus, List<Pitch> counterpoint) {
    List<Interval> intervals = new ArrayList<>();
    for (int i = 0; i < cantusFirmus.size() - 1; i++) {
      intervals.add(new Interval(cantusFirmus.get(i), counterpoint.get(i)));
    }
    return intervals;
  }

  public Interval createInterval(Note cantusNote, Note counterNote) {
    return new Interval(cantusNote.getPitch(), counterNote.getPitch());
  }
}
