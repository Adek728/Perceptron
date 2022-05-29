package com.perceptron;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final int learn_loop_limit = 10000;
        boolean loop_bool = true;
        int loop_iterations=0;

        final double stala_uczenia = 0.01;
        final double akceptowalny_procent_bladow = 0.25 ;
        List<Element> zbior_treningowy = readAndCreate("D:\\STUDIA\\4 semestre\\NAI\\NAI-MPP-02 Perceptron\\NAI-MPP-02_Perceptron\\src\\com\\perceptron\\perceptron.data.csv");
        List<Element> zbior_testowy = readAndCreate("D:\\STUDIA\\4 semestre\\NAI\\NAI-MPP-02 Perceptron\\NAI-MPP-02_Perceptron\\src\\com\\perceptron\\perceptron.test.data.csv");

        Perceptron perceptron = new Perceptron(zbior_treningowy.get(0).coordinates.size(),stala_uczenia,akceptowalny_procent_bladow);

        double ilosc_testowanych ;
        double ilosc_poprawnie_klasyfikowanych ;

        while (loop_bool){
            ilosc_testowanych = 1;
            ilosc_poprawnie_klasyfikowanych = 0.0;

            for (Element e: zbior_treningowy){
                ilosc_testowanych+=1.0;
                int szacowane = perceptron.szacowane(e.coordinates);

                if ( (e.name_of_object.equals("Iris-versicolor") && szacowane == 0) || (e.name_of_object.equals("Iris-virginica") && szacowane == 1 ))
                    ilosc_poprawnie_klasyfikowanych+=1.0;
                else
                    perceptron.uczenie(e.coordinates, szacowane , (e.name_of_object.equals("Iris-versicolor") ? 0 : 1 ));
            }

            if (loop_iterations == learn_loop_limit){
                loop_bool=false;
                System.out.println("\nMargines bledu  " + (1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych)));
            }
            loop_iterations+=1;
        }

        int dokladnosc = 0;

        for(Element e : zbior_testowy){
            int szacowane = perceptron.szacowane(e.coordinates);
            if((szacowane == 1) && (e.name_of_object.equals("Iris-virginica")))
                dokladnosc++;
            else if((szacowane == 0) && (e.name_of_object.equals("Iris-versicolor")))
                dokladnosc++;
        }

        System.out.println("Dokladnosc: "+dokladnosc + "/" + zbior_testowy.size());

    }
    public static List<String> read(String file){
        List<String> list = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(file));
            while(scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Element> readAndCreate(String path){
        List<Element> list = new ArrayList<>();
        List<String> toRead = read(path);
        for(String s : toRead)
            list.add(new Element(s.split(",")));

        return list;
    }
}
