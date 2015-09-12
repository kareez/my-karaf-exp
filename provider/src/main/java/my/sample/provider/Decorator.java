package my.sample.provider;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface Decorator {
    /**
     * Decorates the given message.
     * NOTE: default implementation is just to check JDK8 support
     *
     * @param message the message to be decorated
     * @return the decorated message
     */
    default String decorate(String message) {
        return message;
    }
}
