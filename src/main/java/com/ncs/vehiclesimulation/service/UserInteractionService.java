package com.ncs.vehiclesimulation.service;

import java.util.Map;
import java.util.Scanner;

public interface UserInteractionService {
    Map<String, String[]> getUserInputs(String text, Scanner scanner);
}
