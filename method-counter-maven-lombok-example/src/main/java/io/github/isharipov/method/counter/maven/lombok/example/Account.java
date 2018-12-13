package io.github.isharipov.method.counter.maven.lombok.example;

import io.github.isharipov.method.counter.core.counter.Counter;
import lombok.Data;

@Data
public class Account {

    private final int accountNumber;

    @Counter
    public int getAccount() {
        return accountNumber + 1;
    }


}
