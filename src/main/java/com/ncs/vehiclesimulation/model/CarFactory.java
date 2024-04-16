package com.ncs.vehiclesimulation.model;

import org.springframework.stereotype.Component;

@Component
public class CarFactory {
    public Car createCar(String id, int x, int y, Direction direction, String commands) {
        return new Car(id, x, y, direction, commands);
    }
}
