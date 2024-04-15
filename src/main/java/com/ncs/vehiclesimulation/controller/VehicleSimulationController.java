package com.ncs.vehiclesimulation.controller;

import com.ncs.vehiclesimulation.model.Direction;
import com.ncs.vehiclesimulation.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class VehicleSimulationController implements CommandLineRunner {

    private final CarService carService;

    public VehicleSimulationController(CarService carService){
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Read field dimensions
        String[] fieldDimensions = scanner.nextLine().split(" ");
        int width = Integer.parseInt(fieldDimensions[0]);
        int height = Integer.parseInt(fieldDimensions[1]);

        // Read initial car position and direction
        String[] initialPosition = scanner.nextLine().split(" ");
        int initialX = Integer.parseInt(initialPosition[0]);
        int initialY = Integer.parseInt(initialPosition[1]);
        Direction initialDirection = Direction.valueOf(initialPosition[2]);

        carService.setPosition(initialX, initialY, initialDirection);

        // Read and execute commands
        String commands = scanner.nextLine();
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

        // Output final position and direction
        System.out.println(carService.getX() + " " + carService.getY() + " " + carService.getDirection());
    }
}
