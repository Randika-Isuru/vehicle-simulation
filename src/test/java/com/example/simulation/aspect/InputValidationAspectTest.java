package com.example.simulation.aspect;

import com.example.simulation.constants.UserInteractMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputValidationAspectTest {
    @InjectMocks
    InputValidationAspect inputValidationAspect;

    private final String ASSERT_FALSE = "False";

    @Test
    public void testUserInputWidthAndHeight_ValidInput() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "10"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, key);
    }

    @Test
    public void testUserInputWidthAndHeight_HeightInvalidInputMinusValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "-10"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputWidthAndHeight_WidthInvalidInputMinusValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"-10", "10"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputWidthAndHeight_HeightInvalidInputMaxValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "100001"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputWidthAndHeight_WidthInvalidInputMaxValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"100001", "10"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputWidthAndHeight_HeightInvalidInputLetterValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "A"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputWidthAndHeight_WidthInvalidInputLetterValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"B", "10"};
        sourceMap.put(UserInteractMessage.GET_WIDTH_AND_HEIGHT_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCarId_ValidInput() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"A"};
        sourceMap.put(UserInteractMessage.GET_CAR_ID_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(UserInteractMessage.GET_CAR_ID_MESSAGE, key);
    }

    @Test
    public void testUserInputCarId_ValidInputTwoInputs() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"A", "B"};
        sourceMap.put(UserInteractMessage.GET_CAR_ID_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_ValidInput() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"1", "2", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_XPositionInvalidInputMinusValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"-10", "10", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_YPositionInvalidInputMinusValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "-10", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_XPositionInvalidInputMaxValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"-10", "10", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_YPositionInvalidInputMaxValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "-10", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_XPositionInvalidInputLetterValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"A", "10", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_YPositionInvalidInputLetterValue() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "B", "N"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputCurrentPositionAndFacingDirection_DirectionInvalidInputLetter() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"10", "B", "K"};
        sourceMap.put(UserInteractMessage.GET_CURRENT_POSITION_AND_FACING_DIRECTION_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }

    @Test
    public void testUserInputMoveCommand_ValidInput() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"FFRFFFRRLF"};
        sourceMap.put(UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE, key);
    }

    @Test
    public void testUserInputMoveCommand_InvalidInputLetter() {
        String key = null;
        Map<String, String[]> sourceMap = new HashMap<>();
        String[] sourceArray = {"FFRFFFRRLW"};
        sourceMap.put(UserInteractMessage.GET_MOVE_COMMANDS_MESSAGE, sourceArray);
        Map<String, String[]> resultMap = inputValidationAspect.interceptScannerNextLine(sourceMap);
        if (!resultMap.isEmpty()) {
            Map.Entry<String, String[]> entry = resultMap.entrySet().iterator().next();
            key = entry.getKey();
        }
        assertEquals(ASSERT_FALSE, key);
    }
}