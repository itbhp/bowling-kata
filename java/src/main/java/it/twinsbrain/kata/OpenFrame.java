package it.twinsbrain.kata;

import static java.util.Optional.ofNullable;

import java.util.Objects;

public final class OpenFrame implements Frame {
  private final int first;
  private final Integer second;
  private final int frameNumber;

  public OpenFrame(int first, Integer second, int frameNumber) {
    this.first = first;
    this.second = second;
    this.frameNumber = frameNumber;
  }

  @Override
  public void addScoreTo(Result result) {
    result.add(first + ofNullable(second).orElse(0), frameNumber);
  }

  @Override
  public int frameNumber() {
    return frameNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    OpenFrame openFrame = (OpenFrame) o;
    return first == openFrame.first && frameNumber == openFrame.frameNumber && Objects.equals(second, openFrame.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second, frameNumber);
  }

  @Override
  public String toString() {
    return "OpenFrame{"
        + "first="
        + first
        + ", second="
        + second
        + ", frameNumber="
        + frameNumber
        + '}';
  }
}
