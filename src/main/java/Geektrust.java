/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import controller.InputParser;

/**
 *
 * @author Rushabh
 */
public class Geektrust {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<String> inputStream = new ArrayList<>();

        try {
            String filePath = args[0];
            File file = getFile(filePath);
            inputStream = getInputStreamFromFile(file);
        }
        catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.out.println("Please Enter input file path");
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Please enter valid path");
        }
        catch (IOException ioException) {
            System.out.println("Unable to read the file");
        }

        InputParser inputParser = new InputParser(inputStream);
        inputParser.commandParser();
        
    }

    private static File getFile(String filePath) throws FileNotFoundException {
        return new File(filePath);
    }

    private static ArrayList<String> getInputStreamFromFile(File file) throws IOException {
        ArrayList<String> inputStream = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            inputStream.add(st);
        }
        return inputStream;
    }
    
}
