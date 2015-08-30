package my.sample.command

import my.sample.activator.SampleLogger
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test cases for {@link my.sample.command.ListCommand}
 */
class ListCommandSpec extends Specification {

    ListCommand command

    def setup() {
        command = new ListCommand()
        command.setLogger(Mock(SampleLogger))
    }

    @SuppressWarnings("GroovyAccessibility")
    @Unroll
    def "execute with parameter #arg"() {
        given:
        command.arg = arg

        when:
        def result = command.execute() as List<String>

        then:
        result[1] == res

        where:
        arg    | res
        "arg"  | "arg"
        null   | "<not given>"
        "test" | "test"
    }
}
