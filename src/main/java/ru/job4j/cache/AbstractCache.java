package ru.job4j.cache;

import java.io.FileNotFoundException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> softReference = new SoftReference<>(value);
        cache.put(key, softReference);
    }

    public final V get(K key) throws FileNotFoundException {
        V v = (V) cache.getOrDefault(key, null);
        if (v == null) {
            v = load(key);
        }
        return v;
    }

    protected abstract V load(K key) throws FileNotFoundException;
}