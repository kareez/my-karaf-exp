package my.sample.activator.impl

import my.sample.activator.Logger
import org.osgi.service.log.LogService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultLogger(private val logger: LogService) : Logger {

    override fun debug(message: String) = log(LogService.LOG_DEBUG, message)

    override fun info(message: String) = log(LogService.LOG_INFO, message)

    override fun warn(message: String) = log(LogService.LOG_WARNING, message)

    override fun error(message: String) = log(LogService.LOG_ERROR, message)

    private fun log(level: Int, message: String) = logger.log(level, message)
}
