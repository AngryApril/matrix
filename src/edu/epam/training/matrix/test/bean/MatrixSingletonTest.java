package edu.epam.training.matrix.test.bean;

import edu.epam.training.matrix.main.bean.Matrix;
import edu.epam.training.matrix.main.bean.MatrixLine;
import edu.epam.training.matrix.main.service.MatrixThread;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public class MatrixSingletonTest {

    @Test
    @BeforeMethod
    public void testMatrixSingleton(){

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        MatrixLine matrixLine1 = new MatrixLine(0, list1);
        MatrixLine matrixLine2 = new MatrixLine(0, list2);
        ArrayList<MatrixLine> matrixLines1 = new ArrayList<>();
        ArrayList<MatrixLine> matrixLines2 = new ArrayList<>();
        matrixLines1.add(matrixLine1);
        matrixLines2.add(matrixLine2);

        list1.addAll(Arrays.asList(100, 200, 5673, 4));
        list2.addAll(Arrays.asList(-1, -2, -3, -4));

        Matrix matrix = Matrix.get();
        Matrix matrix1 = Matrix.get();

        matrix.setMatrixLines(matrixLines1);
        matrix1.setMatrixLines(matrixLines2);

        //prove that matrix1 equals matrix - so Matrix object was created only once
        assertEquals(matrix, matrix1);

    }
}
