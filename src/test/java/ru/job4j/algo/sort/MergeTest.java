package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {
    
    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    public void whenArrayContainsOnlyNumbersThanGreatThatZero() {
        int[] array = {10, 4, 6, 4, 8, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    public void whenArrayContainsOnlyNumbersThanLessThatZero() {
        int[] array = {-4, -6, -4, -8, -13};
        assertThat(Merge.mergesort(array)).containsExactly(-13, -8, -6, -4, -4);
    }

    @Test
    public void whenArrayIsEmptyThatGetEmptyArray() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }

    @Test
    public void whenArrayContainsOneNumber() {
        int[] array = {10};
        assertThat(Merge.mergesort(array)).containsExactly(10);
    }

    @Test
    public void whenArrayContainsIdenticalNumbers() {
        int[] array = {10, 10, 10};
        assertThat(Merge.mergesort(array)).containsExactly(10, 10, 10);
    }
}