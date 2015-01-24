package my.sample.provider.impl;

import my.sample.provider.SampleService;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultSampleService implements SampleService {

    @Override
    public String echo(String message) {
        return "Echo processed: " + message;
    }
}