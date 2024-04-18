package com.example.simulation;

import com.example.simulation.aspect.InputValidationAspect;
import com.example.simulation.controller.VehicleSimulationController;
import com.example.simulation.model.CarFactory;
import com.example.simulation.service.CarService;
import com.example.simulation.service.CarServiceImpl;
import com.example.simulation.service.UserInteractionService;
import com.example.simulation.service.UserInteractionServiceImpl;

public class VehicleSimulationApplication {
	public static void main(String[] args) {
		CarFactory carFactory = new CarFactory();
		CarService carService = new CarServiceImpl(carFactory);
		InputValidationAspect inputValidationAspect = new InputValidationAspect();
		UserInteractionService userInteractionService = new UserInteractionServiceImpl(inputValidationAspect);
		VehicleSimulationController controller = new VehicleSimulationController(carService, userInteractionService);
		controller.runSimulation();
	}
}
