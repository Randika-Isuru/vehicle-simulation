package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.Direction;


public interface CarService {
    void moveForward();

    void rotateLeft();

    void rotateRight();

    void setPosition(int x, int y, Direction direction);

    int getX();

    int getY();

    Direction getDirection();
}
