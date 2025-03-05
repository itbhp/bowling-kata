package it.twinsbrain.kata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Result {
    private static final Logger LOG = LoggerFactory.getLogger(Result.class);

    private int value;

    public void add(int frameScore, int frameNumber){
        LOG.info("adding framescore {} for frame number {}, previous score {}", frameScore, frameNumber, value);
        value += frameScore;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "value=" + value +
                '}';
    }
}
