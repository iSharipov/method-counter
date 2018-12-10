package io.github.isharipov.method.counter.gradle.example;

import io.github.isharipov.method.counter.core.counter.Counter;

public class Account {

    @Counter
    public int getAccount() {
        return 1;
    }
}
