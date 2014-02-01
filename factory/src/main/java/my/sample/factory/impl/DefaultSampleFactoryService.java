package my.sample.factory.impl;

import my.sample.factory.SampleFactoryService;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultSampleFactoryService implements SampleFactoryService {
    private InternalBean internalBean;

    public DefaultSampleFactoryService() {
        System.out.println("new DefaultSampleFactoryService: " + this);
    }

    public void init() {
        System.out.println("Initializing DefaultSampleFactoryService: " + this);
    }
    public String echo(String message) {
        return "Echo processed: " + message;
    }

    public void setInternalBean(InternalBean internalBean) {
        System.out.println("setting new internal bean: " + internalBean);
        this.internalBean = internalBean;
    }

    @Override
    public String toString() {
        return "DefaultSampleFactoryService {" +
                " internalBean = " + internalBean +
                " }";
    }
}