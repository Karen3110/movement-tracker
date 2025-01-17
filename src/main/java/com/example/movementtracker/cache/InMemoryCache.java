package com.example.movementtracker.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple in-memory cache implementation that stores key-value pairs where the key is of type K and the value is of type V. The cache is
 * thread-safe and utilizes ConcurrentHashMap for storage.
 *
 * @param <K>
 *         The type of the keys in the cache.
 * @param <V>
 *         The type of the values in the cache.
 */
public class InMemoryCache<K, V> {

    private final ConcurrentHashMap<K, List<V>> cache;

    public InMemoryCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void put(K key, V value) {
        this.cache.compute(key, (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(value);
            return v;
        });
    }

    public List<V> get(K key) {
        return this.cache.get(key);
    }

    public void clear() {
        this.cache.clear();
    }

    public void clear(K userId) {
        this.cache.remove(userId);
    }
}
