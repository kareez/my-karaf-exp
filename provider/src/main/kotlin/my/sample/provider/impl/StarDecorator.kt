package my.sample.provider.impl

import my.sample.provider.SampleDecorator

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class StarDecorator : SampleDecorator {

    override fun decorate(message: String) = "*** $message ***"
}
