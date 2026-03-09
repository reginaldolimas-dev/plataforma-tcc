package com.br.customerservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLifecycleLogger {

    private static final Logger log = LoggerFactory.getLogger(ApplicationLifecycleLogger.class);

    @EventListener(ApplicationStartedEvent.class)
    public void onStart() {
        log.info("Service starting...");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        log.info("Application ready to receive requests");
    }

    @EventListener(ContextClosedEvent.class)
    public void onShutdown() {
        log.info("Service shutting down...");
    }
}