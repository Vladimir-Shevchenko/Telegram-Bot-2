package com.example.LAMBDAandPREDICATE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class WithoutLambda {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("fish",false,true) );
        animals.add(new Animal("kangaroo",true,false) );
        animals.add(new Animal("rabbit",true,false) );
        animals.add(new Animal("turtle",false,true) );


        
       // print(animals, new CheckIfHopper());  // передаем через анонимный класс, не полноценній анонимній а просто обїект без переменной

        //with lambda
        //print(animals, a -> true);         // fish kangaroo rabbit turtle
      //  print(animals, a -> a.canHope());  // kangaroo rabbit    -  передаем через анонимный метод
        print(animals, a -> a.canSwim());    // fishturtle rabbit    -  передаем через анонимный метод
        print2(animals, a -> a.canSwim());  // візіваем с предикатом метод

        //также предикат в методе ремув иф
        System.out.println("---------removeif:");
        animals.removeIf(a->a.canHope()); //принимает на вход предикат
        System.out.println(animals);
        animals.removeIf(a->false);
        System.out.println(animals);
        System.out.println("---------removeif-------");
    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal:animals) {
            if (checker.testAnimal(animal)){ System.out.println(animal+"  ");}
        }
    }


//такой же метож принт только уже вместо интерфейса предикат используем
    private static void print2(List<Animal> animals, Predicate<Animal> checker) {
        for (Animal animal:animals) {
            if (checker.test(animal)){ System.out.println(animal+"  from->predicate  ");}
        }
    }

}
