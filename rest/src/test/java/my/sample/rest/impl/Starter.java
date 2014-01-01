package my.sample.rest.impl;

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
        factory.setResourceProvider(new SingletonResourceProvider(new ItemServiceImpl()));
        factory.setProvider(new JAXBElementProvider());

        Server server = factory.create();
        server.start();
    }
}
