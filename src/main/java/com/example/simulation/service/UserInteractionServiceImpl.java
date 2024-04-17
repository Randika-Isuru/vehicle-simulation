package com.example.simulation.service;

import com.example.simulation.constants.UserInteractMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class UserInteractionServiceImpl implements UserInteractionService {
    @Override
    public Map<String, String[]> getUserInputs(String text, Scanner scanner) {
        Map<String, String[]> inputMap = new HashMap<>();
        try {
            System.out.println(text);
            String[] inputs = scanner.nextLine().split(" ");
            inputMap.put(text, inputs);
        } catch (Exception ex){
            System.out.println(UserInteractMessage.PLEASE_TRY_AGAIN_ERROR_MESSAGE);
        }

        return inputMap;
    }
}
