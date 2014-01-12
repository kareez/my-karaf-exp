package my.sample.rest.impl;

import my.sample.activator.SampleLogger;
import my.sample.rest.api.ItemService;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;

import java.io.IOException;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class Starter {

    public static void main(String[] args) throws IOException {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

        factory.setAddress("http://localhost:8282/repo");
        factory.setResourceClasses(ItemServiceImpl.class);
        ItemServiceImpl service  = new ItemServiceImpl();
        service.setLogger(new SampleLogger() {
            @Override
            public void debug(String message) {

            }

            @Override
            public void info(String message) {

            }

            @Override
            public void warn(String message) {

            }

            @Override
            public void error(String message) {

            }
        });

        factory.setResourceProvider(new SingletonResourceProvider(service));
        factory.setProvider(new JAXBElementProvider());

        Server server = factory.create();
        server.start();
    }
}
