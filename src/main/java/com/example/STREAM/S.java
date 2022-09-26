package com.example.STREAM;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class S {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("cat", "dog", "horse", "fish" );
        Stream<String>stream = list.stream();
        System.out.println(stream.count());
        //после візова терминального метода повторное обрщ к стриму візовет ошибку

        IntStream intStream =  IntStream.of(7,5,3,9,8,2);
        intStream.reduce((r,l)->r+l).orElse(0);
       // intStream.forEach(System.out::println);

    }


}
