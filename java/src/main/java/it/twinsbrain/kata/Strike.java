package it.twinsbrain.kata;

import static java.util.Optional.ofNullable;

import java.util.Objects;

public final class Strike implements Frame {

  private final Integer firstBonus;
  private final Integer secondBonus;
  private final int frameNumber;

  public Strike(Integer firstBonus, Integer secondBonus, int frameNumber) {
    this.firstBonus = firstBonus;
    this.secondBonus = secondBonus;
    this.frameNumber = frameNumber;
  }

  @Override
  public void addScoreTo(Result result) {
    var bonus = ofNullable(firstBonus).flatMap(first -> ofNullable(secondBonus).map(second -> first + second)).orElse(0);
    result.add(10 + bonus, frameNumber);
  }

  @Override
  public int frameNumber() {
    return frameNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Strike strike = (Strike) o;
    return frameNumber == strike.frameNumber
        && Objects.equals(firstBonus, strike.firstBonus)
        && Objects.equals(secondBonus, strike.secondBonus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstBonus, secondBonus, frameNumber);
  }

  @Override
  public String toString() {
    return "Strike{"
        + "firstBonus="
        + firstBonus
        + ", secondBonus="
        + secondBonus
        + ", frameNumber="
        + frameNumber
        + '}';
  }
}
