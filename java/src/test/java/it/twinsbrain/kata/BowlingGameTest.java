package it.twinsbrain.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BowlingGameTest {

  private static final Consumer<Game> ZERO_PINS = pinsDown(0);

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
  @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
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
  void worste_game() {
    roll(20, ZERO_PINS);
    assertThat(game.score(), equalTo(0));
  }

  @Test
  void one_pin_then_zero() {
    roll(1);
    roll(19, ZERO_PINS);
    assertThat(game.score(), equalTo(1));
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

  @Disabled
  void spare_on_the_last_frame() {
    roll(18, pinsDown(4)); // 4 * 18 = 72
    roll(4, 6, 4);

    assertThat(game.score(), equalTo(4 * 18 + 14 )); // 86
  }

  private static Consumer<Game> pinsDown(int pins) {
    return game -> game.roll(pins);
  }

  private void roll(int... rolledPins) {
    Arrays.stream(rolledPins).forEach(pins -> game.roll(pins));
  }

  private void roll(int times, Consumer<Game> play) {
    IntStream.range(0, times - 1).forEach(_ -> play.accept(game));
  }
}
