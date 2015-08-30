
package my.sample.consumer

import my.sample.activator.SampleLogger
import my.sample.provider.SampleService
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
        command.setLogger(Mock(SampleLogger))
        def service = Mock(SampleService)
        service.echo(arg) >> arg
        command.setService(service)
        command.arg = arg

        when:
        def result = command.execute()

        then:
        result == arg

        where:
        arg << ["one", "two", "three"]
    }
}
