package my.sample.provider.impl

import my.sample.provider.Service

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultService : Service {

    override fun echo(message: String): String = "Echo processed: $message"
}