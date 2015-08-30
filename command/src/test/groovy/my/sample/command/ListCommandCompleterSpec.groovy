package my.sample.command

import org.apache.karaf.shell.api.console.CommandLine
import org.apache.karaf.shell.api.console.Session
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class ListCommandCompleterSpec extends Specification {

    @Ignore("re-write the test after changing to the new api")
    @Unroll
    def "completer with (#buffer, #courser)"() {
        given:
        def session = Mock(Session)
        def commandLine = Mock(CommandLine)
        commandLine.buffer >> buffer
        commandLine.cursorArgument >> courser
        def completer = new ListCommandCompleter()

        when:
        def r = completer.complete(session, commandLine, [])

        then:
        r == result

        where:
        buffer | courser || result
        "on"   | 0       || 0
        "on"   | 1       || 0
        "one"  | 1       || 0
//        "ons"  | 1       || -1
    }
}
