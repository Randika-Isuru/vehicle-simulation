package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Direction;


public interface CarService {
    void moveForward();

    void rotateLeft();

    void rotateRight();

    void setPosition(String id, int x, int y, Direction direction, String commands);

    int getX();

    int getY();

    Direction getDirection();
}
