package io.github.isharipov.method.counter.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.Meter;

public class MetricDetail {
    private final Meter.Id meter;
    private final long count;

    public MetricDetail(Meter.Id meter, long count) {
        this.meter = meter;
        this.count = count;
    }

    public Meter.Id getMeter() {
        return meter;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
