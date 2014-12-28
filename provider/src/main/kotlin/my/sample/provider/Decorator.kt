package my.sample.provider

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface Decorator {
    /**
     * Decorates the given message.
     * NOTE: default implementation is just to check JDK8 support

     * @param message the message to be decorated
     * *
     * @return the decorated message
     */
    fun decorate(message: String): String = message
}
