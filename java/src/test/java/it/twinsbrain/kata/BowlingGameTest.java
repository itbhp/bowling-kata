package it.twinsbrain.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BowlingGameTest {

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game();
  }

  @Test
  void no_pin_down_zero_score() {
    game.roll(0);

    assertThat(game.score(), equalTo(0));
  }

  @Test
  void one_pin_down_one_score() {
    game.roll(1);

    assertThat(game.score(), equalTo(1));
  }
}
