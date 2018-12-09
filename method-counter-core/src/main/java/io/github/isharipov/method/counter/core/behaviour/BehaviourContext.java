package io.github.isharipov.method.counter.core.behaviour;

import java.util.HashMap;
import java.util.Map;

public final class BehaviourContext {

    private static final Map<Behaviour.Type, Behaviour> behaviourMap = new HashMap<>();

    static {
        behaviourMap.put(Behaviour.Type.ALWAYS, new AlwaysBehaviour());
        behaviourMap.put(Behaviour.Type.FAILURE, new FailureBehaviour());
        behaviourMap.put(Behaviour.Type.SUCCESS, new SuccessBehaviour());
    }

    public static Behaviour getBehaviour(Behaviour.Type behaviourType) {
        if (!behaviourMap.containsKey(behaviourType)) {
            throw new IllegalArgumentException("Cannot invoke " + behaviourType);
        }
        return behaviourMap.get(behaviourType);
    }
}
