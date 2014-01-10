package my.sample.consumer

import my.sample.provider.SampleDecorator
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DecoratorCommandSpec extends Specification {

    @Unroll
    def "test decorator command with #message"() {
        def decorator1 = Mock(SampleDecorator)
        decorator1.decorate(message) >> "X $message X"
        def decorator2 = Mock(SampleDecorator)
        decorator2.decorate(message) >> "Y $message Y"

        def command = new DecoratorCommand();
        command.setDecorators([decorator1, decorator2])
        command.arg = message

        when:
        def r = command.doExecute()

        then:
        r == ["X $message X", "Y $message Y"]

        where:
        message << ["one", "two", "a message"]
    }
}
