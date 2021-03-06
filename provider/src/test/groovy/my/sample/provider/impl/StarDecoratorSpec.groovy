package my.sample.provider.impl

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class StarDecoratorSpec extends Specification {

    @Unroll
    def "test decoration for #message"() {
        given:
        def decorator = new StarDecorator()

        when:
        def r = decorator.decorate(message)

        then:
        r == "*** $message ***".toString()

        where:
        message << ["one", "two", "a message"]
    }
}
