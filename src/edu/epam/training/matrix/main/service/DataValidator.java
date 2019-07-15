package edu.epam.training.matrix.main.service;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by alexey.valiev on 6/6/19.
 */
public class DataValidator {

    private static final Logger logger = Logger.getLogger(DataValidator.class);

    private static final String delimeter = "\\s";

    private int defaultNumber = 10;


    public ArrayList<String> validateMatrix(Optional<ArrayList<String>> optStrings){

        ArrayList<String> validStrings = new ArrayList<>();

        if(!optStrings.isPresent()){
            //if the file was empty or didn't exist
            addEmptyStrings(8, 8, "0");
            return validStrings;
        }
        int numberOfStrings = optStrings.get().size();
        int elementsExpected = numberOfStrings;

        if(numberOfStrings < 8 ) {
            elementsExpected = 8;
        } else if(numberOfStrings > 12) {
            elementsExpected = 12;
        }

        for(int i = 0; i < numberOfStrings && i < 12; i++){
            Optional<String> optString = validateString(i, optStrings.get().get(i), elementsExpected);
            if(optString.isPresent()){
                validStrings.add(optString.get());
            }
        }

        return validStrings;

    }


    public ArrayList<String> validateThreadNumbers(Optional<ArrayList<String>> optStrings){

        ArrayList<String> validThreads = new ArrayList<>();
        int posNumber;
        int defNumber = defaultNumber;

        if(!optStrings.isPresent()){

            validThreads = addThreadNumbers(4, defaultNumber);
            return validThreads;
        }

        for(int i = 0; i < optStrings.get().size() && i < 6; i++){

            defNumber++;

            if(optStrings.get().get(i).length() < 1) continue;

            try{
                String value = optStrings.get().get(i).split(delimeter)[0];
                posNumber = Integer.valueOf(value);
            }catch (NumberFormatException e){
                logger.error(e.getMessage(), e);
                posNumber = defNumber;
            }
            if(posNumber <= 0 ){
                posNumber = defNumber;
            }

            validThreads.add(String.valueOf(posNumber));
        }

        while(validThreads.size() < 4){
            validThreads.add(String.valueOf(++defNumber));
        }

        return validThreads;
    }

    private Optional<String> validateString(int lineNumber, String string, int elementsExpected){

        StringBuilder stringBuilder = new StringBuilder();
        String[] elements = string.split(delimeter);
        int element;
        int i;

        for(i = 0; i < elements.length && i < elementsExpected; i++){
            try{
                element = Integer.parseInt(elements[i]);
                if(i != lineNumber){
                    stringBuilder.append(elements[i]);
                }else{
                    stringBuilder.append("0");
                }
            }catch (NumberFormatException e){
                logger.error(e.getMessage(), e);
                stringBuilder.append("0");
            }
            if(i < elementsExpected - 1){
                stringBuilder.append(" ");
            }
        }
        if (elements.length < elementsExpected){
            for(i = elements.length; i < elementsExpected; i++){
                stringBuilder.append("0");
                if(i < elementsExpected - 1){
                    stringBuilder.append(" ");
                }
            }
        }

        return Optional.of(stringBuilder.toString());
    }


    private ArrayList<String> addEmptyStrings(int numberOfStrings, int numberOfElements, String element){

        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < numberOfStrings; i++){
            StringBuilder stringBuilder = new StringBuilder();

            for(int j = 0; j < numberOfElements; j++){

                stringBuilder.append(element);
                if(j < numberOfElements - 1){
                    stringBuilder.append(" ");
                }
            }
            list.add(stringBuilder.toString());

        }

        return list;
    }

    private ArrayList<String> addThreadNumbers(int numberOfStrings, int element){

        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < numberOfStrings; i++){

            StringBuilder stringBuilder = new StringBuilder();
            element++;
            stringBuilder.append(String.valueOf(element));
            list.add(stringBuilder.toString());
        }

        return list;
    }
}
