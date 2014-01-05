package my.sample.activator

import org.osgi.framework.Bundle
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceReference
import org.osgi.framework.Version
import org.osgi.service.log.LogService
import spock.lang.Specification

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class ActivatorSpec extends Specification {
    def context = Mock(BundleContext)
    def service = Mock(LogService)
    def bundle = Mock(Bundle)

    def setup() {
        bundle.getSymbolicName() >> "bundle-symbolic-name"
        bundle.getVersion() >> new Version("1.0.0")

        context.getServiceReference(_ as String) >> Mock(ServiceReference)
        context.getService(_ as ServiceReference) >> service
        context.getBundle() >> bundle
    }

    def "Start a bundle"() {
        when:
        new Activator().start(context)

        then:
        1 * service.log(LogService.LOG_WARNING, "Starting Bundle: (bundle-symbolic-name: 1.0.0)")
    }

    def "Stop a bundle"() {
        when:
        new Activator().stop(context)

        then:
        1 * service.log(LogService.LOG_WARNING, "Stopping Bundle: (bundle-symbolic-name: 1.0.0)")
    }
}
