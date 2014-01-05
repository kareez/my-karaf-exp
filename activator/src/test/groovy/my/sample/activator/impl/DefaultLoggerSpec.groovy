package my.sample.activator.impl

import org.osgi.service.log.LogService
import spock.lang.Specification

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class DefaultLoggerSpec extends Specification {
    DefaultLogger defaultLogger
    LogService service

    def setup() {
        service = Mock(LogService)

        defaultLogger = new DefaultLogger()
        defaultLogger.logger = service
    }

    def "call debug"() {
        when:
        defaultLogger.debug("message")

        then:
        1 * service.log(LogService.LOG_DEBUG, "message")
    }

    def "call info"() {
        when:
        defaultLogger.info("message")

        then:
        1 * service.log(LogService.LOG_INFO, "message")
    }

    def "call warn"() {
        when:
        defaultLogger.warn("message")

        then:
        1 * service.log(LogService.LOG_WARNING, "message")
    }

    def "call error"() {
        when:
        defaultLogger.error("message")

        then:
        1 * service.log(LogService.LOG_ERROR, "message")
    }
}
