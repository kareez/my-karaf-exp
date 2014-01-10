package my.sample.provider.impl

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class StarDecoratorSpec extends Specification {

    @Unroll
    def "test decoration for #message"() {
        def decorator = new StarDecorator()

        when:
        def r = decorator.decorate(message)

        then:
        r == "*** $message ***"

        where:
        message << ["one", "two", "a message"]
    }
}
