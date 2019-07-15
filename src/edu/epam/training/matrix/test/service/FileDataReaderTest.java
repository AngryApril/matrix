package edu.epam.training.matrix.test.service;

import edu.epam.training.matrix.main.service.FileDataReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.testng.Assert.*;
/**
 * Created by alexey.valiev on 6/9/19.
 */
public class FileDataReaderTest {

    @Test
    @BeforeMethod
    public void testReadNonExistingFile(){

        Optional<ArrayList<String>> listOptional;
        Optional<ArrayList<String>> listOptional2 = Optional.empty();
        FileDataReader fileDataReader = new FileDataReader();

        listOptional = fileDataReader.readToStringArrayList("Non-Existing-File-LOL.txt");

        assertEquals(listOptional, listOptional2);
    }

}
