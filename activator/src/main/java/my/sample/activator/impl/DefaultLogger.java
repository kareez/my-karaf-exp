package my.sample.activator.impl;

import my.sample.activator.SampleLogger;
import org.osgi.service.log.LogService;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultLogger implements SampleLogger {
    private LogService logger;

    public void setLogger(LogService logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String message) {
        log(LogService.LOG_DEBUG, message);
    }

    @Override
    public void info(String message) {
        log(LogService.LOG_INFO, message);
    }

    @Override
    public void warn(String message) {
        log(LogService.LOG_WARNING, message);
    }

    @Override
    public void error(String message) {
        log(LogService.LOG_ERROR, message);
    }

    private void log(int level, String message) {
        if (logger != null) {
            logger.log(level, message);
        } else {
            System.out.println(message);
        }
    }
}
