package ru.otus.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    Map<K, V> cache = new WeakHashMap<>();
    List<HwListener<K, V>> listeners = new ArrayList<>();
    @Override
    public void put(K key, V value) {
        cache.put(key, value);
        listeners.forEach(listener -> listener.notify(key, value, "put"));
    }

    @Override
    public void remove(K key) {
        var value = cache.get(key);
        cache.remove(key);
        listeners.forEach(listener -> listener.notify(key, value, "remove"));
    }

    @Override
    public V get(K key) {
        var value = cache.get(key);
        listeners.forEach(listener -> listener.notify(key, value, "get"));
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        this.listeners.remove(listener);
    }
}
