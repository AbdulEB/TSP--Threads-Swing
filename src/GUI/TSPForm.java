package GUI;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class is the main class and creates the GUI and runs the program
 * */

import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class TSPForm {

    //the GUI components that will be used to construct the GUI
    private static JFrame frame;
    private static JPanel mapPanel;
    private static JLabel filePathLabel, currentBestLabel, numOfThreadsLabel, numOfSearchsLabel, numOfIterationsLabel;
    private static JTextField filePathText, numOfThreadsText, numOfSearchsText, numOfIterationsText;
    private static JButton searchButton;
    public static JTextField currentBestText;

    private static ThreadManager threadManager;//threadmanager to handle the threads

    //constructor
    public TSPForm(){
        frame = new JFrame();//initialize the frame

        executeDisplay();//methd to execute the display

        threadManager = new ThreadManager();// initialize the thread manager
    }

    //method to construct the components for the display
    private void executeDisplay(){
        filePathLabel = new JLabel("File Path");
        filePathLabel.setBounds(10, 10, 50, 20);
        filePathText = new JTextField();
        filePathText.setBounds(60, 10, 200, 20 );

        searchButton = new JButton("Search");
        searchButton.setBounds(270, 7, 100, 25);
        //on click method is to execute when the button is clicked
        searchButton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent event){
                searchButtonMouseOnClick(event);
            }
        });

        currentBestLabel = new JLabel("Current Best");
        currentBestLabel.setBounds(385, 10 , 100, 20);
        currentBestText = new JTextField();
        currentBestText.setBounds(485, 10, 100, 20);

        numOfThreadsLabel = new JLabel("Number of Threads");
        numOfThreadsLabel.setBounds(590, 10, 150, 20);
        numOfThreadsText = new JTextField();
        numOfThreadsText.setBounds(745, 10, 50, 20);

        numOfSearchsLabel = new JLabel("Number of Searches");
        numOfSearchsLabel.setBounds(200, 40, 150 ,20);
        numOfSearchsText = new JTextField();
        numOfSearchsText.setBounds(350, 40, 100, 20);

        numOfIterationsLabel = new JLabel("Number of Iterations");
        numOfIterationsLabel.setBounds(460, 40, 150, 20 );
        numOfIterationsText = new JTextField();
        numOfIterationsText.setBounds(615, 40, 100, 20);

        mapPanel = new DrawCanvas();
        mapPanel.setBounds(150,70,600,550);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mapPanel.setBackground(Color.white);

        frame.add(filePathLabel);
        frame.add(filePathText);
        frame.add(searchButton);
        frame.add(currentBestLabel);
        frame.add(currentBestText);
        frame.add(numOfThreadsLabel);
        frame.add(numOfThreadsText);
        frame.add(numOfSearchsLabel);
        frame.add(numOfSearchsText);
        frame.add(numOfIterationsLabel);
        frame.add(numOfIterationsText);
        frame.add(mapPanel);

        frame.setPreferredSize(new Dimension(900, 700));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle("TSP Solver");
        frame.setResizable(false);
        frame.setLocation(700, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.pack();
    }

    //search button on click method that runs the program
    public void searchButtonMouseOnClick(MouseEvent event){

        //get the file name from the file path text field and set it to the mapfile scanner
        String filePath = filePathText.getText();
        InputFile file = new InputFile(filePath);
        Scanner mapFile = file.getFile();

        //the scanner with the file will be pased on with an arraylist to map to fill in the arraylist and then
        // the arraylist will be passed in city distance matrix to fill in both the 2d city adjacency matrix
        ArrayList<City> map = new ArrayList<>();
        Map mapping = new Map(map, mapFile);
        int numVertices = mapping.getCityMap().size();
        CityDistanceMatrix cityMatrix = new CityDistanceMatrix(numVertices, mapping.getCityMap());

        //sends a copy of the city map arraylist to the DrawCanvas class
        ((DrawCanvas) mapPanel).cityLocations(mapping.getCityMap());

        //initialize the remaining the number of searches, iterations, and threads from the user inputs
        int numOfThreads = Integer.parseInt(numOfThreadsText.getText());
        int numOfSearches = Integer.parseInt(numOfSearchsText.getText());
        int numOfIterations = Integer.parseInt(numOfIterationsText.getText());

        //runs threads processes to find the fastest path
        for (int i = 0; i < numOfThreads; i++) {
            threadManager.startSearchThread(cityMatrix.getDistanceMatrix(),numVertices,numOfSearches,numOfIterations);
        }
    }
    //method to draw the path
    public synchronized static void drawPath(int[] path){
        ((DrawCanvas) mapPanel).travelPath(path);
        mapPanel.paint(mapPanel.getGraphics());
    }
    //run the thread and the program
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TSPForm();
            }
        });
    }


}
