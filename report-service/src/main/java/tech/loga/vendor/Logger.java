package tech.loga.vendor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class Logger {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH'h'mm");

    public static void info(String message) {
        log.info("["+dateFormat.format(new Date())+"]"+message+"\n");
    }

    public static void warn(String message) {
        log.warn("["+dateFormat.format(new Date())+"]"+message+"\n");
    }

    public static void error(String message) {
        log.error("["+dateFormat.format(new Date())+"]"+message+"\n");
    }
}