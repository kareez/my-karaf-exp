package my.sample.command

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class ListCommandCompleterSpec extends Specification {

    @Unroll
    def "completer with (#buffer, #courser)"() {
        ListCommandCompleter completer = new ListCommandCompleter()

        when:
        def r = completer.complete(buffer, courser, [])

        then:
        r == result

        where:
        buffer | courser || result
        "on"   | 0       || 0
        "on"   | 1       || 0
        "one"  | 1       || 0
        "ons"  | 1       || -1
    }
}
