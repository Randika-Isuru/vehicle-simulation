package com.ncs.vehiclesimulation.model;

public enum Direction {
    N, E, S, W;

    public Direction rotateLeft() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction rotateRight() {
        return values()[(ordinal() + 1) % 4];
    }
}
