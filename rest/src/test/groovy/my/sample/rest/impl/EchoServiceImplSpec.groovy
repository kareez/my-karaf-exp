package my.sample.rest.impl

import my.sample.activator.SampleLogger
import my.sample.factory.SampleFactoryService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class EchoServiceImplSpec extends RepoAware {

    EchoServiceImpl service
    SampleFactoryService factory

    def setup() {
        service = new EchoServiceImpl()

        service.logger = Mock(SampleLogger)
        service.factory = factory = Mock(SampleFactoryService)
    }

    def "check echo"() {
        given:
        factory.echo(_ as String) >> "Echo Me"

        when:
        def r = service.echo()

        then:
        r.entity == "Echo Me"
    }
}
