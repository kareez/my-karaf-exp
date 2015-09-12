package my.sample.rest.impl

import my.sample.activator.Logger
import my.sample.factory.FactoryService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class EchoServiceImplSpec extends RepoAware {

    EchoServiceImpl service
    FactoryService factory

    def setup() {
        service = new EchoServiceImpl()

        service.logger = Mock(Logger)
        service.factory = factory = Mock(FactoryService)
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
