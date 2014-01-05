package my.sample.rest.impl;

import org.apache.cxf.jaxrs.JAXRSInvoker;
import org.apache.cxf.message.Exchange;
import org.osgi.service.log.LogService;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class SampleInvoker extends JAXRSInvoker {
    private LogService logger;

    public void setLogger(LogService logger) {
        this.logger = logger;
    }

    @Override
    public Object invoke(Exchange exchange, Object request, Object resourceObject) {
        log("Invoker...");
        return super.invoke(exchange, request, resourceObject);
    }

    private void log(String message) {
        if (logger != null) {
            logger.log(LogService.LOG_WARNING, message);
        } else {
            System.out.println(message);
        }
    }
}
