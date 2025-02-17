package it.twinsbrain.kata;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private final List<Integer> pins = new ArrayList<>();

  public void roll(int pins) {
    this.pins.add(pins);
  }

  public int score() {
    var score = 0;
    for (int i = 0; i < pins.size(); i = i + 2) {
      score += pins.get(i);
      if (i + 1 < pins.size()) {
        score += pins.get(i+1);
      }
    }
    return score;
  }
}
