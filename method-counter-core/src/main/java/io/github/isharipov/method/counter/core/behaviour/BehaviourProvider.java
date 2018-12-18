package io.github.isharipov.method.counter.core.behaviour;

import java.util.ServiceLoader;

public final class BehaviourProvider {

    public static final BehaviourProvider behaviourProvider = new BehaviourProvider();

    public Behaviour behaviour(Class behaviourClass) {
        ServiceLoader<Behaviour> loader = ServiceLoader.load(Behaviour.class);
        for (Behaviour behaviour : loader) {
            if (behaviour.getClass().equals(behaviourClass)) {
                return behaviour;
            }
        }
        throw new IllegalArgumentException("Cannot invoke " + behaviourClass);
    }
}
