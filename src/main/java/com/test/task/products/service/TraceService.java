package com.test.task.products.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TraceService {

    private final Tracer tracer;

    public String getTraceId() {
        if (tracer.currentSpan() == null || tracer.currentSpan().context() == null) {
            return UUID.randomUUID().toString();
        }
        return tracer.currentSpan().context().traceId();
    }
}
