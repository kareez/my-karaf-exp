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

        defaultLogger = new DefaultLogger(service)
    }

    def "debug a message"() {
        when:
        defaultLogger.debug("message")

        then:
        1 * service.log(LogService.LOG_DEBUG, "message")
    }

    def "info a message"() {
        when:
        defaultLogger.info("message")

        then:
        1 * service.log(LogService.LOG_INFO, "message")
    }

    def "warn a message"() {
        when:
        defaultLogger.warn("message")

        then:
        1 * service.log(LogService.LOG_WARNING, "message")
    }

    def "error a message"() {
        when:
        defaultLogger.error("message")

        then:
        1 * service.log(LogService.LOG_ERROR, "message")
    }
}
