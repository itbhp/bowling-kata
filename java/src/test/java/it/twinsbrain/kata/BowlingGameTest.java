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

  @Test
  void on_multiple_rolls_no_spare_no_strike_right_score() {
    game.roll(4);
    game.roll(5);

    assertThat(game.score(), equalTo(9));
  }

  @Test
  void on_complete_game_no_spare_no_strike_right_score() {
    game.roll(4);
    game.roll(5); //9

    game.roll(4);
    game.roll(5); //18

    game.roll(4);
    game.roll(5); //27

    game.roll(4);
    game.roll(5); //36

    game.roll(4);
    game.roll(5); //45

    game.roll(4);
    game.roll(5); //54

    game.roll(4);
    game.roll(5); //63

    game.roll(4);
    game.roll(5); //72

    game.roll(4);
    game.roll(5); //81

    game.roll(4);
    game.roll(5); //90

    assertThat(game.score(), equalTo(90));
  }
}
