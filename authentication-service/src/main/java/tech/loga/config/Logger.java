package tech.loga.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class Logger {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH'h'mm");

    public static void info(String note) {
        log.info("["+dateFormat.format(new Date(System.currentTimeMillis()))+"]"+note+"\n");
    }

    public static void warn(String note) {
        log.warn("["+dateFormat.format(new Date(System.currentTimeMillis()))+"]"+note+"\n");
    }

    public static void error(String note) {
        log.error("["+dateFormat.format(new Date(System.currentTimeMillis()))+"]"+note+"\n");
    }
}
