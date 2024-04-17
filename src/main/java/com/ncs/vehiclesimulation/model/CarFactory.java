package com.ncs.vehiclesimulation.model;

import org.springframework.stereotype.Component;

/**
 * Represents a Car Factory in the application.
 * This will provide functionality for create varies type of car object.
 *
 * @author Randika Isuru Vijayanga
 * @version 1.0
 */
@Component
public class CarFactory {

    /**
     * Retrieves the final car object.
     *
     * @param id The car id.
     * @param x The x position of the grid.
     * @param y The x position of the grid.
     * @return new car object.
     */
    public Car createCar(String id, int x, int y, Direction direction, String commands) {
        return new Car(id, x, y, direction, commands);
    }
}
