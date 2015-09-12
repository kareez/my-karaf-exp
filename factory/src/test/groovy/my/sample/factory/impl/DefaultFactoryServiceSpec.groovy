package my.sample.factory.impl

import spock.lang.Specification

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DefaultFactoryServiceSpec extends Specification {
    DefaultFactoryService service

    def setup() {
        service = new DefaultFactoryService()
    }

    def "check echo implementation"() {
        when:
        def result = new DefaultFactoryService().echo("Me")

        then:
        result == "Echo processed: Me"
    }
}
