package io.github.isharipov.method.counter.core.counter;

import io.github.isharipov.method.counter.core.behaviour.Behaviour;
import io.github.isharipov.method.counter.core.counter.type.CounterType;
import io.github.isharipov.method.counter.core.counter.type.DefaultCounterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Inherited
public @interface Counter {

    String name() default "";

    Class<? extends CounterType> type() default DefaultCounterType.class;

    Behaviour.Type behaviour() default Behaviour.Type.SUCCESS;
}
