package my.sample.bactv;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class MyBundleActivator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("\nStarting Bundle: (" + getInfo(bundleContext.getBundle()) + ")\n");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("\nStopping Bundle: (" + getInfo(bundleContext.getBundle()) + ")\n");
    }

    private String getInfo(Bundle bundle) {
        return bundle.getSymbolicName() + ": " + bundle.getVersion().toString();
    }
}