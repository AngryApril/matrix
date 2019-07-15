package edu.epam.training.matrix.main.service;

import edu.epam.training.matrix.main.bean.Matrix;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public class MatrixHandler {

    private static final Logger logger = Logger.getLogger(MatrixHandler.class);

    public void runThreads(ArrayList<MatrixThread> matrixThreads){

        ExecutorService executor = Executors.newFixedThreadPool(matrixThreads.size());

        for(MatrixThread matrixThread : matrixThreads){
            executor.submit(matrixThread);
        }

        try {
            TimeUnit.SECONDS.sleep(9);

        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        shutdownAndAwaitTermination(executor);
        executor.shutdownNow();

    }

    public ArrayList<MatrixThread> createThreads(ArrayList<Integer> threadNumbers, Matrix matrix){

        ArrayList<MatrixThread> matrixThreads = new ArrayList<>();

        for(int i = 0; i < threadNumbers.size(); i++){
            matrixThreads.add(new MatrixThread(threadNumbers.get(i), matrix));
        }

        return matrixThreads;
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {

        pool.shutdown(); // Disable new tasks from being submitted

        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
            }

        } catch (InterruptedException ie) {

            logger.error(ie.getMessage(), ie);
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
