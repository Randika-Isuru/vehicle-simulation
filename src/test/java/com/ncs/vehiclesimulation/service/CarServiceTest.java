package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarServiceImpl carService;

    @Test
    public void testMoveCar() {
        String id = "A";
        int[] field = {10, 10};
        int width = field[0];
        int height = field[1];
        int[] position = {1, 2};
        Direction orientation = Direction.N;
        String commands = "FFRFFFRRLF";

        carService.setPosition(id,position[0], position[1], orientation, commands);

        for (char command : commands.toCharArray()) {

            if (command == 'F') {
                switch (carService.getDirection()) {
                    case N -> {
                        if (carService.getY() + 1 < height) carService.moveForward();
                    }
                    case E -> {
                        if (carService.getX() + 1 < width) carService.moveForward();
                    }
                    case S -> {
                        if (carService.getY() - 1 >= 0) carService.moveForward();
                    }
                    case W -> {
                        if (carService.getX() - 1 >= 0) carService.moveForward();
                    }
                }
            } else if (command == 'L') {
                carService.rotateLeft();;
            } else if (command == 'R') {
                carService.rotateRight();
            }
        }
        assertEquals("4 3 S", carService.getX() + " " + carService.getY() + " " + carService.getDirection());
    }

    @Test
    public void testMoveCarOutOfTheBoundary() {
        String id = "B";
        int[] field = {10, 10};
        int width = field[0];
        int height = field[1];
        int[] position = {1, 2};
        Direction orientation = Direction.N;
        String commands = "FFLFFFRRLF";

        carService.setPosition(id, position[0], position[1], orientation, commands);

        for (char command : commands.toCharArray()) {

            if (command == 'F') {
                switch (carService.getDirection()) {
                    case N -> {
                        if (carService.getY() + 1 < height) carService.moveForward();
                    }
                    case E -> {
                        if (carService.getX() + 1 < width) carService.moveForward();
                    }
                    case S -> {
                        if (carService.getY() - 1 >= 0) carService.moveForward();
                    }
                    case W -> {
                        if (carService.getX() - 1 >= 0) carService.moveForward();
                    }
                }
            } else if (command == 'L') {
                carService.rotateLeft();;
            } else if (command == 'R') {
                carService.rotateRight();
            }
        }
        assertEquals("0 5 N", carService.getX() + " " + carService.getY() + " " + carService.getDirection());
    }

    @Test
    public void testMoveMultipleCars() {
        String id = "C";
        int[] field = {10, 10};
        int width = field[0];
        int height = field[1];
        int[] position = {1, 2};
        Direction orientation = Direction.N;
        String commands = "FFLFFFRRLF";

        carService.setPosition(id, position[0], position[1], orientation, commands);

        for (char command : commands.toCharArray()) {

            if (command == 'F') {
                switch (carService.getDirection()) {
                    case N -> {
                        if (carService.getY() + 1 < height) carService.moveForward();
                    }
                    case E -> {
                        if (carService.getX() + 1 < width) carService.moveForward();
                    }
                    case S -> {
                        if (carService.getY() - 1 >= 0) carService.moveForward();
                    }
                    case W -> {
                        if (carService.getX() - 1 >= 0) carService.moveForward();
                    }
                }
            } else if (command == 'L') {
                carService.rotateLeft();;
            } else if (command == 'R') {
                carService.rotateRight();
            }
        }
        assertEquals("0 5 N", carService.getX() + " " + carService.getY() + " " + carService.getDirection());
    }

}