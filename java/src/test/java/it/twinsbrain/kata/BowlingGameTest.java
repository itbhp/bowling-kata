package it.twinsbrain.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

class BowlingGameTest {

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
    roll(pins);
    assertThat(game.score(), equalTo(pins));
  }

  @Test
  void on_multiple_rolls_no_spare_no_strike_right_score() {
    roll(4, 5);

    assertThat(game.score(), equalTo(9));
  }

  @Test
  void on_complete_game_no_spare_no_strike_right_score() {
    roll(4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5);

    assertThat(game.score(), equalTo(9 * 10)); // 90
  }

  @Test
  void one_spare_and_a_normal_frame() {
    roll(4, 6, 4, 5);

    assertThat(game.score(), equalTo(14 + 9)); // 23
  }

  private void roll(int...rolledPins) {
    Arrays.stream(rolledPins).forEach(pins -> game.roll(pins));
  }
}
