package com.xjsaber.java.collection17;

import java.util.*;

public class SlowMap<K, V> extends AbstractMap<K, V> {

    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    @Override
    public V put(K key, V value){
        V oldValue = this.get(key); // The old value or null
        if (!keys.contains(key)){
            keys.add(key);
            values.add(value);
        }
        else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    @Override
    public V get(Object key){ // key is type Object, not K
        if (!keys.contains(key)){
            return null;
        }
        return values.get(keys.indexOf(key));
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        Iterator<K> ki = keys.iterator();
        Iterator<V> vi = values.iterator();
        while (ki.hasNext()){
            set.add(new MapEntry<>(ki.next(), vi.next()));
        }
        return set;
    }

    public static void main(String[] args){
//        SlowMap<String, String> m = new SlowMap<>();
//        m.putAll(Countri);
    }
}
