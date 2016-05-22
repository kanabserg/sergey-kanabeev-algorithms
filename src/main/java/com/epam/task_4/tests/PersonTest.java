package com.epam.task_4.tests;

import com.epam.task_4.person.Person;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class to tests Java 8 functionality.
 */
public class PersonTest {

    private static final int STREAM_SIZE = 10;

    /**
     * Private constructor to prevent creation of consumers.
     */
    private PersonTest() {
    }

    /**
     * Main method to run the test.
     */
    public static void run() {
        System.out.println("This test will show java 8 functionality applied to Streams....");

        System.out.println("Running java 8 tests...");
        List<Person> persons = Stream.generate(Person::new).limit(STREAM_SIZE).collect(Collectors.toList());
        System.out.println("Just generated stream:");
        persons.stream().forEach(System.out::println);

        double averageAge = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();
        System.out.printf("\nAverage age: %.1f\n", averageAge);

        System.out.println("\nSorting be age:");
        persons.stream()
                .sorted((fPerson, sPerson) -> Integer.compare(fPerson.getAge(), sPerson.getAge()))
                .forEach(System.out::println);

        System.out.println("\nAge subtraction for women:");
        ageSubtraction(value -> {
            int age = value.getAge();
            if (value.getSex() == Person.Sex.Female &&  age > 10){
                value.setAge(age - 10);
            }
            return value;
        },persons);

        System.out.println("\nSame name pairs:");
        persons.stream().map(Person::getName)
                .collect(HashMap::new, (map, name) -> map.put(name, map.containsKey(name) ? ((int) map.get(name) + 1) : 1), HashMap::putAll)
                .forEach((key, value) -> System.out.println(key + " -> " + value));

        System.out.println("\nFiltered under 18:");
        persons.stream().limit(10)
                .filter(person -> person.getAge() < 18)
                .forEach(System.out::println);
    }

    /**
     * Performs a function upon list.
     *
     * @param ageFunction function to be applied
     * @param persons list
     */
    private static void ageSubtraction(Function<Person,Person> ageFunction, List<Person> persons){
        persons.stream().map(ageFunction).forEach(System.out::println);
    }
}
