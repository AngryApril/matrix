package edu.epam.training.matrix.main.service;

import edu.epam.training.matrix.main.bean.Matrix;
import edu.epam.training.matrix.main.bean.MatrixLine;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public class MatrixThread extends Thread {

    private static final Logger logger = Logger.getLogger(MatrixThread.class);

    private int value;
    private Matrix matrix;

    public MatrixThread(int value, Matrix matrix) {
        this.value = value;
        this.matrix = matrix;
    }

    @Override
    public void run() {

        for(int i = 0; i < matrix.get().getMatrixLines().size(); i++){

            MatrixLine matrixLine = Matrix.get().getMatrixLines().get(i);

            if(matrixLine.getState().equals(MatrixLine.State.UNCHANGED)) {
                matrixLine.set(i, value);
            }
        }
    }
}
