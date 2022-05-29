package com.perceptron;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    /*
        Wariant versicolor = 0
        Wariant virginica = 1
    */

    final double stala_uczenia ;
    final double akceptowalny_procent_bladow ;
    double prog_odchylenia = Math.random();
    List<Double> wagi = new ArrayList<>();
    int iloscWag;

    public Perceptron(int number_of_wagi , double stala_uczenia , double procent_bledow) {
        this.iloscWag = number_of_wagi;
        System.out.print("Wagi Perceptronu: ");
        for (int i = 0; i < number_of_wagi; i++) {
            wagi.add((Math.random()*2)*(Math.random()>0.5?-1:1));
            System.out.print(wagi.get(i) + " ");
        }
        this.stala_uczenia=stala_uczenia;
        this.akceptowalny_procent_bladow=procent_bledow;
    }

    public void uczenie(List<Double> tested , int szacowane , int prawdziwy ) {

            List<Double> nowa_waga = new ArrayList<>();

            for (int i = 0; i < tested.size(); i++)
                nowa_waga.add(wagi.get(i)+(stala_uczenia*(prawdziwy-szacowane))*tested.get(i));


            this.wagi = nowa_waga;
            this.prog_odchylenia= prog_odchylenia-stala_uczenia*(prawdziwy-szacowane);

    }

    public int szacowane(List<Double> tested){
        double net = 0.0;

        for (int i = 0; i < tested.size(); i++)
            net+=tested.get(i)*wagi.get(i);

        net-=prog_odchylenia;

        if (net >= 0)
            return 1;

        return 0;
    }




}
