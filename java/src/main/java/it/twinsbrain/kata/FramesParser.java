package it.twinsbrain.kata;

import java.util.*;

public class FramesParser {
  private FramesParser() {
  }

  public static List<Frame> parse(List<Integer> rolls) {
    var parser = new Parser();
    rolls.forEach(parser::accept);
    return parser.result();
  }

  private static class Parser {
    protected static final int MAXIMUM_NUMBER_OF_FRAMES = 10;
    protected static final int MAXIMUM_NUMBER_OF_PINS = 10;
    private State state = new EmptyFrame();

    public void accept(int roll) {
      state = state.accept(roll);
    }

    public List<Frame> result() {
      State nextState = state;
      final List<Frame> result = new ArrayList<>();
      while (nextState != null) {
        var frames = nextState.toFrames();
        if (!frames.isEmpty()) {
          result.addAll(frames);
        }
        nextState = nextState.previous().orElse(null);
      }
      result.sort(Comparator.comparing(Frame::frameNumber));
      return result;
    }

    private sealed interface State {

      Optional<State> previous();

      State accept(int roll);

      List<Frame> toFrames();
    }

    private record EmptyFrame() implements State {
      @Override
      public Optional<State> previous() {
        return Optional.empty();
      }

      @Override
      public State accept(int roll) {
        if (roll == MAXIMUM_NUMBER_OF_PINS) {
          return new StrikeMissingBonuses(this, 0);
        }
        return new IncompleteFrame(roll, this, 0);
      }

      @Override
      public List<Frame> toFrames() {
        return List.of();
      }
    }

    private record IncompleteFrame(int firstRoll, State previousState, int frameNumber)
        implements State {
      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        if (roll + firstRoll == MAXIMUM_NUMBER_OF_PINS) {
          return new SpareMissingBonus(firstRoll, roll, previousState, frameNumber);
        }
        return new OpenFrame(firstRoll, roll, previousState, frameNumber + 1);
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(new it.twinsbrain.kata.OpenFrame(firstRoll, null, frameNumber + 1));
      }
    }

    private record OpenFrame(int first, int second, State previousState, int frameNumber)
        implements State {
      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        if (roll == MAXIMUM_NUMBER_OF_PINS) {
          return new StrikeMissingBonuses(this, frameNumber);
        }
        return new IncompleteFrame(roll, this, frameNumber);
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(new it.twinsbrain.kata.OpenFrame(first, second, frameNumber));
      }
    }

    private record SpareMissingBonus(int first, int second, State previousState, int frameNumber)
        implements State {
      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        var spareWithBonus = new Spare(first, second, roll, previousState, frameNumber + 1);
        if (frameNumber + 1 < MAXIMUM_NUMBER_OF_FRAMES) {
          return new IncompleteFrame(roll, spareWithBonus, frameNumber + 1);
        } else {
          return spareWithBonus;
        }
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(new it.twinsbrain.kata.Spare(first, second, null, frameNumber + 1));
      }
    }

    private record Spare(int first, int second, int bonus, State previousState, int frameNumber)
        implements State {
      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        throw new IllegalStateException("Cannot happen, completed in the SpareMissingBonus");
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(new it.twinsbrain.kata.Spare(first, second, bonus, frameNumber));
      }
    }

    private record StrikeMissingBonuses(State previousState, int frameNumber) implements State {

      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        if (roll == MAXIMUM_NUMBER_OF_PINS) {
          return new TwoConsecutiveStrikes(previousState, frameNumber);
        } else return new StrikeAndIncompleteFrame(previousState, roll, frameNumber);
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(new it.twinsbrain.kata.Strike(null, null, frameNumber + 1));
      }
    }

    private record TwoConsecutiveStrikes(State previousState, int frameNumber) implements State {
      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        var strike = new Strike(MAXIMUM_NUMBER_OF_PINS, roll, previousState, frameNumber + 1);
        if (roll == MAXIMUM_NUMBER_OF_PINS) {
          return new TwoConsecutiveStrikes(strike, frameNumber + 1);
        } else {
          return new StrikeAndIncompleteFrame(strike, roll, frameNumber + 1);
        }
      }

      @Override
      public List<Frame> toFrames() {
        if (frameNumber + 2 <= MAXIMUM_NUMBER_OF_FRAMES) {
          return List.of(
              new it.twinsbrain.kata.Strike(MAXIMUM_NUMBER_OF_PINS, null, frameNumber + 1),
              new it.twinsbrain.kata.Strike(null, null, frameNumber + 2));
        } else {
          return List.of();
        }
      }
    }

    private record StrikeAndIncompleteFrame(State previousState, int first, int frameNumber)
        implements State {

      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        var strikeFrameNumber = frameNumber + 1;
        var strike = new Strike(first, roll, previousState, strikeFrameNumber);
        if (strikeFrameNumber == MAXIMUM_NUMBER_OF_FRAMES) {
          return strike;
        } else {
          return new OpenFrame(first, roll, strike, frameNumber + 2);
        }
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(
            new it.twinsbrain.kata.Strike(first, null, frameNumber + 1),
            new it.twinsbrain.kata.OpenFrame(first, null, frameNumber + 2));
      }
    }

    private record Strike(int firstBonus, int secondBonus, State previousState, int frameNumber)
        implements State {
      @Override
      public Optional<State> previous() {
        return Optional.of(previousState);
      }

      @Override
      public State accept(int roll) {
        if (roll == MAXIMUM_NUMBER_OF_PINS) {
          return new StrikeMissingBonuses(this, frameNumber);
        }
        return new IncompleteFrame(roll, this, frameNumber);
      }

      @Override
      public List<Frame> toFrames() {
        return List.of(new it.twinsbrain.kata.Strike(firstBonus, secondBonus, frameNumber));
      }
    }
  }
}
