package edu.epam.training.matrix.main.service;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public class FileDataReader {

    private String line;
    private ArrayList<String> arrayList;
    private static final Logger logger = Logger.getLogger(FileDataReader.class);
    private static final String filePath = "input/"; //path to input folder

    public Optional<ArrayList<String>> readToStringArrayList(String filename) {

        arrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath+filename))) {

            while ((line = reader.readLine()) != null) {

                if(line.length() > 0){
                    arrayList.add(line);
                }
            }

        } catch (FileNotFoundException fnfe){
            logger.error(fnfe.getMessage(),fnfe);
            return Optional.empty();
        }
        catch (IOException e) {
            logger.error(e.getMessage(),e);
            return Optional.empty();
        }

        return Optional.of(arrayList);
    }
}
