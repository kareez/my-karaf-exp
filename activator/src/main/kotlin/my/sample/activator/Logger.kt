package my.sample.activator

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
interface Logger {
    /**
     * debug
     *
     * @param message the message to be logged
     */
    fun debug(message: String)

    /**
     * info
     *
     * @param message the message to be logged
     */
    fun info(message: String)

    /**
     * warn
     *
     * @param message the message to be logged
     */
    fun warn(message: String)

    /**
     * error
     *
     * @param message the message to be logged
     */
    fun error(message: String)
}
