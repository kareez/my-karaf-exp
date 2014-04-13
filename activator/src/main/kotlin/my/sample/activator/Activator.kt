package my.sample.activator

import org.osgi.framework.Bundle
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceReference
import org.osgi.service.log.LogService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class Activator : BundleActivator {
    override fun start(bundleContext: BundleContext?) {
        val context = bundleContext!!
        log(context, ">>> Starting Bundle: (${getInfo(context.getBundle()!!)})")
    }

    override fun stop(bundleContext: BundleContext?) {
        val context = bundleContext!!
        log(context, ">>> Stopping Bundle: (${getInfo(context.getBundle()!!)})")
    }

    private fun log(context: BundleContext, message: String) {
        val reference: ServiceReference<LogService>? =
                context.getServiceReference(javaClass<LogService>().getName()) as ServiceReference<LogService>?

        if (reference != null) {
            val logger = context.getService(reference)
            logger?.log(LogService.LOG_WARNING, message)
        } else {
            println(message)
        }
    }

    private fun getInfo(bundle: Bundle): String {
        return bundle.getSymbolicName() + ": " + bundle.getVersion().toString()
    }
}
