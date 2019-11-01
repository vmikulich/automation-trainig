package by.bsu.unitTests;

import by.bsu.unitTests.exception.InvalidValueOfSideException;

public class TaskSolver {

    public static boolean isTriangle(double a, double b, double c) throws InvalidValueOfSideException {

        if (a <= 0 || b <= 0 || c <= 0) {
            throw new InvalidValueOfSideException("One or more sides are equal or less then zero");
        }

        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)) {
            throw new InvalidValueOfSideException("One or more sides are equal to NaN");
        }

        return a + b > c && a + c > b && b + c > a ? true : false;
    }
}

