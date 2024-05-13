package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

    public static void logInfo(String message, Object... args) {
        logger.info(message, args);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
