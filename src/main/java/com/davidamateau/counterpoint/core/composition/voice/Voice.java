package com.davidamateau.counterpoint.core.composition.voice;

import com.davidamateau.counterpoint.core.entity.key.Key;
import com.davidamateau.counterpoint.core.entity.key.ScaleDegree;
import com.davidamateau.counterpoint.core.entity.note.Note;
import com.davidamateau.counterpoint.core.entity.note.PitchClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.davidamateau.counterpoint.core.entity.key.ScaleDegree.*;

/**
 * Base class for a contrapuntal voice.
 */
public class Voice {

  protected List<Note> notes;
  protected final Key key;
  protected final HashMap<ScaleDegree, PitchClass> scaleDegreesToPitchClass;
  protected HashMap<PitchClass, Integer> pitchClassAppearance = new HashMap<>();

  public Voice(Key key) {
    this(key, new ArrayList<>());
  }

  public Voice(Key key, List<Note> notes) {
    this.key = key;
    this.notes = notes;
    this.scaleDegreesToPitchClass = loadScaleDegrees();
  }

  /**
   * Maps scale degrees to pitch classes.
   *
   * @return map of scale degree to pitch class.
   */
  private HashMap<ScaleDegree, PitchClass> loadScaleDegrees() {
    return new HashMap<>() {
      {
        put(TONIC, key.getTonic());
        put(SUPER_TONIC, key.getSuperTonic());
        put(MEDIANT, key.getMediant());
        put(SUB_DOMINANT, key.getSubDominant());
        put(DOMINANT, key.getDominant());
        put(SUB_MEDIANT, key.getSubMediant());
        put(LEADING_TONE, key.getLeadingTone());
      }
    };
  }

  /**
   * Adds the pen ultimate note to a voice.
   *
   * @param penUltimate the pen ultimate note.
   * @param secondToLastIndexInVoice the index of the pen ultimate note.
   */
  public void addPenUltimate(Note penUltimate, int secondToLastIndexInVoice) {
    addToAppearanceMap(penUltimate.getPitchClass());
    this.notes.add(secondToLastIndexInVoice, penUltimate);
  }

  /**
   * Adds the ultimate note to the voice.
   *
   * @param ultimate the ultimate note.
   * @param lastIndexInVoice the index of the ultimate.
   */
  public void addUltimate(Note ultimate, int lastIndexInVoice) {
    addToAppearanceMap(ultimate.getPitchClass());
    this.notes.add(lastIndexInVoice, ultimate);
  }

  /**
   * Adds a given note to the voice.
   *
   * @param note the given note.
   */
  public void addNote(Note note) {
    addToAppearanceMap(note.getPitchClass());
    this.notes.add(note);
  }

  /**
   * Adds or increases the count of appearances of a given pitch class in a voice.
   *
   * @param pitchClass the pitch class being added or increased in the map.
   */
  protected void addToAppearanceMap(PitchClass pitchClass) {
    if (pitchClassAppearance.containsKey(pitchClass)) {
      pitchClassAppearance.put(pitchClass, pitchClassAppearance.get(pitchClass) + 1);
    } else {
      pitchClassAppearance.put(pitchClass, 1);
    }
  }

  public List<Note> getNotes() {
    return this.notes;
  }

  protected void setVoiceLength(int cantusLength) {
    this.notes = new ArrayList<>(cantusLength);
  }

  protected Note getUltimate() {
    //TODO
    return null;
  }
}
