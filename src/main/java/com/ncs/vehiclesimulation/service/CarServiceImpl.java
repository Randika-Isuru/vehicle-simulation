package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Car;
import com.ncs.vehiclesimulation.model.Direction;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private Car car;

    public CarServiceImpl(Car car) {
        this.car = car;
    }

    @Override
    public void setPosition(String id, int x, int y, Direction direction, String commands) {
        this.car = new Car(id, x, y, direction, commands);
    }

    @Override
    public void moveForward() {
        car.moveForward();
    }

    @Override
    public void rotateLeft() {
        car.rotateLeft();
    }

    @Override
    public void rotateRight() {
        car.rotateRight();
    }

    @Override
    public int getX() {
        return car.getX();
    }

    @Override
    public int getY() {
        return car.getY();
    }

    @Override
    public Direction getDirection() {
        return car.getDirection();
    }
}