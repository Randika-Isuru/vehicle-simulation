package com.ncs.vehiclesimulation.model;

import org.springframework.stereotype.Component;

@Component
public class Car extends BaseVehicle{
    public Car(String id, int x, int y, Direction direction, String commands) {
        super(id, x, y, direction, commands);
    }

    public Car(){

    }

    public void moveForward() {
        switch (direction) {
            case N:
                y++;
                break;
            case E:
                x++;
                break;
            case S:
                y--;
                break;
            case W:
                x--;
                break;
        }
    }

    public void rotateLeft() {
        direction = direction.rotateLeft();
    }

    public void rotateRight() {
        direction = direction.rotateRight();
    }
}