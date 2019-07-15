package edu.epam.training.matrix.test.service;

import edu.epam.training.matrix.main.service.DataValidator;
import edu.epam.training.matrix.main.service.FileDataReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.testng.Assert.*;

/**
 * Created by alexey.valiev on 6/7/19.
 */
public class DataValidatorTest {

    @Test
    @BeforeMethod
    public void testMatrixWithMoreColumns(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validStrings = new ArrayList<>();
        ArrayList<String> validStrings2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("columns.txt");
        listOptional2 = fileDataReader.readToStringArrayList("matrix10.txt");

        validStrings = dataValidator.validateMatrix(listOptional);
        validStrings2 = dataValidator.validateMatrix(listOptional2);

        assertEquals(validStrings, validStrings2);

    }

    @Test
    @BeforeMethod
    public void testMatrixWithMoreLines(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validStrings = new ArrayList<>();
        ArrayList<String> validStrings2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("lines.txt");
        listOptional2 = fileDataReader.readToStringArrayList("matrix12.txt");

        validStrings = dataValidator.validateMatrix(listOptional);
        validStrings2 = dataValidator.validateMatrix(listOptional2);

        assertEquals(validStrings, validStrings2);

    }

    @Test
    @BeforeMethod
    public void testMatrixNonExistOrEmptyFiles(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validStrings = new ArrayList<>();
        ArrayList<String> validStrings2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("empty.txt");
        listOptional2 = fileDataReader.readToStringArrayList("ue53s48di6rxyd7.txt");

        validStrings = dataValidator.validateMatrix(listOptional);
        validStrings2 = dataValidator.validateMatrix(listOptional2);

        assertEquals(validStrings, validStrings2);

    }

    @Test
    @BeforeMethod
    public void testMatrixFewerLinesOrColumns(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validStrings = new ArrayList<>();
        ArrayList<String> validStrings2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("matrix6.txt");
        listOptional2 = fileDataReader.readToStringArrayList("6zeros.txt");

        validStrings = dataValidator.validateMatrix(listOptional);
        validStrings2 = dataValidator.validateMatrix(listOptional2);

        assertEquals(validStrings, validStrings2);
    }

    @Test
    @BeforeMethod
    public void testThreadsNonExistOrEmptyFiles(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validThreadNumbers = new ArrayList<>();
        ArrayList<String> validThreadNumbers2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("empty.txt");
        listOptional2 = fileDataReader.readToStringArrayList("FakeXXYYYZZZZ.txt");

        validThreadNumbers = dataValidator.validateThreadNumbers(listOptional);
        validThreadNumbers2 = dataValidator.validateThreadNumbers(listOptional2);

        assertEquals(validThreadNumbers, validThreadNumbers2);

    }

    @Test
    @BeforeMethod
    public void testThreadsLessThen4(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validThreadNumbers = new ArrayList<>();
        ArrayList<String> validThreadNumbers2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("empty.txt");
        listOptional2 = fileDataReader.readToStringArrayList("threads3.txt");

        validThreadNumbers = dataValidator.validateThreadNumbers(listOptional);
        validThreadNumbers2 = dataValidator.validateThreadNumbers(listOptional2);

        assertEquals(validThreadNumbers, validThreadNumbers2);

    }

    @Test
    @BeforeMethod
    public void testThreadsMoreThan6(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2;
        ArrayList<String> validThreadNumbers = new ArrayList<>();
        ArrayList<String> validThreadNumbers2 = new ArrayList<>();
        FileDataReader fileDataReader = new FileDataReader();
        DataValidator dataValidator = new DataValidator();

        listOptional = fileDataReader.readToStringArrayList("threads6.txt");
        listOptional2 = fileDataReader.readToStringArrayList("threads7.txt");

        validThreadNumbers = dataValidator.validateThreadNumbers(listOptional);
        validThreadNumbers2 = dataValidator.validateThreadNumbers(listOptional2);

        assertEquals(validThreadNumbers, validThreadNumbers2);

    }

}
