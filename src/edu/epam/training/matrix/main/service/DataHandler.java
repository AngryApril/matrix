package edu.epam.training.matrix.main.service;

import edu.epam.training.matrix.main.bean.Matrix;
import edu.epam.training.matrix.main.bean.MatrixLine;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by alexey.valiev on 6/7/19.
 */
public class DataHandler {

    private static final Logger logger = Logger.getLogger(DataHandler.class);

    private static final String delimeter = "\\s";

    private static final int defMatrixElement = 0;


    public ArrayList<MatrixLine> createMatrixLines(ArrayList<String> strings){

        ArrayList<MatrixLine> matrixLines = new ArrayList<>();
        ArrayList<Integer> matrixLine;

        for(int j = 0; j < strings.size(); j++){
            matrixLine = new ArrayList<>();
            String[] elements = strings.get(j).split(delimeter);
            int element;

            for(int i = 0; i < elements.length; i++){
                try{
                    element = Integer.parseInt(elements[i]);
                    matrixLine.add(element);
                }
                catch (NumberFormatException e){
                    logger.error(e.getMessage(), e);
                    matrixLine.add(defMatrixElement);
                }
            }
            matrixLines.add(new MatrixLine(j, matrixLine));
        }

        for(MatrixLine line : matrixLines){
            logger.info("\nMatrixLine: " + line.toString());
        }

        return matrixLines;
    }

    public ArrayList<Integer> createThreadsNumbers(ArrayList<String> strings){

        ArrayList<Integer> list = new ArrayList<>();
        int defaultNumber = 10;
        int number;

        for(int i = 0; i < strings.size(); i++){
            try{
                number = Integer.parseInt(strings.get(i));
                list.add(number);
            }
            catch (NumberFormatException e){
                logger.error(e.getMessage(), e);
                list.add(++defaultNumber);
            }
        }

        logger.info("\nThreads:\n" + list.toString());

        return list;
    }
}
