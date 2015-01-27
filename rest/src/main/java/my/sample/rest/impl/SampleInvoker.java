package my.sample.rest.impl;

import my.sample.activator.SampleLogger;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class SampleInvoker { //extends JAXRSInvoker {
    private SampleLogger logger;

    @SuppressWarnings("UnusedDeclaration")
    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

//    @Override
//    public Object invoke(Exchange exchange, Object request, Object resourceObject) {
//        logger.warn("Invoker...");
//        return super.invoke(exchange, request, resourceObject);
//    }
}
