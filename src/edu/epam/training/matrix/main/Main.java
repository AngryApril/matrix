package edu.epam.training.matrix.main;

import edu.epam.training.matrix.main.bean.*;

import edu.epam.training.matrix.main.service.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexey.valiev on 6/4/19.
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){

        Optional<ArrayList<String>> listOptional;
        ArrayList<String> validStrings = new ArrayList<>();
        ArrayList<String> validThreadNumbers = new ArrayList<>();
        ArrayList<Integer> threadNumbers = new ArrayList<>();
        ArrayList<MatrixLine> matrixLines = new ArrayList<>();
        ArrayList<MatrixThread> matrixThreads = new ArrayList<>();

        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();
        DataHandler dataHandler = new DataHandler();
        MatrixHandler matrixHandler = new MatrixHandler();

        listOptional = fileDataReader.readToStringArrayList("matrix14.txt");
        validStrings = dataValidator.validateMatrix(listOptional);

        listOptional = fileDataReader.readToStringArrayList("threads7.txt");
        validThreadNumbers = dataValidator.validateThreadNumbers(listOptional);
        logger.info("\nValid Threads:\n" + validThreadNumbers.toString() + "\n");

        matrixLines = dataHandler.createMatrixLines(validStrings);
        threadNumbers = dataHandler.createThreadsNumbers(validThreadNumbers);

        Matrix matrix = Matrix.get();
        matrix.setMatrixLines(matrixLines);
        logger.info("\n\nOriginal Matrix:\n" + matrix.toString());

        matrixThreads = matrixHandler.createThreads(threadNumbers, matrix);

        matrixHandler.runThreads(matrixThreads);

        logger.info("\n\nResulting Matrix:\n" + matrix.toString());

        while (!Thread.currentThread().isInterrupted()){
            Thread.currentThread().interrupt();
        }

    }
}
