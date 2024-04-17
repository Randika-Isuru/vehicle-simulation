package com.example.simulation.service;

import com.example.simulation.model.BaseVehicle;

import java.util.Map;

/**
 * Represents a Car service in the application.
 * This will provide functionality for managing vehicle information.
 *
 * @author Randika Isuru Vijayanga
 * @version 1.0
 */
public interface CarService {

    /**
     * Retrieves the final result of after moving the vehicle once get all the input from user.
     * if single vehicle moved, return final position with the direction ex : "4 3 S".
     * 4 denote the final x position
     * 3 denote the final y position
     * S denote the final direction of the vehicle
     * If no collision happen in parallel run of multiple vehicles, returns ""no collision"".
     *
     * @param vehicleMap The key of map is car id and value is Vehicle object.
     * @param height The height of the grid.
     * @param width The width of the grid
     * @return final result of after moving the vehicle once get all the input from user.
     *      if single vehicle moved, return final position with the direction ex : "4 3 S".
     *      If no collision happen in parallel run of multiple vehicles, returns ""no collision"".
     */
    String moveVehicle(Map<String, BaseVehicle> vehicleMap, int height, int width);
}
