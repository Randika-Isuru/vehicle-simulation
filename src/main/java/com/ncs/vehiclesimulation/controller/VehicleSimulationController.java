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
            String result = carService.moveVehicle(vehicleMap, height, width);
            System.out.println(result);
        }


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
