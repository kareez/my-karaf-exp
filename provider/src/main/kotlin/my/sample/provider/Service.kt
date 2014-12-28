package my.sample.provider

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface Service {

    /**
     * Concatenates a fixed string with the given message and return the result.
     * NOTE: default implementation is just to check JDK8 support

     * @param message given message
     * *
     * @return the concatenation of a fixed string and given message
     */
    fun echo(message: String): String = message
}