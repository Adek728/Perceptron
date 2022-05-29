package com.perceptron;

import java.util.ArrayList;
import java.util.List;

public class Element {

    List<Double> coordinates;
    String name_of_object;

    public Element (String[] line){
        List<Double> coor_temp = new ArrayList<>();
        for (int i = 0; i < line.length-1; i++)
            coor_temp.add(Double.valueOf(line[i]));

        this.coordinates=coor_temp;
        this.name_of_object=line[line.length-1];
    }
}
