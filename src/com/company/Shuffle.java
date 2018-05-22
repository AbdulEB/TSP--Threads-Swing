package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class will take in a path array and a Random parameter that is already seeded with it's respective
 * thread and shuffle the array the path array to be used to get the fastest path
 * */

import java.util.Random;

public class Shuffle {
    private int [] path;//path
    Random rand;// the random parameter

    //constructor
    public Shuffle(int[] path, Random rand){
        this.path = path;//make path array a copy of the passed in path
        this.rand = rand;//make random parameter a copy of the passed in random
        shuffling();//run the shuffling method
    }
    //shuffle method that shuffle the array using the fisher-yates shuffle
    public void shuffling(){
        for (int i = 0; i< path.length; i++){
            int swapVertex = (int)(rand.nextDouble()*i);
            int temp = path[swapVertex];
            path[swapVertex] = path[i];
            path[i] = temp;
        }
    }

    //get method to return the shuffled array
    public int [] getShuffledPath(){
        return path;
    }
}
