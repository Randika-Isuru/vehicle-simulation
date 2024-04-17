package com.ncs.vehiclesimulation.aspect;

import com.ncs.vehiclesimulation.model.Direction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.ncs.vehiclesimulation.constants.UserInteractMessage;

import java.util.Map;
import java.util.Map.Entry;

@Aspect
@Component
public class InputValidationAspect {

    @Pointcut("execution(* com.ncs.vehiclesimulation.service.UserInteractionServiceImpl.getUserInputs(..))")
    public void getInputs() {}

    @Around("getInputs()")
    public Object interceptScannerNextLine(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();
        try {
            if (result instanceof Map) {
                Map<String, String[]> resultMap = (Map<String, String[]>) result;
                for (Entry<String, String[]> entry : resultMap.entrySet()) {
                    String key = entry.getKey();
                    String[] values = entry.getValue();
                    validateWithInputs(key, values);
                }
            } else {
                System.out.println(UserInteractMessage.UNEXPECTED_RESULT_TYPE_ERROR_MESSAGE + result.getClass());
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(UserInteractMessage.INVALID_INPUT_ERROR_MESSAGE + iae.getMessage());
            return iae;
        } catch (Exception ex) {
            System.out.println(UserInteractMessage.INVALID_INPUT_ERROR_MESSAGE + ex.getMessage());
            return ex;
        }

        return result;
    }

    private void validateWithInputs(String key, String[] values) {
        boolean isValid;
        switch (key) {
            case UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE -> {
                isValid = isValidTwoIntegersInput(values);
                if(!isValid){
                    throw new IllegalArgumentException(UserInteractMessage.GET_WIDTH_AND_HEIGHT_ERROR_MESSAGE);
                }
            }
            case UserInteractMessage.GET_CAR_ID_MESSAGE -> {
                isValid = isValidSingleTextInput(values);
                if(!isValid){
                    throw new IllegalArgumentException(UserInteractMessage.GET_CAR_ID_ERROR_MESSAGE);
                }
            }
            case UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE -> {
                isValid = isValidTwoIntegersAndDirectionInput(values);
                if(!isValid){
                    throw new IllegalArgumentException(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_ERROR_MESSAGE);
                }
            }
            case UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE -> {
                isValid = isValidCommand(values);
                if(!isValid){
                    throw new IllegalArgumentException(UserInteractMessage.GET_MOVE_COMMANDS_ERROR_MESSAGE);
                }
            }
            case UserInteractMessage.GET_CAR_ID_OR_EXIT_MESSAGE -> {
                isValid = isValidSingleTextInput(values);
                if(!isValid){
                    throw new IllegalArgumentException(UserInteractMessage.GET_CAR_ID_OR_EXIT_ERROR_MESSAGE);
                }
            }
        }
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
                    if (num < 0) {
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
                    if (num < 0) {
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
