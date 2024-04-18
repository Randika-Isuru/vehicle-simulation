package com.example.simulation.service;

import com.example.simulation.aspect.InputValidationAspect;
import com.example.simulation.constants.UserInteractMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInteractionServiceImpl implements UserInteractionService {
    private final InputValidationAspect inputValidationAspect;

    public UserInteractionServiceImpl(InputValidationAspect inputValidationAspect){
        this.inputValidationAspect = inputValidationAspect;
    }
    @Override
    public Map<String, String[]> getUserInputs(String text, Scanner scanner) {
        Map<String, String[]> inputMap = new HashMap<>();
        try {
            System.out.println(text);
            String[] inputs = scanner.nextLine().split(" ");
            inputMap.put(text, inputs);
            return inputValidationAspect.interceptScannerNextLine(inputMap);
        } catch (Exception ex){
            System.out.println(UserInteractMessage.PLEASE_TRY_AGAIN_ERROR_MESSAGE);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return inputMap;
    }
}
