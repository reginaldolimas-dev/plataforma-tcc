package com.br.customerservice.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TabDelimitedLayout extends LayoutBase<ILoggingEvent> {

    @Override
    public String doLayout(ILoggingEvent event) {
        String timestamp = Instant.ofEpochMilli(event.getTimeStamp())
                .atZone(ZoneOffset.UTC)
                .truncatedTo(ChronoUnit.SECONDS)
                .format(DateTimeFormatter.ISO_INSTANT);

        String level = mapLevel(event.getLevel().toString());
        String message = event.getFormattedMessage();

        return timestamp + '\t' + level + '\t' + message + '\n';
    }

    private String mapLevel(String logbackLevel) {
        return "WARN".equals(logbackLevel) ? "WARNING" : logbackLevel;
    }
}