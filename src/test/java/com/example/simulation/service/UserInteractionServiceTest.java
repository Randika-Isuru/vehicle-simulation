package com.example.simulation.service;

import com.example.simulation.constants.UserInteractMessage;
import com.example.simulation.model.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInteractionServiceTest {
    @InjectMocks
    UserInteractionServiceImpl userInteractionService;

    @Mock
    Scanner scanner;

    @Test
    public void testUserInputWidthAndHeight(){
        int width = 0;
        int height = 0;
        when(scanner.nextLine()).thenReturn("10 10");
        Map<String, String[]> inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, scanner);
        for (String[] values : inputs.values()){
            width = Integer.parseInt(values[0]);
            height = Integer.parseInt(values[1]);
        }
        assertEquals(10, width);
        assertEquals(10, height);
    }

    @Test
    public void testUserInputCarId(){
        String carId = "";
        when(scanner.nextLine()).thenReturn("A");
        Map<String, String[]> inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_CAR_ID_MESSAGE, scanner);
        for (String[] values : inputs.values()){
            carId = values[0];
        }
        assertEquals("A", carId);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection(){
        int positionX = 0;
        int positionY = 0;
        Direction direction = Direction.N;
        when(scanner.nextLine()).thenReturn("1 2 N");
        Map<String, String[]> inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, scanner);
        for (String[] values : inputs.values()){
            positionX = Integer.parseInt(values[0]);
            positionY = Integer.parseInt(values[1]);
            direction = Direction.valueOf(values[2]);
        }
        assertEquals(1, positionX);
        assertEquals(2, positionY);
        assertEquals(Direction.N, direction);
    }

    @Test
    public void testUserInputMoveCommand(){
        String command = "";
        when(scanner.nextLine()).thenReturn("FFRFFFRRLF");
        Map<String, String[]> inputs = userInteractionService.getUserInputs(UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE, scanner);
        for (String[] values : inputs.values()){
            command = values[0];
        }
        assertEquals("FFRFFFRRLF", command);
    }

}