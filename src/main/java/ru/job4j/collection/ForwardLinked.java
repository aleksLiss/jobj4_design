package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;


    public void add(T value) {
        if (head == null) {
            head = new ForwardLinked.Node<>(value, null);
        } else {
            ForwardLinked.Node<T> next = head;
            while (next.next != null) {
                next = next.next;
            }
            next.next = new ForwardLinked.Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> next = head;
        for (int counter = 0; counter < index; counter++) {
            next = next.next;
        }
        return next.getItem();
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> deleted = head;
        head = deleted.next;
        if (head.next != null) {
            head.next = deleted.next.next;
        }
        return deleted.getItem();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private ForwardLinked.Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T val = currentNode.item;
                currentNode = currentNode.next;
                return val;
            }
        };
    }

    private static class Node<E> {

        private E item;
        private ForwardLinked.Node<E> next;

        public Node(E item, ForwardLinked.Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public E getItem() {
            return item;
        }
    }
}