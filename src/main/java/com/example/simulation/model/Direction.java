package com.example.simulation.model;

/**
 * Represents a Direction in the application.
 * This enum will provide functionality for managing direction information.
 *
 * @author Randika Isuru Vijayanga
 * @version 1.0
 */
public enum Direction {
    N, E, S, W;
    public Direction rotateLeft() {
        return values()[(ordinal() + 3) % 4];
    }
    public Direction rotateRight() {
        return values()[(ordinal() + 1) % 4];
    }
}
