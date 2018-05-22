package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class will manage the threads
 * */

public class ThreadManager {

    private static int numOfThreads;//number of threads

    public static double optimalPath;//the fastest (shortest) path of the program
    //constructor
    public ThreadManager(){
        this.optimalPath = Double.MAX_VALUE;//initialize the optimal path to max
        numOfThreads = 0;//initialize the number of threads
    }
    //method to run the threads that passes in cityMatrix, number of cities, and the users searches and iteration input values
    public void startSearchThread(double[][] cityDistanceMatrix, int numVertices, int numOfSearches,  int numOfIterations){
        //create a thread that runs an instance of search
        Thread search = new Thread(new Search(numOfThreads++, cityDistanceMatrix, numVertices, numOfSearches, numOfIterations));
        search.start();//start thread
    }

}
