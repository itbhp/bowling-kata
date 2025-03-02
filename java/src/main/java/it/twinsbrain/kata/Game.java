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
    var frameCount = 1;
    while (index < rolls.size() && frameCount <= 10) {
      var frameScore = rolls.get(index);
      var moveIndex = 0;
      if (frameScore == 10) { // strike
        moveIndex = 1;
        frameScore += strikeBonus(index);
      } else if (index + 1 < rolls.size()) {
        frameScore += rolls.get(index + 1);
        if (frameScore == 10) { // spare
          frameScore += spareBonus(index);
        }
        moveIndex = 2;
      } else {
        moveIndex = 1; // incomplete game
      }

      frameCount++;
      index = index + moveIndex;
      score += frameScore;
    }
    return score;
  }

  private Integer spareBonus(int index) {
    return (index + 2) < rolls.size() ? rolls.get(index + 2) : 0;
  }

  private Integer strikeBonus(int index) {
    var bonus = 0;
    if (index + 1 < rolls.size()) {
      bonus += rolls.get(index + 1);
    }
    if (index + 2 < rolls.size()) {
      bonus += rolls.get(index + 2);
    }
    return bonus;
  }
}
