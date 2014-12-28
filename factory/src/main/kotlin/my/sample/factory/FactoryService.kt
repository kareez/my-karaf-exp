package my.sample.factory

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface FactoryService {

    /**
     * Concatenates a fixed string with the given message and return the result.

     * @param message given message
     * *
     * @return the concatenation of a fixed string and given message
     */
    public fun echo(message: String): String
}