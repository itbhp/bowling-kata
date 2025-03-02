package it.twinsbrain.kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    rollMany(20, 0);
    assertThat(game.score(), equalTo(0));
  }

  @Test
  void one_pin_then_zero() {
    roll(1);
    rollMany(19, 0);
    assertThat(game.score(), equalTo(1));
  }

  @Test
  void on_complete_game_no_spare_no_strike_right_score() {
    rollManySameFrame(10, 5, 4);
    assertThat(game.score(), equalTo(9 * 10)); // 90
  }

  @Test
  void one_spare_and_a_normal_frame() {
    roll(4, 6, 4, 5);

    assertThat(game.score(), equalTo(14 + 9)); // 23
  }

  @Test
  void spare_on_the_last_frame() {
    rollMany(18, 4); // 4 * 18 = 72
    roll(4, 6, 4);

    assertThat(game.score(), equalTo(4 * 18 + 14)); // 86
  }

  @Test
  void strike_and_a_normal_frame() {
    roll(10);
    roll(4, 4);

    assertThat(game.score(), equalTo(18 + 8)); // 22
  }

  @Test
  void strike_on_the_last_frame() {
    rollManySameFrame(9,2,4);
    roll(10, 4, 4);

    assertThat(game.score(), equalTo(9 * 6 + 18  )); // 72
  }

  @Test
  void gold_game_all_strikes(){
    rollMany(12, 10);
    assertThat(game.score(), equalTo(300));
  }

  private void roll(int... rolledPins) {
    Arrays.stream(rolledPins).forEach(pins -> game.roll(pins));
  }

  private void rollMany(int times, int pins) {
    roll(Collections.nCopies(times, pins).stream().mapToInt(i -> i).toArray());
  }

  @SuppressWarnings("SameParameterValue")
  private void rollManySameFrame(int times, int first, int second) {
    roll(
        IntStream.range(0, times)
            .mapToObj(_ -> List.of(first, second))
            .flatMap(Collection::stream)
            .mapToInt(i -> i)
            .toArray());
  }
}
