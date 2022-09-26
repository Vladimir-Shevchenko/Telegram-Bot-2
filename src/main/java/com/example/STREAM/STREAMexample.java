package com.example.STREAM;  //  CTR+ALT+T ОКРУЖИТЬ КОНСТРУКЦИЕЙ ВІДЕЛЕНІЙ КОД

/*https://annimon.com/article/2778
- Ecть: 1. источник
        2. промежуточные - обрабатывают элементы и возвращают стрим
        3. терминальные операторы - завершает работу стрима, может быть только один.
           После терминальной операции стрим повторно нельзя использовать.
           Обработка стрима не начнется пока не будет вызван терминальный оператор
Источники:
empty()    -    пустой стрим
of()         -  стрим для последовательности, перечисленых элементов
ofNullable()   - возвращает пустой стрим, если в качестве аргумента передан null
generate()  -   стрим с бесконечной последовательностью
iterate()   -   бесконечній стрим с єлементами на основе функции
iterate()  -    конечный, как цикл на основе хэзнекст
concat()   -    объединяет два стрима
builder()  -    cоздаёт мутабельный объект для добавления элементов в стрим без использования какого-либо контейнера для этого
range()    -    coздает стрим из числового промежутка
rangeClosed() - тоже самое только включает в стрим последний элемент включительно(второго индекса)

Промежуточные операторы:
filter - фильтрует, взять лишь некоторые элементы
map - преобразовать каждый элемент, применяет функцию к каждому элементу и затем возвращает стрим,можно применять для изменения типа элементов
flatMap -для преобразования, свидения(несколько стримов в один), а мєп только для преобразоаания
reduce - посчитать сумму элементов или объединить всё в один объект
limit - ограничивает кол-во єлементов
forEach - применяет функцию print к каждому приходящему элементу
skip    - Пропускает n элементов стрима
sorted - сортирует и помечает как отсортированій, если отсортированій стрим то сортировать не будет
sorted(Comparator comparator) - c компаратором
distinct  -  убирает повторяющиеся элементы и возвращаем стрим с уникальными элементами
peek -  просто передает дальше и можно в промежутке делать действия с єлементами или віводить на єкран
takeWhile -  появился в Java 9. Возвращает элементы до тех пор, пока они удовлетворяют условию
dropWhile  - наоборот, пропускает элементы до тех пор, пока они удовлетворяют условию, затем возвращает оставшуюся часть стрима
boxed   -    преобразует примитивный стрим в объектный
*/


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

