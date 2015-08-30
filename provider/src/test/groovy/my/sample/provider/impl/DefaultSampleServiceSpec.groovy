package my.sample.provider.impl

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DefaultSampleServiceSpec extends Specification {

    @Unroll
    def "test echo #message"() {
        given:
        def service = new DefaultSampleService()

        when:
        def r = service.echo(message)

        then:
        r == "Echo processed: $message".toString()

        where:
        message << ["one", "two", "three"]

    }
}

