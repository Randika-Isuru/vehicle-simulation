package com.example.simulation.service;

import com.example.simulation.constants.UserInteractMessage;
import com.example.simulation.model.BaseVehicle;
import com.example.simulation.model.Car;
import com.example.simulation.model.CarFactory;
import com.example.simulation.model.Direction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {
    private Car myCar;
    private final CarFactory carFactory;
    public CarServiceImpl(CarFactory carFactory) {
        this.carFactory = carFactory;
    }
    private final Map<Integer, Integer[]> positionsMap = new HashMap<>();
    private int index = 1;

    @Override
    public String moveVehicle(Map<String, BaseVehicle> vehicleMap, int height, int width) {
        for (BaseVehicle car : vehicleMap.values()) {
            myCar = carFactory.createCar(car.getId(), car.getX(), car.getY(), car.getDirection(), car.getCommands());
            for (char command : car.getCommands().toCharArray()) {
                executeCommand(height, width, command);
                index++;
            }
            if(vehicleMap.values().size() == 1){
                return getX() + " " + getY() + " " + getDirection();
            }
        }
        if(vehicleMap.values().size() > 1) {
            for (BaseVehicle car : vehicleMap.values()) {
                System.out.print(car.getId()  + " ");
            }
            Boolean foundEqualArrays =  checkEqualArrays(positionsMap);
            if(!foundEqualArrays){
                return UserInteractMessage.MULTIPLE_VEHICLE_MOVE_WITHOUT_COLLISION_SUCCESS_MESSAGE;
            } else {
                return foundEqualArrays.toString();
            }
        }
        return "Done";
    }
    private void executeCommand(int height, int width, char command) {
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
    }
    private Boolean checkEqualArrays(Map<Integer, Integer[]> positionsMap) {
        boolean foundEqualArrays = false;
        for (Map.Entry<Integer, Integer[]> entry1 : positionsMap.entrySet()) {
            Integer[] array1 = entry1.getValue();
            foundEqualArrays = positionsMap.entrySet().stream()
                    .filter(entry2 -> entry1.getKey() < entry2.getKey())
                    .anyMatch(entry2 -> arraysEqual(array1, entry2.getValue()));
            if (foundEqualArrays) {
                System.out.println();
                System.out.println(entry1.getValue()[0] + " " + entry1.getValue()[1]);
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
    private void moveForward() {
        if(myCar != null) {
            myCar.moveForward();
        }
    }
    private void rotateLeft() {
        if(myCar != null){
            myCar.rotateLeft();
        }
    }
    private void rotateRight() {
        if(myCar != null) {
            myCar.rotateRight();
        }
    }
    private int getX() {
        if(myCar != null) {
            return myCar.getX();
        }
        return 0;
    }
    private int getY() {
        if(myCar != null) {
            return myCar.getY();
        }
        return 0;
    }
    private Direction getDirection() {
        if(myCar != null) {
            return myCar.getDirection();
        }
        return Direction.N;
    }
}