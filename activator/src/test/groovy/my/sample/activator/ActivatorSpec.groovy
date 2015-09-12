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
        bundle.symbolicName >> "bundle-symbolic-name"
        bundle.version >> new Version("1.0.0")

        context.getServiceReference(_ as String) >> Mock(ServiceReference)
        context.getService(_ as ServiceReference) >> service
        context.bundle >> bundle
    }

    def "start a bundle"() {
        when:
        new Activator().start(context)

        then:
        1 * service.log(LogService.LOG_WARNING, "Starting Bundle: ($bundle.symbolicName: $bundle.version)")
    }

    def "stop a bundle"() {
        when:
        new Activator().stop(context)

        then:
        1 * service.log(LogService.LOG_WARNING, "Stopping Bundle: ($bundle.symbolicName: $bundle.version)")
    }
}
