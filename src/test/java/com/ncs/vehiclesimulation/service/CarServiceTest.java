package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Car;
import com.ncs.vehiclesimulation.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarServiceImpl carService;

    int[] field;
    int width;
    int height;

    @BeforeEach
    public void setup() {
        field = new int[]{10, 10};
        width = field[0];
        height = field[1];
    }

    @Test
    public void testMoveCar() {
        String id = "A";
        int[] position = {1, 2};
        Direction orientation = Direction.N;
        String commands = "FFRFFFRRLF";

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put(id, new Car(id, position[0], position[1], orientation, commands));

        String result = carService.moveVehicle(vehicleMap, height, width);
        assertEquals("4 3 S", result);
    }

    @Test
    public void testMoveCarOutOfTheBoundary() {
        String id = "B";
        int[] position = {1, 2};
        Direction orientation = Direction.N;
        String commands = "FFLFFFRRLF";

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put(id, new Car(id, position[0], position[1], orientation, commands));

        String result = carService.moveVehicle(vehicleMap, height, width);
        assertEquals("0 5 N", result);
    }

    @Test
    public void testMoveMultipleCars_withCollision() {
        String idA = "A";
        int[] positionA = {1, 2};
        Direction orientationA = Direction.N;
        String commandsA = "FFRFFFFRRL";

        String idB = "B";
        int[] positionB = {7, 8};
        Direction orientationB = Direction.W;
        String commandsB = "FFLFFFFFFF";

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put(idA, new Car(idA, positionA[0], positionA[1], orientationA, commandsA));
        vehicleMap.put(idB, new Car(idB, positionB[0], positionB[1], orientationB, commandsB));

        String result = carService.moveVehicle(vehicleMap, height, width);
        assertEquals("true", result);
    }

    @Test
    public void testMoveMultipleCars_withoutCollision() {
        String idA = "A";
        int[] positionA = {1, 2};
        Direction orientationA = Direction.N;
        String commandsA = "FFRFFFFRRL";

        String idB = "B";
        int[] positionB = {7, 8};
        Direction orientationB = Direction.W;
        String commandsB = "FFRFFFFFFF";

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put(idA, new Car(idA, positionA[0], positionA[1], orientationA, commandsA));
        vehicleMap.put(idB, new Car(idB, positionB[0], positionB[1], orientationB, commandsB));

        String result = carService.moveVehicle(vehicleMap, height, width);
        assertEquals("no collision", result);
    }

}