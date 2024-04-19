package com.example.simulation.aspect;

import com.example.simulation.constants.UserInteractMessage;
import com.example.simulation.model.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents validation aspect in the application.
 * This will provide functionality for managing all the input validation.
 *
 * @author Randika Isuru Vijayanga
 * @version 1.0
 */
public class InputValidationAspect {

    /**
     * Retrieves the user input and validate those input according to several aspects.
     *
     * @param userInputs The Map with String key and String array as value. key contain the displayed message and values
     * contains the user inputs data.
     * @return a Map with String key and String array as value. key contain the displayed message or "False" if
     * the user input is invalid.
     * String array contains the user input which getting from the console using scanner.
     *
     */
    public Map<String, String[]> interceptScannerNextLine(Map<String, String[]> userInputs){
        Map<String, String[]> updatedResultMap = new HashMap<>();
        try {
            if (userInputs != null) {
                Map<String, String[]> resultMap = (Map<String, String[]>) userInputs;
                for (Entry<String, String[]> entry : resultMap.entrySet()) {
                    String key = entry.getKey();
                    String[] values = entry.getValue();
                    validateWithInputs(key, values, updatedResultMap);
                }
                return updatedResultMap;
            } else {
                System.out.println(UserInteractMessage.UNEXPECTED_RESULT_TYPE_ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(UserInteractMessage.INVALID_INPUT_ERROR_MESSAGE + iae.getMessage());
        } catch (Exception ex) {
            System.out.println(UserInteractMessage.INVALID_INPUT_ERROR_MESSAGE + ex.getMessage());
        }
        return userInputs;
    }
    private Map<String, String[]> validateWithInputs(String key, String[] values, Map<String, String[]> updatedResultMap) {
        boolean isValid;
        switch (key) {
            case UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE -> {
                isValid = isValidTwoIntegersInput(values);
                if(!isValid){
                    System.out.println(UserInteractMessage.GET_WIDTH_AND_HEIGHT_ERROR_MESSAGE);
                    updatedResultMap.put("False", values);
                    return updatedResultMap;
                }
            }
            case UserInteractMessage.GET_CAR_ID_MESSAGE -> {
                isValid = isValidSingleTextInput(values);
                if(!isValid){
                    System.out.println(UserInteractMessage.GET_CAR_ID_ERROR_MESSAGE);
                    updatedResultMap.put("False", values);
                    return updatedResultMap;
                }
            }
            case UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE -> {
                isValid = isValidTwoIntegersAndDirectionInput(values);
                if(!isValid){
                    System.out.println(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_ERROR_MESSAGE);
                    updatedResultMap.put("False", values);
                    return updatedResultMap;
                }
            }
            case UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE -> {
                isValid = isValidCommand(values);
                if(!isValid){
                    System.out.println(UserInteractMessage.GET_MOVE_COMMANDS_ERROR_MESSAGE);
                    updatedResultMap.put("False", values);
                    return updatedResultMap;
                }
            }
            case UserInteractMessage.GET_CAR_ID_OR_EXIT_MESSAGE -> {
                isValid = isValidSingleTextInput(values);
                if(!isValid){
                    System.out.println(UserInteractMessage.GET_CAR_ID_OR_EXIT_ERROR_MESSAGE);
                    updatedResultMap.put("False", values);
                    return updatedResultMap;
                }
            }
        }
        updatedResultMap.put(key, values);
        return updatedResultMap;
    }
    private boolean isValidSingleTextInput(String[] values) {
        return values.length == 1;
    }
    private boolean isValidTwoIntegersInput(String[] values) {
        if(values.length == 2){
            boolean allIntegers = true;
            boolean allPositiveIntegers = true;
            for (String s : values) {
                try {
                    int num = Integer.parseInt(s);
                    if (num < 0 || num > 100000) {
                        allPositiveIntegers = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    allIntegers = false;
                    break;
                }
            }

            if (allIntegers && allPositiveIntegers) {
                return true;
            } else if (!allIntegers) {
                return false;
            } else {
                return false;
            }
        }
        return false;
    }
    private boolean isValidTwoIntegersAndDirectionInput(String[] values) {
        boolean firstTwoPositiveIntegers = true;
        boolean thirdMatchesEnum = false;
        if(values.length == 3){
            for (int i = 0; i < 2; i++) {
                try {
                    int num = Integer.parseInt(values[i]);
                    if (num < 0 || num > 100000) {
                        firstTwoPositiveIntegers = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    firstTwoPositiveIntegers = false;
                    break;
                }
            }
            for (Direction value : Direction.values()) {
                if (value.name().equals(values[2])) {
                    thirdMatchesEnum = true;
                    break;
                }
            }
            if (firstTwoPositiveIntegers && thirdMatchesEnum) {
                return true;
            } else if (!firstTwoPositiveIntegers) {
                return false;
            } else {
                return false;
            }
        }
        return false;
    }
    private boolean isValidCommand(String[] values) {
        char[] charArray = {'F', 'L', 'R'};
        if(values.length == 1){
            boolean allContainChars = true;

            for (String s : values) {
                for (char c : s.toCharArray()) {
                    if (!contains(charArray, c)) {
                        allContainChars = false;
                        break;
                    }
                }
            }
            return allContainChars;
        }
        return false;
    }
    private boolean contains(char[] array, char c) {
        for (char item : array) {
            if (item == c) {
                return true;
            }
        }
        return false;
    }
}