public class STREAMexample {
    public static void main(String[] args) {

        /*
  - Пустой стрим: Stream.empty() // Stream<String>
  - Стрим из List: list.stream() // Stream<String>
  - Стрим из Map: map.entrySet().stream() // Stream<Map.Entry<String, String>>
  - Стрим из массива: Arrays.stream(array) // Stream<String>
  - Стрим из указанных элементов: Stream.of("a", "b", "c") // Stream<String>
  */

        //STREAM OF ARRAY
       Integer []ar = new Integer[]{1,7,4,6,2,8};
        List<Integer> list = Arrays.stream(ar)
                .filter(s -> s>3)
                .collect(Collectors.toList());
        System.out.println("Arrays.stream(ar):  "+list);

        //СТРИМ ИЗ ПОСЛЕДОВАТЕЛЬНОСТИ
        System.out.print("Стрим из последовательности: ");
        IntStream.of(33,28,66,24,45,56,11,38)
                .filter(num->num<35)
                .map(num->num*2)
                .limit(2)
                .forEach(num->System.out.print(num+" "));



//parallelStream()
   List<Integer> list2  =     list.parallelStream()
                .filter(x -> x < 10)
                .map(x -> x * 2)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("list2: "+list2);


        IntStream.range(0, 4)
                .parallel()
                .map(x -> x * 10)
                .forEach(System.out::println);
                //.sum();


//////////////////////////////////////////////////////////////////////////////////////////////
       //пустой стрим
        Stream.empty()
                .forEach(System.out::println);// Вывода нет

        //of()
        System.out.println("of: -----------------------");
        Stream.of("one","two","three","four")
                .forEach(System.out::println);

        //ofNullable()  -  в джава 9
//        String s = Math.random()>0.5?"meow":null;
//        Stream.ofNullable(s)
//                .forEach(System.out::println);

        //generate()
        System.out.println("generate:---------------------------");

        Stream.generate(()->7) //поток семерок идет
                .limit(3)
                .forEach(System.out::println);//7,7,7

        //iterate() - бесконечній
        System.out.println("iterate:---------------------------");
        Stream.iterate(3,x->x+2)     //генерирует цифры, начиная с тройки прибавляет каждый раз два
                .limit(5)
                .forEach(System.out::println);//3,5,7,9,11

//iterate()  -конечный, как цикл на основе хэзнекст
        System.out.println("iterate с хэзнекст:---------------------------");
        Stream.iterate(2, x -> x < 25, x -> x + 6)  //хєзнекст меньше ли 25ти
                .forEach(System.out::println);// 2, 8, 14, 20

        //concat()
        System.out.println("concat:  --------------------------------");
        Stream.concat(
                Stream.of(2,4),
                Stream.of(1,7)
        ).forEach(System.out::println); //2,4,1,7


        //build()
        System.out.println("build:------------------");
        Stream.Builder<Integer> streamBuider = Stream.<Integer>builder()
                .add(0)
                .add(1);
        for (int i = 2; i <= 8; i += 2) {
            streamBuider.accept(i);
        }
        streamBuider
                .add(9)
                .add(10)
                .build()
                .forEach(System.out::println); // 0, 1, 2, 4, 6, 8, 9, 10


//range() - inclusive
        System.out.println("int range:--------------");
        IntStream.range(7,11)
                .forEach(System.out::println);//7,8,9,10

        System.out.println("long range:--------------");
        LongStream.range(4,7)
                .forEach(System.out::println); //4,5,6

        //rangeClosed() - exclusive
        System.out.println("int rangeClosed:--------------");
        IntStream.rangeClosed(0, 5)
                .forEach(System.out::println);// 0, 1, 2, 3, 4, 5

        System.out.println("long rangeClosed:--------------");
        LongStream.rangeClosed(-8L, -5L)
                .forEach(System.out::println);// -8, -7, -6, -5


// ПРОМЕЖУТОЧНЫЕ   INTERMEDIATE ПРОМЕЖУТОЧНЫЕ   INTERMEDIATE ПРОМЕЖУТОЧНЫЕ   INTERMEDIATE


        //map
        Stream.of("3", "4", "5")
                .map(Integer::parseInt)
                .map(x -> x + 10)
                .forEach(System.out::println);// 13, 14, 15

        Stream.of(120, 410, 85, 32, 314, 12)
                .map(x -> x + 11)
                .forEach(System.out::println);// 131, 421, 96, 43, 325, 23

        System.out.println( "I: "+Integer.parseInt("32", 16));

        //flatMap
               // Creating a List of Strings
                List<String> l = Arrays.asList("5.6", "7.4", "4",
                "1", "2.3");

                // Using Stream flatMap(Function mapper)
                 l.stream().flatMap(num -> Stream.of(num)).
                forEach(System.out::println);



                 /////////////flatMap example 2 - сведение
        // Creating a list of Prime Numbers
        List<Integer> PrimeNumbers = Arrays.asList(5, 7, 11,13);

        // Creating a list of Odd Numbers
        List<Integer> OddNumbers = Arrays.asList(1, 3, 5);

        // Creating a list of Even Numbers
        List<Integer> EvenNumbers = Arrays.asList(2, 4, 6, 8);

        List<List<Integer>> listOfListofInts =
                Arrays.asList(PrimeNumbers, OddNumbers, EvenNumbers);

        System.out.println("The Structure before flattening is : " +
                listOfListofInts);

        // Using flatMap for transformating and flattening.
        List<Integer> listofInts  = listOfListofInts.stream()
                .flatMap(ll -> ll.stream())
                .collect(Collectors.toList());

        System.out.println("The Structure after flattening is : " +
                listofInts);


        //flatMapToInt
        Stream.of(2, 3, 0, 1, 3)
                .flatMapToInt(x -> IntStream.range(0, x))
                .forEach(System.out::println); // 0, 1, 0, 1, 2, 0, 0, 1, 2

        Stream.of(1, 2, 3, 4, 5, 6)
                .flatMap(x -> {
                    switch (x %
                            3
                    ) {
                        case 0:
                            return Stream.of(x, x*x, x*x*
                                    2
                            );
                        case 1:
                            return Stream.of(x);
                        case 2:
                        default:
                            return Stream.empty();
                    }
                })
                .forEach(System.out::println);// 1, 3, 9, 18, 4, 6, 36, 72


        //multiMap  d -  java 16
//        Stream.of(1, 2, 3, 4, 5, 6)
//                .mapMulti((x, consumer) -> {
//                    if (x % 2 == 0) {
//                        consumer.accept(-x);
//                        consumer.accept(x);
//                    }
//                })
//                .forEach(System.out::println);// -2, 2, -4, 4, -6, 6

        //skip
        System.out.println("skip:---------------------------------");
        Stream.of(120, 410, 85, 32, 314, 12)
                .skip(2)
                .forEach(System.out::println);// 85, 32, 314, 12

        //sorted
        System.out.println("sorted:--------------------------------");
        Stream.of(120, 410, 85, 32, 314, 12)
                .sorted()
                .forEach(System.out::println);// 12, 32, 85, 120, 314, 410

        System.out.println("sorted with comparator:---------------------------------");
        Stream.of(120, 410, 85, 32, 314, 12)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);// 410, 314, 120, 85, 32, 12


//distinct
        System.out.println("distinct:-----------------------------------------");
        Stream.of(2, 1, 8, 1, 3, 2)
                .distinct()
                .forEach(System.out::println);// 2, 1, 8, 3

//peek
        System.out.println("peek:---------------------------------");
        Stream.of(0, 3, 0, 0, 5)
                .peek(x -> System.out.format("before distinct: %d%n", x))
                .distinct()
                .peek(x -> System.out.format("after distinct: %d%n", x))
                .map(x -> x * x)
                .forEach(x -> System.out.format("after map: %d%n", x));


//takeWhile
        System.out.println("takeWhile:-----------------------------------------");
        Stream.of(1, 2, 3, 4, 2, 5)
                .takeWhile(x -> x < 3)
                .forEach(System.out::println);// 1, 2

        //dropeWhile
        System.out.println("dropWhile:-----------------------------------------");
        Stream.of(1,2,3,4,5)
                .dropWhile(x->x<3)
                        .forEach(System.out::println);







        //boxed
        System.out.println("boxed:---------------------------------");
        DoubleStream.of(0.1, Math.PI)
                    .boxed()
                .map(Object::getClass)
                .forEach(System.out::println);
// class java.lang.Double
// class java.lang.Double




















    }
}
