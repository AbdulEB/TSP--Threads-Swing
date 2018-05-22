package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class is an object class to store the city number of from the text file and the city's x and y
 * coordinates.
 *
 * */

public class City {

    private int cityNum; // stores City's Number
    private double cityX; // stores the city X coordinates
    private double cityY; // stores the city Y coordinates

    public City( int cityNum, double cityX, double cityY) {

        this.cityNum = cityNum;//returns city number
        this.cityX = cityX;// returns city's x coordinate
        this.cityY = cityY;// returns city's y coordinate
    }

    //a get City number method
    public int getCityNum(){
        return cityNum;
    }
    //a get City X coordinate method
    public double getCityX() {
        return cityX;
    }
    //a get City Y coordinate method
    public double getCityY() {
        return cityY;
    }
}
