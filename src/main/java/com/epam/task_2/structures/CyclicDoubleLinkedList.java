package com.epam.task_2.structures;

import com.epam.task_2.structures.utils.Function;
import com.epam.task_2.structures.utils.TwoWayIterable;
import com.epam.task_2.structures.utils.TwoWayIterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A list implemented with a doubly linked list. The elements are stored
 * (and iterated over) in the same order that they are inserted.
 *
 * @param <T> the type of elements in nodes
 */
public class CyclicDoubleLinkedList<T extends Comparable<T>>
        implements Iterable<T>, TwoWayIterable<T> {

    private DoubleNode head;
    private int size = 0;
    private int modCount = 0;

    public CyclicDoubleLinkedList() {
        head = new DoubleNode(null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * Get an element by its index.
     *
     * @param index index of element
     * @return element value
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) {
        checkPositionIndex(index);
        return node(index).value;
    }

    /**
     * Add element to the end of list. (Actually, the element to be
     * added is placed before head element).
     *
     * @param element value to be added to
     */
    public void add(T element) {
        addNode(head, element);
    }

    /**
     * Add and element to list by index. Elements next to inserted one
     * will be moved.
     *
     * @param index   index of inserting element
     * @param element value to be inserted to
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, T element) {
        checkPositionIndex(index);
        addNode(node(index), element);
    }

    /**
     * Remove last element in the list. (Actually, the element to be
     * removed is placed before head element).
     */
    public void remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        removeNode(head.prev);
    }

    /**
     * Remove node by its index.
     *
     * @param index element index to be removed
     * @throws IndexOutOfBoundsException
     */
    public void remove(int index) {
        checkElementIndex(index);
        removeNode(node(index));
    }

    /**
     * Get an array representation of list.
     *
     * @return list representation
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        DoubleNode element = head.next;
        for (int i = 0; i < size; i++) {
            result[i] = element.value;
            element = element.next;
        }
        return result;
    }

    /**
     * Sort the list with MergeSort.
     */
    public void sort() {
        if (size > 1)
            head.next = doSort(head.next, size);
    }

    /**
     * Merge sort implementation
     *
     * @param firstEl first element in the list
     * @param size    size of list
     * @return first element in sorted list
     */
    private DoubleNode doSort(DoubleNode firstEl, int size) {
        if (size > 1) {
            DoubleNode secondEl = firstEl;
            for (int i = 0; i < size / 2; i++) {
                secondEl = secondEl.next;
            }
            firstEl = doSort(firstEl, size / 2);
            secondEl = doSort(secondEl, (size + 1) / 2);
            return merge(firstEl, secondEl, size);
        } else {
            return firstEl;
        }
    }

    /**
     * Merging two lists
     *
     * @param firstEl  beginning of the left list
     * @param secondEl beginning of the right list
     * @param size     size of total merged list
     * @return sorted merged list
     */
    private DoubleNode merge(DoubleNode firstEl, DoubleNode secondEl, int size) {
        DoubleNode result = firstEl.prev; //remember the beginning of the new list will begin after its merged
        int right = 0;
        int i = 0;
        while (i < size) {
            if (firstEl.value.compareTo(secondEl.value) <= 0) {
                if (firstEl.next == secondEl)
                    break;                  //end of first list and all items in the second list are already sorted, thus break
                firstEl = firstEl.next;
            } else {
                if (right == (size + 1) / 2)
                    break;                  //we have merged all elements of the right list into the first list, thus break
                if (secondEl == result)
                    result = result.prev;   //special case that we are merging the last element then the result element moves one step back.
                DoubleNode nextSecondEl = secondEl.next;
                /*remove second*/
                secondEl.prev.next = secondEl.next;
                secondEl.next.prev = secondEl.prev;
                /*insert second behind first.prev*/
                secondEl.prev = firstEl.prev;
                firstEl.prev.next = secondEl;
                /*insert second before first*/
                secondEl.next = firstEl;
                firstEl.prev = secondEl;
                /*move on to the next item in the second list*/
                secondEl = nextSecondEl;
                right++;
            }
            i++;
        }
        return result.next;                 //return the beginning of the merged list
    }

    /**
     * Transform list to the new list with defined by function
     * type.
     *
     * @param function function that defines hot to transform ourpur
     * @param <IN>     input values type
     * @param <OUT>    output values type
     * @return transformed instance of list
     */
    public <IN, OUT extends Comparable<OUT>> CyclicDoubleLinkedList<OUT> map(Function<IN, OUT> function) {
        CyclicDoubleLinkedList<OUT> newList = new CyclicDoubleLinkedList<>();
        for (T value : this) {
            try {
                newList.add(function.apply((IN) value));
            } catch (ClassCastException ex) {
                System.err.printf("ERROR: Value %s not added, cause cannot be converted", value);
            }
        }
        return newList;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private DoubleNode current = head.next;
            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                checkForComodification();
                if (!hasNext()) throw new NoSuchElementException();
                T value = current.value;
                current = current.next;
                index++;
                return value;
            }

            private void checkForComodification() {
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
            }
        };
    }

    @Override
    public TwoWayIterator<T> twoWayIterator() {
        return new TwoWayIterator<T>() {

            private DoubleNode current = head.next;

            @Override
            public T previous() {
                current = current.prev;
                if (current == head) current = head.prev;
                return current.value;
            }

            @Override
            public T next() {
                if (current == head) current = head.next;
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * an add operation.
     */
    private void checkPositionIndex(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size %d", index, size));
        }
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size %d", index, size));
    }

    /**
     * Add node to the list before pointer element
     *
     * @param pointer element after inserted one
     * @param element value to be inserted
     */
    private void addNode(DoubleNode pointer, T element) {
        DoubleNode newNode = new DoubleNode(element, pointer, pointer.prev);
        pointer.prev.next = newNode;
        pointer.prev = newNode;
        size++;
        modCount++;
    }

    /**
     * Remove node by unlinking it from siblings nodes
     *
     * @param node to be removed
     */
    private void removeNode(DoubleNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = node.prev = null;
        size--;
        modCount++;
    }

    /**
     * Get node by index
     *
     * @param index represent of number of element searched for
     * @return found node
     */
    private DoubleNode node(int index) {
        DoubleNode node = head.next;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    /**
     * Represent class of double linked list node
     */
    private class DoubleNode {
        T value;
        DoubleNode next;
        DoubleNode prev;

        DoubleNode(T value, DoubleNode next, DoubleNode prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        DoubleNode(T value) {
            this.value = value;
        }
    }
}
