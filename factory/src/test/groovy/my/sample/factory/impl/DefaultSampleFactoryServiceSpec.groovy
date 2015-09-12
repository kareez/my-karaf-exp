package my.sample.factory.impl

import spock.lang.Specification

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DefaultSampleFactoryServiceSpec extends Specification {
    DefaultSampleFactoryService service

    def setup() {
        service = new DefaultSampleFactoryService()
    }

    def "check echo implementation"() {
        when:
        def result = new DefaultSampleFactoryService().echo("Me")

        then:
        result == "Echo processed: Me"
    }
}
