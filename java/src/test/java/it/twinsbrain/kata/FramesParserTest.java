package it.twinsbrain.kata;

import static it.twinsbrain.kata.FramesParser.parse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class FramesParserTest {

  @Test
  void parses_one_open_frame() {
    assertThat(parse(List.of(5, 4)), equalTo(List.of(new OpenFrame(5, 4, 1))));
  }

  @Test
  void parses_many_open_frames() {
    assertThat(
        parse(List.of(5, 4, 3, 4, 2, 6)),
        equalTo(List.of(new OpenFrame(5, 4, 1), new OpenFrame(3, 4, 2), new OpenFrame(2, 6, 3))));
  }

  @Test
  void parses_an_open_frame_a_spare_and_an_incomplete_open_frame() {
    assertThat(
        parse(List.of(5, 4, 6, 4, 2)),
        equalTo(List.of(new OpenFrame(5, 4, 1), new Spare(6, 4, 2, 2), new OpenFrame(2, null, 3))));
  }

  @Test
  void parses_an_spare_on_the_last_frame() {
    assertThat(
        parse(List.of(5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 6, 4, 2)),
        equalTo(
            List.of(
                new OpenFrame(5, 4, 1),
                new OpenFrame(5, 4, 2),
                new OpenFrame(5, 4, 3),
                new OpenFrame(5, 4, 4),
                new OpenFrame(5, 4, 5),
                new OpenFrame(5, 4, 6),
                new OpenFrame(5, 4, 7),
                new OpenFrame(5, 4, 8),
                new OpenFrame(5, 4, 9),
                new Spare(6, 4, 2, 10))));
  }

  @Test
  void parses_a_strike() {
    assertThat(parse(List.of(10)), equalTo(List.of(new Strike(null, null, 1))));
  }

  @Test
  void parses_a_strike_and_an_incomplete_frame() {
    assertThat(
        parse(List.of(10, 3)), equalTo(List.of(new Strike(3, null, 1), new OpenFrame(3, null, 2))));
  }

  @Test
  void parses_a_strike_and_an_open_frame() {
    assertThat(
        parse(List.of(10, 3, 6)), equalTo(List.of(new Strike(3, 6, 1), new OpenFrame(3, 6, 2))));
  }

  @Test
  void parses_an_open_frame_and_a_strike() {
    assertThat(
        parse(List.of(5, 4, 10)),
        equalTo(List.of(new OpenFrame(5, 4, 1), new Strike(null, null, 2))));
  }

  @Test
  void parses_an_open_frame_a_strike_and_an_incomplete_frame() {
    assertThat(
        parse(List.of(5, 4, 10, 3)),
        equalTo(
            List.of(new OpenFrame(5, 4, 1), new Strike(3, null, 2), new OpenFrame(3, null, 3))));
  }

  @Test
  void parses_an_open_frame_a_strike_and_an_open_frame() {
    assertThat(
        parse(List.of(5, 4, 10, 3, 6)),
        equalTo(List.of(new OpenFrame(5, 4, 1), new Strike(3, 6, 2), new OpenFrame(3, 6, 3))));
  }

  @Test
  void parses_strike_on_last_frame() {
    assertThat(
        parse(List.of(5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 10, 5, 4)),
        equalTo(
            List.of(
                new OpenFrame(5, 4, 1),
                new OpenFrame(5, 4, 2),
                new OpenFrame(5, 4, 3),
                new OpenFrame(5, 4, 4),
                new OpenFrame(5, 4, 5),
                new OpenFrame(5, 4, 6),
                new OpenFrame(5, 4, 7),
                new OpenFrame(5, 4, 8),
                new OpenFrame(5, 4, 9),
                new Strike(5, 4, 10))));
  }

  @Test
  void parses_gold_game_all_strikes() {
    assertThat(
        parse(Collections.nCopies(12, 10)),
        equalTo(
            List.of(
                new Strike(10, 10, 1),
                new Strike(10, 10, 2),
                new Strike(10, 10, 3),
                new Strike(10, 10, 4),
                new Strike(10, 10, 5),
                new Strike(10, 10, 6),
                new Strike(10, 10, 7),
                new Strike(10, 10, 8),
                new Strike(10, 10, 9),
                new Strike(10, 10, 10))));
  }
}
