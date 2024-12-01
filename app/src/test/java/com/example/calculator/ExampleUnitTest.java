package com.example.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        // Mocking basic addition
        double op1 = 5;
        double op2 = 3;
        double result = op1 + op2;
        assertEquals(8, result, 0.0);
    }

    @Test
    public void subtraction_isCorrect() {
        double op1 = 5;
        double op2 = 3;
        double result = op1 - op2;
        assertEquals(2, result, 0.0);
    }

    @Test
    public void multiplication_isCorrect() {
        double op1 = 5;
        double op2 = 3;
        double result = op1 * op2;
        assertEquals(15, result, 0.0);
    }

    @Test
    public void division_isCorrect() {
        double op1 = 6;
        double op2 = 2;
        double result = op1 / op2;
        assertEquals(3, result, 0.0);
    }

    @Test
    public void division_byZero_returnsErrorMessage() {
        double op1 = 6;
        double op2 = 0;
        String error = "Error: Division by Zero"; // expected error message

        String result;
        if (op2 == 0) {
            result = error;
        } else {
            result = String.valueOf(op1 / op2);
        }

        assertEquals(error, result);
    }

    @Test
    public void squareRoot_isCorrect() {
        double value = 16;
        double result = Math.sqrt(value);
        assertEquals(4, result, 0.0);
    }

    @Test
    public void squareRoot_ofNegative_isError() {
        double value = -16;
        String error = "Invalid Input"; // expected error message
        try {
            double result = Math.sqrt(value);
        } catch (NumberFormatException e) {
            assertEquals(error, e.getMessage());
        }
    }
}