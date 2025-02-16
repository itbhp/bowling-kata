package it.twinsbrain.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

  @ParameterizedTest
  @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
  void one_or_ten_pins_down_same_score(int pins) {
    game.roll(pins);

    assertThat(game.score(), equalTo(pins));
  }
}
