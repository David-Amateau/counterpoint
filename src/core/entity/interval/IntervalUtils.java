package core.entity.interval;

import static core.entity.interval.IntervalQuality.*;
import static core.entity.interval.IntervalType.*;

import java.util.HashMap;
import java.util.List;

public class IntervalUtils {

  static HashMap<IntervalQuality, IntervalType> loadIntervalQualities() {
    return new HashMap<IntervalQuality, IntervalType>() {
      {
        put(UNISON, PERFECT_CONSONANCE);
        put(MINOR_SECOND, DISSONANCE);
        put(MAJOR_SECOND, DISSONANCE);
        put(MINOR_THIRD, IMPERFECT_CONSONANCE);
        put(MAJOR_THIRD, IMPERFECT_CONSONANCE);
        put(PERFECT_FOURTH, DISSONANCE);
        put(AUGMENTED_FOURTH, DISSONANCE);
        put(DIMINISHED_FIFTH, DISSONANCE);
        put(PERFECT_FIFTH, PERFECT_CONSONANCE);
        put(MINOR_SIXTH, IMPERFECT_CONSONANCE);
        put(MAJOR_SIXTH, IMPERFECT_CONSONANCE);
        put(MINOR_SEVENTH, DISSONANCE);
        put(MAJOR_SEVENTH, DISSONANCE);
        put(OCTAVE, PERFECT_CONSONANCE);
      }
    };
  }

  static List<IntervalQuality> loadConsonantIntervals() {
    return List.of(
        UNISON,
        MINOR_THIRD,
        MAJOR_THIRD,
        PERFECT_FIFTH,
        MAJOR_SIXTH,
        MAJOR_SIXTH,
        OCTAVE);
  }

  static List<IntervalQuality> loadDissonantIntervals() {
    return List.of(
        MINOR_SECOND,
        MAJOR_SECOND,
        PERFECT_FOURTH,
        AUGMENTED_FOURTH,
        DIMINISHED_FIFTH,
        MINOR_SEVENTH,
        MAJOR_SEVENTH);
  }
}
