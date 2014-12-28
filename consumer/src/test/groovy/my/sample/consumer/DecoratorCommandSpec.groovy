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
        def first = Mock(Decorator)
        first.decorate(message) >> "X $message X"
        def second = Mock(Decorator)
        second.decorate(message) >> "Y $message Y"

        def command = new DecoratorCommand();
        command.decorators = [first, second]
        command.arg = message

        when:
        def r = command.execute() as List<String>

        then:
        r == ["X $message X", "Y $message Y"] as List<String>

        where:
        message << ["one", "two", "a message"]
    }

    @SuppressWarnings("GroovyAccessibility")
    @Unroll
    def "test decorator command without any decorator"() {
        given:
        def command = new DecoratorCommand();
        command.decorators = []

        when:
        def r = command.execute() as List<String>

        then:
        r == []
    }
}
