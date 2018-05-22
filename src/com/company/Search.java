package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class will run the mutation, the total path distance, and the run method that will uses mutation and
 * total path distance in the thread implements runnable method
 * */

import GUI.TSPForm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Search implements Runnable{

    int [] path;// path of city to city in an array
    double [][] cityDistanceMatrix;//2d array matix that will call the matrix created in the CityDistanceMatrix class
    int numVertices,  numOfSearches, numOfIterations;//number of cities (vertices), and the number of searches and iterations the user inputed
    Random  rand;//random to be used for the seed

    //constructor to call the search and it passes a thread index, city distance matrix, number of cities, and
    // the users search and iteration input numbers
    public Search(int threadIndex, double [][] cityDistanceMatrix, int numVertices, int numOfSearches, int numOfIterations){
        this.cityDistanceMatrix=cityDistanceMatrix;
        this.numVertices = numVertices;
        this.numOfSearches = numOfSearches;
        this.numOfIterations = numOfIterations;
        rand = new Random(System.currentTimeMillis()*(1 + threadIndex)); //generalize a random number based from the seed & thread index
        path = new int[numVertices];
        for(int i = 0; i<numVertices; i++){//initialize the path in ascending order
            path[i] = i;
        }

    }
    //mutate method that passes in the path to switch the 3 indices in path with each other randomly
    private int[] mutatedPath(int [] path){
        int [] pathMutation = new int[numVertices];//the mutated path initialized
        for(int i=0;i<numVertices;i++){//making mutated path a copy of the passed in path
            pathMutation[i] = path[i];
        }

        int mutatedVertex1, mutatedVertex2, mutatedVertex3;// the three vertices that will be selected for the mutation

        mutatedVertex1 = (int)(rand.nextDouble()*numVertices);//using the seeded random variable to select the first mutated index
        mutatedVertex2 = (int)(rand.nextDouble()*numVertices);//using the seeded random variable to select the second mutated index
        while(mutatedVertex1 == mutatedVertex2){//in case first two random indices are equal, select another second mutated index
            mutatedVertex2 = (int)(rand.nextDouble()*numVertices);
        }
        mutatedVertex3 = (int)(rand.nextDouble()*numVertices);//using the seeded random variable to select the third mutated index
        //in case either the first two random indices are equal to the third index, select another third mutated index
        while(mutatedVertex1 == mutatedVertex3 || mutatedVertex2 == mutatedVertex3){
            mutatedVertex3 = (int)(rand.nextDouble()*numVertices);
        }

        ArrayList<Integer> mutatedList = new ArrayList<>();//place the selected mutated indices in an arraylist
        mutatedList.add(mutatedVertex1);
        mutatedList.add(mutatedVertex2);
        mutatedList.add(mutatedVertex3);
        java.util.Collections.shuffle(mutatedList);// shuffle the mutated indices

        //rearrange the selected indices in the for the mutation of the path
        pathMutation[mutatedList.get(0)] = path[mutatedVertex1];
        pathMutation[mutatedList.get(1)] = path[mutatedVertex2];
        pathMutation[mutatedList.get(2)] = path[mutatedVertex3];

        return pathMutation;// return path mutation

    }

    //method to calculate the total distance
    private double getPathTotalDistance(int[] path){
        double pathDistance = 0;//initialize the path distance
        //for-loop that uses the passed in path array as the indices values in the city distance matrix array that
        // needed to be added to the path distance
        for (int i = 1; i < path.length; i++) {
            pathDistance += cityDistanceMatrix[path[i - 1]][path[i]];
        }
        //adds the distance of the cities of the first and last index of the passed in array
        pathDistance += cityDistanceMatrix[path[path.length - 1]][path[0]];
        return pathDistance;//returns path distance
    }

    @Override//runnable method that will be used by the threads
    public void run() {
        int [] fastestPath = path;//initialize the fastest path
        //for loop to shuffle the path array, mutates it, and prints the path and it's shortest distance
        for (int i = 0; i<numOfSearches; i++){
            Shuffle shuffling = new Shuffle(path, rand);//new instance of shuffle
            int [] currentPath = shuffling.getShuffledPath();//get the shuffle array
            //for loop to get mutate the path and find a faster path
            for (int j =0; j<numOfIterations; j++){
                int [] mutatePath = mutatedPath(currentPath);
                //if mutate path is shorter than current path, current path will be equal to mutate path
                if(getPathTotalDistance(mutatePath) < getPathTotalDistance(currentPath)){
                    currentPath = mutatePath;
                }
            }
            //if current path is shorter than fastest path, fastest path will be equal to current path
            if (getPathTotalDistance(currentPath)<getPathTotalDistance(fastestPath)){
                fastestPath = currentPath;
            }
            //if current path is shorter than optimal path of the whole program, optimal path will be set to current path
            if (getPathTotalDistance(currentPath) < ThreadManager.optimalPath){
                ThreadManager.optimalPath = getPathTotalDistance(currentPath);
                TSPForm.currentBestText.setText(Double.toString(ThreadManager.optimalPath));//optimal pass will be displaced in the TSP Solver Frame
                TSPForm.drawPath(currentPath);//and the path will be drawn in the canvas
            }
        }

        System.out.println("end");//print that the thread ends

        for (int i = 0; i < fastestPath.length; i++) {//print the path
            System.out.print(fastestPath[i] + " ");
        }
        System.out.println("");
        System.out.println(getPathTotalDistance(fastestPath));//print the distance
        System.out.println("");



    }
}
