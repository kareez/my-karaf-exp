package my.sample.provider;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface SampleDecorator {
    /**
     * Decorates the given message.
     * the default implementation just return the string without any change.
     *
     * @param message the message to be decorated
     * @return the decorated message
     */
    default String decorate(String message) {
        return message;
    }
}
