package com.example.simulation.service;

import java.util.Map;
import java.util.Scanner;

public interface UserInteractionService {

    /**
     * Retrieves the user input form the console. This method will around with "InputValidationAspect".
     * This method will handle all the Exceptions which will throwing from the InputValidationAspect.
     *
     * @param text The message which display to user.
     * @param scanner To get the input from user.
     * @return a Map with String key and String array as value. key contain the displayed message to the user.
     * String array contains the user input which getting from the console using scanner.
     *
     */
    Map<String, String[]> getUserInputs(String text, Scanner scanner);
}
