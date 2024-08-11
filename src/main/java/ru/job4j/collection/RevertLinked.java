package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RevertLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        if (head != null) {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new Node<>(value, null);
            return;
        }
        head = new Node<>(value, null);
    }

    public boolean revert() {
        if (head != null && head.next != null) {
            Node<T> tail = head;
            Node<T> next = head.next;
            tail.next = null;
            while (next.next != null) {
                Node<T> cont = tail;
                tail = next;
                next = tail.next;
                tail.next = cont;
            }
            next.next = tail;
            head = next;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}