package my.sample.rest.impl

import my.sample.activator.Logger
import org.apache.cxf.jaxrs.JAXRSInvoker
import org.apache.cxf.message.Exchange

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class Invoker : JAXRSInvoker() {
    lateinit var logger: Logger

    override fun invoke(exchange: Exchange, request: Any, resourceObject: Any): Any {
        logger.warn("Invoker...")
        return super.invoke(exchange, request, resourceObject)
    }
}
