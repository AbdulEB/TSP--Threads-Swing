package GUI;

/* Author: Abdul El Badaoui
 * Student Number: 5745716
 * Description: This class will represent the canvas of the GUI display that will illustrate the map
 * */

import com.company.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawCanvas extends JPanel {
    private ArrayList<City> map;//map arraylist
    private int [] path;//the path

    //constructor
    public DrawCanvas(){

    }
    //method that make the class city arraylist a copy of the passed in arraylist
    public void cityLocations(ArrayList<City> map){
        this.map = map;
    }
    //method that make the class path array a copy of the passed in array
    public void travelPath( int [] path){
        this.path = path;
    }
    //method to paint the canvas
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK );

        //if array is not null will use the map arraylist and the path to get two city coordinates to draw a line to
        // from one city to another and back to the original city
        if(path != null){
            for (int i = 1; i < path.length; i++) {
                City cityA = map.get(path[i-1]);
                City cityB = map.get(path[i]);
                g.drawLine((int)cityA.getCityX()/3, (int)cityA.getCityY()/3,
                        (int)cityB.getCityX()/3, (int) cityB.getCityY()/3);
            }

            City cityA = map.get(path[path.length-1]);
            City cityB = map.get(path[0]);
            g.drawLine((int)cityA.getCityX()/3, (int)cityA.getCityY()/3,
                    (int)cityB.getCityX()/3, (int) cityB.getCityY()/3);

        }


    }

}
