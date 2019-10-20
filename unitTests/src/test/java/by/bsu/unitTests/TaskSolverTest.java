package by.bsu.unitTests;


import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSolverTest {
    int[] t1;
    int[] t2;
    int[] t3;
    int[] t4;
    int[] t5;
    int[] t6;
    int[] t7;
    int[] t8;
    int[] t9;
    int[] t10;
    int[][] allTrueTr;
    int[][] allFalseTr;

    @BeforeEach
    public void initialization() {
        t1 = new int[]{5, 5, 5}; //true
        t2 = new int[]{5, 5, 7}; //true
        t3 = new int[]{5, 3, 4}; //true
        t4 = new int[]{3, 1, 1}; //false
        t5 = new int[]{5, 10, 7}; //true
        t6 = new int[]{2, 5, 7}; //false
        t7 = new int[]{20, 100, 30}; //false
        t8 = new int[]{10, 10, 10}; //true
        t9 = new int[]{5, 2, 1}; //false
        t10 = new int[]{5, 5, 10}; //false
        allTrueTr = new int[][]{t1, t2, t3, t5, t8};
        allFalseTr = new int[][]{t4, t6, t7, t9, t10};
    }

    @Test
    public void isTriangleTrue() {
        for(int i = 0; i < allTrueTr.length; i++) {
            assertTrue(TaskSolver.isTriangle(allTrueTr[i]));
        }
    }
    @Test
    public void isTriangleFalse() {
        for(int i = 0; i < allTrueTr.length; i++) {
            assertFalse(TaskSolver.isTriangle(allFalseTr[i]));
        }
    }
}
