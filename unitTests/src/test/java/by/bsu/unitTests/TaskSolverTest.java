package by.bsu.unitTests;

import by.bsu.unitTests.exception.InvalidValueOfSideException;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.rules.ExpectedException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;



//@RunWith(DataProviderRunner.class)
public class TaskSolverTest {

//    @BeforeEach
//    public void initialization() {
//        t1 = new int[]{5, 5, 5}; //true
//        t2 = new int[]{5, 5, 7}; //true
//        t3 = new int[]{5, 3, 4}; //true
//        t4 = new int[]{3, 1, 1}; //false
//        t5 = new int[]{5, 10, 7}; //true
//        t6 = new int[]{2, 5, 7}; //false
//        t7 = new int[]{20, 100, 30}; //false
//        t8 = new int[]{10, 10, 10}; //true
//        t9 = new int[]{5, 2, 1}; //false
//        t10 = new int[]{5, 5, 10}; //false
//        allTrueTr = new int[][]{t1, t2, t3, t5, t8};
//        allFalseTr = new int[][]{t4, t6, t7, t9, t10};
//    }



    @Test
    public void rightTriangleTest() throws InvalidValueOfSideException {
      assertTrue(TaskSolver.isTriangle(5, 4, 3));
    }

    @Test
    public void equilateralTriangleTest() throws InvalidValueOfSideException {
        assertTrue(TaskSolver.isTriangle(5, 5, 5));
    }

    @Test
    public void isoscelesTriangleTest() throws InvalidValueOfSideException {
        assertTrue(TaskSolver.isTriangle(5, 5, 7));
    }

    @Test
    public void arbitraryTriangleTest() throws InvalidValueOfSideException {
        assertTrue(TaskSolver.isTriangle(5, 10, 7));
    }

    @Test
    public void sideBiggerThanSumOfTwoAnotherTest() throws InvalidValueOfSideException {
        assertFalse(TaskSolver.isTriangle(3, 1, 1));
    }

    @Test
    public void sideEqualToTheSumOfTwoAnotherTest() throws InvalidValueOfSideException {
        assertFalse(TaskSolver.isTriangle(5, 5, 10));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void negativeSideTriangleTest() {
        InvalidValueOfSideException exception =
                                        assertThrows(InvalidValueOfSideException.class,
                                                () -> TaskSolver.isTriangle(5, 4 ,-5));
        assertEquals("One or more sides are equal or less then zero", exception.getMessage());
    }

    @Test
    public void zeroSideTriangleTest() {
        InvalidValueOfSideException exception =
                assertThrows(InvalidValueOfSideException.class,
                        () -> TaskSolver.isTriangle(5, 4 ,0));
        assertEquals("One or more sides are equal or less then zero", exception.getMessage());
    }

    @Test
    public void nanSideTriangleTest() {
        InvalidValueOfSideException exception =
                assertThrows(InvalidValueOfSideException.class,
                        () -> TaskSolver.isTriangle(Double.NaN, 4 ,5));
        assertEquals("One or more sides are equal to NaN", exception.getMessage());
    }


//    @DataProvider
//    public static Object[] data() {
//        return new Object[][] {
//                { new int[]{5, 5, 5}, true },
//                { new int[]{5, 5, 5}, true },
//        };
//    }
//
//    @Test
//    @UseDataProvider("data")
//    public void testIsTriangle(final int[] input, final boolean expected) {
//        assertEquals(expected, TaskSolver.isTriangle(input));
//    }
}
