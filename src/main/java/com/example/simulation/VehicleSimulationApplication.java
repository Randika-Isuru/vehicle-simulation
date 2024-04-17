package com.example.simulation;

import com.example.simulation.config.AppConfig;
import com.example.simulation.controller.VehicleSimulationController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class VehicleSimulationApplication {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		VehicleSimulationController controller = context.getBean(VehicleSimulationController.class);
		controller.runSimulation();
	}

}
