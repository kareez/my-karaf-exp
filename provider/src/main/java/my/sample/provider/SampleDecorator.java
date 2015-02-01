package my.sample.provider;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface SampleDecorator {
    /**
     * Decorates the given message.
     *
     * @param message the message to be decorated
     * @return the decorated message
     */
     String decorate(String message);
}
