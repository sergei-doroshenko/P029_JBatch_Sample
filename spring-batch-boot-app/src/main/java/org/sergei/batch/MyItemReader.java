package org.sergei.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Sergei_Doroshenko on 11/10/2016.
 */
public class MyItemReader implements ItemReader<String> {
    private BufferedReader lineReader;

    public MyItemReader(String fileName) throws FileNotFoundException {
        this.lineReader = new BufferedReader(new FileReader(new File(fileName)));;
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return lineReader.readLine();
    }
}
