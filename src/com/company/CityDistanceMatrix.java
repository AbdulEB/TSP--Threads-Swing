package com.company;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class calculate the distance between each city to every other city using the pythagorean theorem
 * to calculate the distance. Then it is stored in a 2d array
 * */
import java.util.ArrayList;

public class CityDistanceMatrix {

    private double [][]  distanceBetweenCities;//2d array to store the distance between cities

    // constructor that will pass the number of cities and the arraylist
    public CityDistanceMatrix(int numCity, ArrayList<City> map){
        distanceBetweenCities = fillDistanceMatrix(map,distanceBetweenCities, numCity);//fill the 2d array

    }
    //method to set the distance value for each cell
    public double[][] fillDistanceMatrix(ArrayList<City> map, double[][] cityDistances, int n){
        cityDistances = new double[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                cityDistances[i][j] = hypotenuse(map.get(i), map.get(j));
            }
        }
        return cityDistances;

    }
    //the hypotenuse method which will return the distance between two cities
    public double hypotenuse(City cityPointA, City cityPointB){
        double deltaX = cityPointA.getCityX()-cityPointB.getCityX();
        double deltaY = cityPointA.getCityY()-cityPointB.getCityY();
        return Math.sqrt((deltaX*deltaX)+(deltaY*deltaY)); // returns the distance between both cities
    }

    //a get method to get the city distance adjacency matrix
    public double [][] getDistanceMatrix(){
        return distanceBetweenCities;
    }


}
