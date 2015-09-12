package my.sample.rest.impl;

import my.sample.activator.Logger;
import org.apache.cxf.jaxrs.JAXRSInvoker;
import org.apache.cxf.message.Exchange;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class Invoker extends JAXRSInvoker {
    private Logger logger;

    @SuppressWarnings("UnusedDeclaration")
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Object invoke(Exchange exchange, Object request, Object resourceObject) {
        logger.warn("Invoker...");
        return super.invoke(exchange, request, resourceObject);
    }
}
