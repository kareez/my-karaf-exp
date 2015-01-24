package my.sample.factory.impl;

import my.sample.activator.SampleLogger;
import my.sample.factory.SampleFactoryService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultSampleFactoryService implements SampleFactoryService {
    private SampleLogger logger;
    private Map<String, Object> config;

    public DefaultSampleFactoryService() {
        this.config = new HashMap<>();
    }

    public void init() {
        logger.info("Initializing DefaultSampleFactoryService: " + this);
    }

    public void update(Map<String, ?> config) {
        this.config.clear();
        this.config.putAll(config);

        logger.info("updating....: " + this);
    }

    public void destroy() {
        logger.info("Destroying DefaultSampleFactoryService: " + this);
    }

    public String echo(String message) {
        return "Echo processed: " + message;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (String key : config.keySet()) {
            builder.append("\n\t").append(key).append(": ").append(config.get(key));
        }
        builder.append("\n").append("}");

        return builder.toString();
    }
}