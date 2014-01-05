package my.sample.activator;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface SampleLogger {

    void debug(String message);

    void info(String message);

    void warn(String message);

    void error(String message);
}
