package it.twinsbrain.kata;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private final List<Integer> rolls = new ArrayList<>();

  public void roll(int pins) {
    this.rolls.add(pins);
  }

  public int score() {
    var score = 0;
    var index = 0;
    while (index < rolls.size()) {
      var frameScore = rolls.get(index);
      var moveIndex = 0;
      if (index + 1 < rolls.size() - 1) {
        frameScore += rolls.get(index + 1);
        moveIndex = 2;
      } else {
        moveIndex = 1;
      }

      if (frameScore == 10 && (index + 2) < rolls.size() - 1) {
        frameScore += rolls.get(index + 2);
      }
      index = index + moveIndex;
      score += frameScore;
    }
    return score;
  }
}
