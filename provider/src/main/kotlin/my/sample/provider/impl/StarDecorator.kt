package my.sample.provider.impl

import my.sample.provider.Decorator

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class StarDecorator : Decorator {
    override fun decorate(message: String): String = "*** $message ***"
}
