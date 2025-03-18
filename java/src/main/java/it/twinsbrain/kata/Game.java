package it.twinsbrain.kata;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private final List<Integer> rolls = new ArrayList<>();

  public void roll(int pins) {
    this.rolls.add(pins);
  }

  public int score() {
    var frames = FramesParser.parse(rolls);
    var result = new Result();
    frames.forEach(frame -> frame.addScoreTo(result));
    return result.score();
  }
}
