package my.sample.rest.impl;

import my.sample.activator.SampleLogger;
import my.sample.factory.SampleFactoryService;
import my.sample.rest.api.EchoService;

import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class EchoServiceImpl implements EchoService {
    private SampleFactoryService factory;
    private SampleLogger logger;

    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

    public void setFactory(SampleFactoryService factory) {
        this.factory = factory;
    }

    @Override
    public Response echo() {
        logger.warn("GET:: echo");

        return Response.ok(factory.echo(new Date().toString())).build();
    }
}