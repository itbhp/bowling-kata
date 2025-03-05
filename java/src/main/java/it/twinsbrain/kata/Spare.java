package it.twinsbrain.kata;

import static java.util.Optional.ofNullable;

import java.util.Objects;

public final class Spare implements Frame {

  private final int first;
  private final int second;
  private final Integer bonus;
  private final int frameNumber;

  public Spare(int first, int second, Integer bonus, int frameNumber) {
    this.first = first;
    this.second = second;
    this.bonus = bonus;
    this.frameNumber = frameNumber;
  }

  @Override
  public void addScoreTo(Result result) {
    result.add(first + second + ofNullable(bonus).orElse(0), frameNumber);
  }

  @Override
  public int frameNumber() {
    return frameNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Spare spare = (Spare) o;
    return first == spare.first
        && second == spare.second
        && frameNumber == spare.frameNumber
        && Objects.equals(bonus, spare.bonus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second, bonus, frameNumber);
  }

  @Override
  public String toString() {
    return "Spare{"
        + "first="
        + first
        + ", second="
        + second
        + ", bonus="
        + bonus
        + ", frameNumber="
        + frameNumber
        + '}';
  }
}
