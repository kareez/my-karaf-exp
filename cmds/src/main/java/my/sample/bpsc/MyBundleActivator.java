package my.sample.bpsc;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class MyBundleActivator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        Bundle bundle = bundleContext.getBundle();
        System.out.println("*** Starting bundle... " + bundle.getSymbolicName());
        System.out.println("bundle.getSymbolicName() = " + bundle.getSymbolicName());
        System.out.println("bundle.getBundleId() = " + bundle.getBundleId());
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        Bundle bundle = bundleContext.getBundle();
        System.out.println("### Stopping bundle... " + bundle.getSymbolicName());
        System.out.println("bundle.getSymbolicName() = " + bundle.getSymbolicName());
        System.out.println("bundle.getBundleId() = " + bundle.getBundleId());
    }
}
