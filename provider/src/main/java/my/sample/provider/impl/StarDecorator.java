package my.sample.provider.impl;

import my.sample.provider.Decorator;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class StarDecorator implements Decorator {
    @Override
    public String decorate(String message) {
        return "*** " + message + " ***";
    }
}
