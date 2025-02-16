package it.twinsbrain.kata;

public class Game {

  private int pins;

  public void roll(int pins) {
    this.pins += pins;
  }

  public int score() {
    return pins;
  }
}
