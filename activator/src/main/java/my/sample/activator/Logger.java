package my.sample.activator;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface Logger {

    /**
     * debug
     *
     * @param message the message to be logged
     */
    void debug(String message);

    /**
     * info
     *
     * @param message the message to be logged
     */
    void info(String message);

    /**
     * warn
     *
     * @param message the message to be logged
     */
    void warn(String message);

    /**
     * error
     *
     * @param message the message to be logged
     */
    void error(String message);
}
