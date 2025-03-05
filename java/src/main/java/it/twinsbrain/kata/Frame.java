package it.twinsbrain.kata;

public sealed interface Frame permits Spare, Strike,  OpenFrame{
    void addScoreTo(Result result);
    int frameNumber();
}
