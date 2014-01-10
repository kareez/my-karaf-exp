package my.sample.provider.impl

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DefaultSampleServiceSpec extends Specification {

    @Unroll
    def "test echo #message"() {
        def service = new DefaultSampleService()

        when:
        def r = service.echo(message)

        then:
        r == "Echo processed: " + message

        where:
        message << ["one", "two", "three"]

    }
}

