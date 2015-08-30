package my.sample.consumer

import my.sample.provider.SampleDecorator
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
        def decorator1 = Mock(SampleDecorator)
        decorator1.decorate(message) >> "X $message X"
        def decorator2 = Mock(SampleDecorator)
        decorator2.decorate(message) >> "Y $message Y"

        def command = new DecoratorCommand();
        command.setDecorators([decorator1, decorator2])
        command.arg = message

        when:
        def r = command.execute() as List<String>

        then:
        r == ["X $message X", "Y $message Y"] as List<String>

        where:
        message << ["one", "two", "a message"]
    }
}
