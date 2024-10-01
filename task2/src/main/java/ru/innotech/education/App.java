package ru.innotech.education;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Тестовое задание номер 2
 */
public class App {
    public static void main(String[] args) {

        List<Integer> listInts = Arrays.asList(new Integer[]{1,2,3,5,7,8,9,10,15,10,9,8,7,6});

        /*
            Реализуйте удаление из листа всех дубликатов
         */
        listInts.stream()
                    .distinct()
                    .forEach(System.out::println);

        /*
            Найдите в списке целых чисел 3-е наибольшее число
         */
        System.out.println(listInts.stream()
                                .sorted(Collections.reverseOrder())
                                .limit(3)
                                .min(Math::min)
                                .get());

        /*
            Найдите в списке целых чисел 3-е наибольшее «уникальное» число
         */
        System.out.println(listInts.stream()
                                    .distinct()
                                    .sorted(Collections.reverseOrder())
                                    .limit(3)
                                    .min(Math::min)
                                    .get());

        List<Employee> listEmployee = Arrays.asList(new Employee[]{
                new Employee("Stefanos", 30, Employee.Position.ENGINEER),
                new Employee("Carlos", 18, Employee.Position.ENGINEER),
                new Employee("Rafael", 48, Employee.Position.MANAGER),
                new Employee("Jannik", 51, Employee.Position.DIRECTOR),
                new Employee("Roger", 37, Employee.Position.ENGINEER),
                new Employee("Novak", 37, Employee.Position.ENGINEER),
        });

        /*
            Имеется список объектов типа Сотрудник (имя, возраст, должность),
            необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер»,
            в порядке убывания возраста
         */
        listEmployee.stream()
                        .filter(it -> it.position.equals(Employee.Position.ENGINEER))
                        .sorted((e1,e2) -> e2.age.compareTo(e1.age))
                        .limit(3)
                        .forEach(System.out::println);

        /*
            Имеется список объектов типа Сотрудник (имя, возраст, должность),
            посчитайте средний возраст сотрудников с должностью «Инженер»
        */
        System.out.println(listEmployee.stream()
                                        .filter(it -> it.position.equals(Employee.Position.ENGINEER))
                                        .mapToInt(Employee::getAge)
                                        .average()
                                        .getAsDouble());

        /*
            Найдите в списке слов самое длинное
         */
        String testString = "Test Taste Taate Testt Testtt Testttt Test";

        String[] words = testString.split(" ");
        System.out.println(Arrays.stream(words)
                                    .max(Comparator.comparingInt(String::length))
                                    .get());
        /*
            Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
            Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        */
        Map<String, Integer> wordsMap =  Arrays.stream(words)
                    .collect(HashMap::new, (map, key) -> {
                        if(map.containsKey(key))
                            map.put(key, map.get(key) + 1);
                        else
                            map.put(key, 1);
                    }, HashMap::putAll);

        /*
            Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
            если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        */
        Arrays.stream(words)
                        .sorted(Comparator.comparingInt(String::length)
                                          .thenComparing(Comparator.naturalOrder()))
                        .forEach(System.out::println);

        /*
            Имеется массив строк, в каждой из которых лежит набор из 5 строк, 
            разделенных пробелом, найдите среди всех слов самое длинное, 
            если таких слов несколько, получите любое из них
         */
        String[] wordsArray = new String[7];
        wordsArray[0] = "dasd dasd das ds";
        wordsArray[1] = "ytry trtrtrt tfdhjsfdsf fdsffdsffsdfdsfdsf";
        wordsArray[2] = "Test testss tfdhjsfdsf fdsffdsffsdfdsfdsf";
        wordsArray[3] = "Test testss tfdhjsfdsf fdsffdsffsdfdsfdsf";
        wordsArray[4] = "Test testss tfdhjsfdsf fdsffdsffsdfdsfdsf";
        wordsArray[5] = "Test testss tfdhjsfdsf jghfjjhjgfjgjfjfgjgfjgfjgfjj11111111";
        wordsArray[6] = "Test testss tfdhjsfdsf jghfjjhjgfjgjfjfgjgfjgfjgfjj11111110";
        
        System.out.println(Arrays.stream(wordsArray)
                                    .flatMap(pair -> Stream.of(pair.split(" ")))
                                    .collect(Collectors.toList())
                                    .stream()
                                        .sorted(Comparator.comparingInt(String::length).reversed())
                                        .findFirst()
                                        .get());
    }
}
