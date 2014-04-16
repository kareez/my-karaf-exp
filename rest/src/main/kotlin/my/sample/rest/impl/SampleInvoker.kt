package my.sample.rest.impl

import my.sample.activator.SampleLogger
import org.apache.cxf.jaxrs.JAXRSInvoker
import org.apache.cxf.message.Exchange

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class SampleInvoker : JAXRSInvoker() {
    public var logger: SampleLogger? = null

    override fun invoke(exchange: Exchange?, request: Any?, resourceObject: Any?): Any? {
        logger?.warn("Invoker...")
        return super.invoke(exchange, request, resourceObject)
    }
}
