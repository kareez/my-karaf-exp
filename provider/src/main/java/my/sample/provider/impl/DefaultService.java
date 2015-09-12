package my.sample.provider.impl;

import my.sample.provider.Service;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultService implements Service {

    @Override
    public String echo(String message) {
        return "Echo processed: " + message;
    }
}