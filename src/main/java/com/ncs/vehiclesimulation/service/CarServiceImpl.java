package com.ncs.vehiclesimulation.service;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Car;
import com.ncs.vehiclesimulation.model.Direction;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    private Car car;

    public CarServiceImpl(Car car) {
        this.car = car;
    }

    public String moveVehicle(Map<String, BaseVehicle> vehicleMap, int height, int width) {
        Map<Integer, Integer[]> positionsMap = new HashMap<>();
        int index = 1;
        for (BaseVehicle car : vehicleMap.values()) {
            setPosition(car.getId(), car.getX(), car.getY(), car.getDirection(), car.getCommands());
            for (char command : car.getCommands().toCharArray()) {

                if (command == 'F') {
                    switch (getDirection()) {
                        case N -> {
                            if (getY() + 1 < height) {
                                moveForward();
                                positionsMap.put(index, new Integer[]{getX(), getY()});
                            }
                        }
                        case E -> {
                            if (getX() + 1 < width){
                                moveForward();
                                positionsMap.put(index, new Integer[]{getX(), getY()});
                            }
                        }
                        case S -> {
                            if (getY() - 1 >= 0){
                                moveForward();
                                positionsMap.put(index, new Integer[]{getX(), getY()});
                            }
                        }
                        case W -> {
                            if (getX() - 1 >= 0) {
                                moveForward();
                                positionsMap.put(index, new Integer[]{getX(), getY()});
                            }
                        }
                    }
                } else if (command == 'L') {
                    rotateLeft();
                } else if (command == 'R') {
                    rotateRight();
                }

                index++;
            }

            // Output final position and direction
            if(vehicleMap.values().size() == 1){
                return getX() + " " + getY() + " " + getDirection();
            }

        }
        if(vehicleMap.values().size() > 1) {
            Boolean foundEqualArrays =  checkEqualArrays(positionsMap);
            if(!foundEqualArrays){
                return "no collision";
            } else {
                return foundEqualArrays.toString();
            }
        }

        return "Done";
    }

    private Boolean checkEqualArrays(Map<Integer, Integer[]> positionsMap) {
        boolean foundEqualArrays = false;
        for (Map.Entry<Integer, Integer[]> entry1 : positionsMap.entrySet()) {
            Integer[] array1 = entry1.getValue();
            foundEqualArrays = positionsMap.entrySet().stream()
                    .filter(entry2 -> entry1.getKey() < entry2.getKey())
                    .anyMatch(entry2 -> arraysEqual(array1, entry2.getValue()));
            if (foundEqualArrays) {
                System.out.println(Arrays.toString(entry1.getValue()));
                System.out.println(entry1.getKey());
                break;
            }
        }
        return foundEqualArrays;
    }

    private boolean arraysEqual(Integer[] arr1, Integer[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
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