package it.twinsbrain.kata;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private final List<Integer> pins = new ArrayList<>();

  public void roll(int pins) {
    this.pins.add(pins);
  }

  public int score() {
    var baseScore = pins.stream().mapToInt(i -> i).sum();
    var bonus = 0;
    for (int i = 2; i < pins.size(); i = i + 2) {
      if (pins.get(i - 2) + pins.get(i - 1) == 10) {
        bonus += pins.get(i);
      }
    }
    return baseScore + bonus;
  }
}
