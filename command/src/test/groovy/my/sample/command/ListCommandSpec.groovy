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

    @Unroll
    def "execute with parameter #arg"() {
        command.arg = arg

        when:
        def result = command.doExecute()

        then:
        result[1] == res

        where:
        arg    | res
        "arg"  | "arg"
        null   | "<not given>"
        "test" | "test"
    }
}
