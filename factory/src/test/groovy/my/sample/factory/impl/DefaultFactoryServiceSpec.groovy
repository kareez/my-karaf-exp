package my.sample.factory.impl

import my.sample.activator.Logger
import spock.lang.Specification

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DefaultFactoryServiceSpec extends Specification {
    DefaultFactoryService service

    def setup() {
        service = new DefaultFactoryService()
        service.logger = Mock(Logger)
    }

    def "check echo implementation"() {
        when:
        def result = service.echo("Me")

        then:
        result == "Echo processed: Me"
    }
}
