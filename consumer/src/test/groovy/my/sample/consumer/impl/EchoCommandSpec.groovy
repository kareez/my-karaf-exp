
package my.sample.consumer.impl

import my.sample.activator.Logger
import my.sample.provider.Service
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test cases for {@link EchoCommand}
 */
class EchoCommandSpec extends Specification {

    @SuppressWarnings("GroovyAccessibility")
    @Unroll
    def "test command with #arg"() {
        given:
        def command = new EchoCommand()
        command.logger = Mock(Logger)
        def service = Mock(Service)
        service.echo(arg) >> arg
        command.service = service
        command.arg = arg

        when:
        def result = command.execute()

        then:
        result == arg

        where:
        arg << ["one", "two", "three"]
    }
}
