package my.sample.rest.impl;

import my.sample.activator.Logger;
import my.sample.factory.FactoryService;
import my.sample.rest.api.EchoService;

import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class EchoServiceImpl implements EchoService {
    private FactoryService factory;
    private Logger logger;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void setFactory(FactoryService factory) {
        this.factory = factory;
    }

    @Override
    public Response echo() {
        logger.warn("GET:: echo");

        return Response.ok(factory.echo(new Date().toString())).build();
    }
}