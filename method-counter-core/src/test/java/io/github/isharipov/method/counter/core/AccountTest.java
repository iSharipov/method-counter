package io.github.isharipov.method.counter.core;

import io.github.isharipov.method.counter.core.behaviour.Behaviour;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    @Test
    public void getAccountTest() {
        Account account = new Account();
        int i;
        for (i = 0; i < 5; i++) {
            account.getAccount();
        }
        Counter counter = Metrics.counter("methods.counter",
                Tags.of(Tag.of(Behaviour.class.getSimpleName().toLowerCase(), Behaviour.Type.SUCCESS.name().toLowerCase()),
                        Tag.of("class", Account.class.getName()),
                        Tag.of("method", Account.class.getMethods()[0].getName())));
        Assert.assertEquals(counter.count(), i, 0);
    }
}
