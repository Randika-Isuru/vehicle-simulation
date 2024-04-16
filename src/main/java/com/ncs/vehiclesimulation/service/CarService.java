package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Direction;

import java.util.Map;


public interface CarService {

    String moveVehicle(Map<String, BaseVehicle> vehicleMap, int height, int width);
}
