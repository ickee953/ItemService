package com.github.ickee953.micros.core.messages;

public interface Producer<K, V> {

    void send(K key, V value);

}
