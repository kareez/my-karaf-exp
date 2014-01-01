package my.sample.provider.impl;

import my.sample.provider.SampleDecorator;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class HashDecorator implements SampleDecorator {
    @Override
    public String decorate(String message) {
        return "### " + message + " ###";
    }
}
