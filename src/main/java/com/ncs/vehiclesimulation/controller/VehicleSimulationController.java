package com.ncs.vehiclesimulation.controller;

import com.ncs.vehiclesimulation.constants.UserInteractMessage;
import com.ncs.vehiclesimulation.model.BaseVehicle;
import com.ncs.vehiclesimulation.model.Car;
import com.ncs.vehiclesimulation.model.Direction;
import com.ncs.vehiclesimulation.service.CarService;
import com.ncs.vehiclesimulation.service.UserInteractionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class VehicleSimulationController implements CommandLineRunner {
    private final CarService carService;
    private final UserInteractionService userInteractionService;

    private Integer width = 0;
    private Integer height = 0;
    private String  carId = "";

    private Map<String, String[]> inputs = new HashMap<>();

    public VehicleSimulationController(CarService carService, UserInteractionService userInteractionService){
        this.carService = carService;
        this.userInteractionService = userInteractionService;
    }
    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println(UserInteractMessage.HEADER_MESSAGE);
            Scanner scanner = new Scanner(System.in);
            inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, scanner);

            for (String[] values : inputs.values()){
                width = Integer.parseInt(values[0]);
                height = Integer.parseInt(values[1]);
            }

            Map<String, BaseVehicle> vehicleMap = getVehicleInputs(scanner);

            if(!vehicleMap.isEmpty()){
                String result = carService.moveVehicle(vehicleMap, height, width);
                if(!"Done".equals(result) && !"true".equalsIgnoreCase(result)){
                    System.out.println(result);
                }
            }
        } catch (Exception ex){
            System.out.println(UserInteractMessage.PLEASE_TRY_AGAIN_ERROR_MESSAGE);
        }

    }

    private Map<String, BaseVehicle> getVehicleInputs(Scanner scanner){

        Map<String, BaseVehicle> carData = new HashMap<>();

        try {
            inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_CAR_ID_MESSAGE, scanner);
            for (String[] values : inputs.values()){
                carId = values[0];
            }

            do {
                inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, scanner);

                int initialX = 0;
                int initialY = 0;
                Direction initialDirection = Direction.N;

                for (String[] values : inputs.values()){
                    initialX = Integer.parseInt(values[0]);
                    initialY = Integer.parseInt(values[1]);
                    initialDirection = Direction.valueOf(values[2]);
                }

                inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE, scanner);
                String commands = "";
                for (String[] values : inputs.values()){
                    commands = values[0];
                }

                carData.put(carId, new Car(carId, initialX, initialY, initialDirection, commands));

                inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_CAR_ID_OR_EXIT_MESSAGE, scanner);
                for (String[] values : inputs.values()){
                    carId = values[0];
                }

            } while (!"move".equalsIgnoreCase(carId));
        } catch (Exception ex){
            System.out.println(UserInteractMessage.PLEASE_TRY_AGAIN_ERROR_MESSAGE);
        }

        return carData;
    }
}
