package my.sample.activator

import org.osgi.framework.Bundle
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.service.log.LogService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class Activator : BundleActivator {
    override fun start(context: BundleContext) = log(context, "Starting Bundle: (${getInfo(context.bundle)})")

    override fun stop(context: BundleContext) = log(context, "Stopping Bundle: (${getInfo(context.bundle)})")

    private fun getInfo(bundle: Bundle) = "${bundle.symbolicName}: ${bundle.version.toString()}"

    private fun log(context: BundleContext, message: String) {
        val reference = context.getServiceReference(LogService::class.java.name)

        if (reference != null) {
            val logger = context.getService(reference) as LogService
            logger.log(LogService.LOG_WARNING, message)
        } else {
            println(message)
        }
    }
}
