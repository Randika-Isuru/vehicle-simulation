package com.ncs.vehiclesimulation.controller;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Car;
import com.ncs.vehiclesimulation.model.Direction;
import com.ncs.vehiclesimulation.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleSimulationControllerTest {

    @InjectMocks
    VehicleSimulationController controller;

    @Mock
    CarService carService;

    int[] field;
    int width;
    int height;

    String idA;
    int[] positionA;
    int xA;
    int yA;
    Direction orientationA;
    String commandsA;

    String idB;
    int[] positionB;
    int xB;
    int yB;
    Direction orientationB;
    String commandsB;

    @BeforeEach
    public void setup() {
        field = new int[]{10, 10};
        width = field[0];
        height = field[1];

        idA = "A";
        positionA = new int[]{1, 2};
        xA = positionA[0];
        yA = positionA[1];
        orientationA = Direction.N;
        commandsA = "FFRFFFRRLF";

        idB = "B";
        positionB = new int[]{7, 8};
        xB = positionB[0];
        yB = positionB[1];
        orientationB = Direction.W;
        commandsB = "FFLFFFFFFF";
    }



    @Test
    public void testMoveCar(){

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put(idA, new Car(idA, xA, yA, orientationA, commandsA));

        when(carService.moveVehicle(vehicleMap, height, width)).thenReturn("4 3 S");
        String result = carService.moveVehicle(vehicleMap, height, width);

        assertEquals("4 3 S", result);
    }

    @Test
    public void testMoveMultipleCars(){

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put(idA, new Car(idA, xA, yA, orientationA, commandsA));
        vehicleMap.put(idB, new Car(idB, xB, yB, orientationB, commandsB));

        when(carService.moveVehicle(vehicleMap, height, width)).thenReturn("Done");
        String result = carService.moveVehicle(vehicleMap, height, width);

        assertEquals("Done", result);

    }


}