package by.bsu.unitTests;

public class TaskSolver {
    public static boolean isTriangle(int[] arr) {
        return arr[0] + arr[1] > arr[2] && arr[0] + arr[2] > arr[1] && arr[1] + arr[2] > arr[0] ? true : false;
    }
}

