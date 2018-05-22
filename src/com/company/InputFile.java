package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class reads in the text file from a passed in string (the file path location)
 * */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile {
    private Scanner fileInput;//scanner
    //constructor
    public InputFile(String fileName){

        fileInput = new Scanner(System.in);// used to load the text file
        try {
            fileInput = new Scanner(new FileInputStream(new File(fileName)));/* loads the text file that
            has the cities information*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //get method to return the text file
    public Scanner getFile(){
        return fileInput;
    }
}
