package com.epam.task_2;

import com.epam.task_2.structures.CyclicDoubleLinkedList;
import com.epam.task_2.structures.utils.Function;
import com.epam.task_2.structures.utils.TwoWayIterator;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        CyclicDoubleLinkedList<Integer> list= new CyclicDoubleLinkedList<>();
        System.out.print("Adding elements to the end:\t\t");
        list.add(2);
        list.add(1);
        list.add(7);
        list.add(3);
        list.add(5);
        list.add(5);
        list.add(1);
        list.add(7);
        list.add(2);
        System.out.println(Arrays.toString(list.toArray()));

        System.out.print("Adding element by index (1):\t");
        list.add(1,0);
        System.out.println(Arrays.toString(list.toArray()));

        System.out.print("Remove last element:\t\t\t");
        list.remove();
        System.out.println(Arrays.toString(list.toArray()));

        System.out.print("Remove element by index (5):\t");
        list.remove(7);
        System.out.println(Arrays.toString(list.toArray()));

        System.out.print("\nMerge sort:\t\t\t\t\t\t");
        list.sort();
        System.out.println(Arrays.toString(list.toArray()));

        System.out.print("\nPrinting with foreach:\t\t\t");
        for (Integer value: list) {
            System.out.print(String.format("%d ", value));
        }

        System.out.println("\nUsing TwoWaYIterator:");
        TwoWayIterator<Integer> twoWayIterator = list.twoWayIterator();
        System.out.println("Two elements forward:");
        System.out.println(twoWayIterator.next());
        System.out.println(twoWayIterator.next());
        System.out.println("Three elements backwards:");
        System.out.println(twoWayIterator.previous());
        System.out.println(twoWayIterator.previous());
        System.out.println(twoWayIterator.previous());

        System.out.println("\nMap function tests:");
        CyclicDoubleLinkedList<String> newList = list.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer value) {
                return value.toString();
            }
        });
        for (String value: newList) {
            System.out.print(value);
        }

        System.out.println("\n\nException tests:");
        for (Integer s : list)
            list.add(0);
    }
}
