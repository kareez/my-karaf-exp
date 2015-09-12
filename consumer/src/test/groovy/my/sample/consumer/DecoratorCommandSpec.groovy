package my.sample.consumer

import my.sample.provider.Decorator
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DecoratorCommandSpec extends Specification {

    @SuppressWarnings("GroovyAccessibility")
    @Unroll
    def "test decorator command with #message"() {
        given:
        def decorator1 = Mock(Decorator)
        decorator1.decorate(message) >> "X $message X"
        def decorator2 = Mock(Decorator)
        decorator2.decorate(message) >> "Y $message Y"

        def command = new DecoratorCommand();
        command.decorators = [decorator1, decorator2]
        command.arg = message

        when:
        def r = command.execute() as List<String>

        then:
        r == ["X $message X", "Y $message Y"] as List<String>

        where:
        message << ["one", "two", "a message"]
    }
}
