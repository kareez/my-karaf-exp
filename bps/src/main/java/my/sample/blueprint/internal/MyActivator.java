package my.sample.blueprint.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class MyActivator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting Bundle: " + bundleContext.getBundle().getSymbolicName());
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping Bundle: " + bundleContext.getBundle().getSymbolicName());
    }
}
