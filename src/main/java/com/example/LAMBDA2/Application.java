package com.example.LAMBDA2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        // LAMBDAS LAMBDAS LAMBDAS LAMBDAS LAMBDAS LAMBDAS LAMBDAS LAMBDAS LAMBDAS LAMBDAS
Printer printer = new Printer() {
    public void print(String msg){
        System.out.println(msg);
    }
};
printer.print("hi");

Printer lambdaPrinter = n -> System.out.println(n);
lambdaPrinter.print("hi lambda");

Printer methodRefPrinter = System.out::println;
methodRefPrinter.print("method reference");

        Map<String, Printer>map = new HashMap<>();
        map.put("first", lambdaPrinter );
        map.put("second", methodRefPrinter );
        map.put("third", new Printer() {
            @Override
            public void print(String msg) {
                System.out.println(msg);
            }
        });
        map.put("fourth", (m)-> {
            System.out.println("-------//-----");
                System.out.println(m);
            System.out.println("-------//-----");
        });

        map.put("fifth", m-> {
         //   System.out.println("-------//-----");
            System.out.println(m);}
         //   System.out.println("-------//-----");
        );
        map.get("fourth").print("im from four");
        map.get("fifth").colorPrint("5");//візіваем дефолтній метод интерфейса

        //STREAM API  STREAM API  STREAM API  STREAM API  STREAM API  STREAM API STREAM API STREAM API
        List<String>names = Arrays.asList("Vova","Kolya","Petya","Julya","Volk", "Vasya");
names.sort(String::compareToIgnoreCase); //сортируем массив
        for (String s:names) {  //обычный вариант распечатать
            System.out.println(s);
        }

        System.out.println("----------------------streeeeeeeeeeeeeeeeeeeeeaaaaaaaaaams");
                                // через стрим плюс фильтруем по нужному нам условию
        names.stream().filter(n->n.startsWith("J")).forEach(System.out::print);
        List<String>filteredNames = names.stream()
                .filter(n->n.startsWith("V"))
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
        System.out.println(filteredNames);


    }
}

//можно добавлять анатацию чтобы при добавлении второго абстрактн метода подчеркивалось красным
// и тогда мы точно будем знать что это функциональный интерфейс
@FunctionalInterface
interface Printer{
void print(String msg);
//void print2(String msg);
default void colorPrint(String msg){
    System.out.println("Color print: "+msg);
}
}
