package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class creates an array list of city objects, where each city will hold its city number,
 * x- & y-coordinates.
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    private ArrayList<City> cityMap;//to hold all the info for each city

    /* constructor that passes in the scanner to get the textfile and an arraylist to read the textfile information to
     * the arraylist
     */
    public Map(ArrayList<City> map, Scanner mapFile){
        this.cityMap=map;
        int n = mapFile.nextInt();// an integer n that will represent the total number of cities
        //places city info to the arraylist
        for (int i = 0; i < n; i++){
            cityMap.add(new City(mapFile.nextInt(), mapFile.nextDouble(), mapFile.nextDouble()));
        }
    }
    //a get method for the arraylist
    public ArrayList<City> getCityMap() {
        return cityMap;
    }

}
