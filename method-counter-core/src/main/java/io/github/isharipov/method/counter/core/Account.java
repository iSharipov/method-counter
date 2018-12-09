package io.github.isharipov.method.counter.core;

import io.github.isharipov.method.counter.core.counter.Counter;

public class Account {
    @Counter
    public int getAccount(){
        return 1;
    }
}
