package my.sample.provider

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public trait SampleService {

    /**
     * Concatenates a fixed string with the given message and return the result.
     *
     * @param message given message
     * @return the concatenation of a fixed string and given message
     */
    fun echo(message: String): String
}