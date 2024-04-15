package com.ncs.vehiclesimulation.controller;

import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Car;
import com.ncs.vehiclesimulation.model.Direction;
import com.ncs.vehiclesimulation.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class VehicleSimulationController implements CommandLineRunner {

    private final CarService carService;

    public VehicleSimulationController(CarService carService){
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Please enter your data below: (once enter all the details enter 'move' to drive your vehicle) ");
        Scanner scanner = new Scanner(System.in);

        // Read field dimensions
        System.out.print("Enter width and height of the field : ");
        String[] fieldDimensions = scanner.nextLine().split(" ");
        int width = Integer.parseInt(fieldDimensions[0]);
        int height = Integer.parseInt(fieldDimensions[1]);

        Map<String, BaseVehicle> vehicleMap = getVehicleInputs(scanner);

        if(!vehicleMap.isEmpty()){
            moveVehicle(vehicleMap, height, width);
        }


    }

    private void moveVehicle(Map<String, BaseVehicle> vehicleMap, int height, int width) {
        Map<Integer, Integer[]> positionsMap = new HashMap<>();
        int index = 1;
        for (BaseVehicle car : vehicleMap.values()) {
            carService.setPosition(car.getId(), car.getX(), car.getY(), car.getDirection(), car.getCommands());
            for (char command : car.getCommands().toCharArray()) {

                if (command == 'F') {
                    switch (carService.getDirection()) {
                        case N -> {
                            if (carService.getY() + 1 < height) {
                                carService.moveForward();
                                positionsMap.put(index, new Integer[]{carService.getX(), carService.getY()});
                            }
                        }
                        case E -> {
                            if (carService.getX() + 1 < width){
                                carService.moveForward();
                                positionsMap.put(index, new Integer[]{carService.getX(), carService.getY()});
                            }
                        }
                        case S -> {
                            if (carService.getY() - 1 >= 0){
                                carService.moveForward();
                                positionsMap.put(index, new Integer[]{carService.getX(), carService.getY()});
                            }
                        }
                        case W -> {
                            if (carService.getX() - 1 >= 0) {
                                carService.moveForward();
                                positionsMap.put(index, new Integer[]{carService.getX(), carService.getY()});
                            }
                        }
                    }
                } else if (command == 'L') {
                    carService.rotateLeft();
                } else if (command == 'R') {
                    carService.rotateRight();
                }

                index++;
            }

            // Output final position and direction
            if(vehicleMap.values().size() == 1){
                System.out.println(carService.getX() + " " + carService.getY() + " " + carService.getDirection());
            }

        }
        if(vehicleMap.values().size() > 1) {
            Boolean foundEqualArrays =  checkEqualArrays(positionsMap);
            if(!foundEqualArrays){
                System.out.println("no collision");
            }
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

    private Map<String, BaseVehicle> getVehicleInputs(Scanner scanner){

        Map<String, BaseVehicle> carData = new HashMap<>();
        System.out.print("Enter car Id : ");
        String carId = scanner.nextLine();

        do {

            // Read initial car position and direction
            System.out.print("Enter current position and facing direction of the car : ");
            String[] initialPosition = scanner.nextLine().split(" ");
            int initialX = Integer.parseInt(initialPosition[0]);
            int initialY = Integer.parseInt(initialPosition[1]);
            Direction initialDirection = Direction.valueOf(initialPosition[2]);

            // Read and execute commands
            System.out.print("Enter car move commands : ");
            String commands = scanner.nextLine();

            carData.put(carId, new Car(carId, initialX, initialY, initialDirection, commands));

            System.out.print("Enter car Id : ( or enter 'move' to drive your vehicle) ");
            carId = scanner.nextLine();

        } while (!"move".equalsIgnoreCase(carId));

        return carData;
    }
}
