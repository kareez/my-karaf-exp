package my.sample.integration

import org.apache.karaf.features.BootFinished
import org.apache.karaf.features.FeaturesService
import org.apache.karaf.shell.api.console.Session
import org.apache.karaf.shell.api.console.SessionFactory
import org.junit.Assert
import org.ops4j.pax.exam.Configuration
import org.ops4j.pax.exam.Option
import org.ops4j.pax.exam.ProbeBuilder
import org.ops4j.pax.exam.TestProbeBuilder
import org.ops4j.pax.exam.karaf.options.LogLevelOption
import org.osgi.framework.*
import org.osgi.util.tracker.ServiceTracker

import javax.inject.Inject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

import static org.ops4j.pax.exam.CoreOptions.*
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class BaseKarafSupport {

    static final Long COMMAND_TIMEOUT = 10000L
    static final Long SERVICE_TIMEOUT = 30000L

    ExecutorService executor = Executors.newCachedThreadPool()

    @Inject
    protected BundleContext bundleContext

    @Inject
    protected FeaturesService featuresService

    /**
     * To make sure the tests run only when the boot features are fully installed
     */
    @SuppressWarnings("GroovyUnusedDeclaration")
    @Inject
    BootFinished bootFinished

    @SuppressWarnings(["GrMethodMayBeStatic", "GroovyUnusedDeclaration"])
    @ProbeBuilder
    public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
        probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*,org.apache.felix.service.*;status=provisional")
        return probe
    }

    @SuppressWarnings(["GrMethodMayBeStatic", "GroovyUnusedDeclaration"])
    @Configuration
    public Option[] config() {

        def frameworkUrl = maven()
                .groupId("my.sample")
                .artifactId("distribution")
                .versionAsInProject()
                .type("tar.gz")

        def distribution = karafDistributionConfiguration()
                .frameworkUrl(frameworkUrl)
                .name("Sample Distribution")
                .unpackDirectory(new File("target/exam"))
                .useDeployFolder(false)

        def groovy = mavenBundle()
                .groupId("org.codehaus.groovy")
                .artifactId("groovy-all")
                .versionAsInProject()

        return [
//                debugConfiguration("5005", true),
                distribution,
                keepRuntimeFolder(),
                provision(groovy),
                logLevel(LogLevelOption.LogLevel.ERROR)
        ] as Option[]
    }

    /**
     * Executes a shell command and returns the output.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command the command to execute
     * @return execution result
     */
    protected Object executeCommand(final String command) {
        return executeCommand(command, COMMAND_TIMEOUT, false)
    }

    /**
     * Executes a shell command and returns the output.
     *
     * @param command The command to execute.
     * @param timeout The amount of time in millis to wait for the command to execute.
     * @param silent Specifies if the command should be displayed in the screen.
     * @return execution result
     */
    protected Object executeCommand(final String command, final Long timeout, final Boolean silent) {
        final SessionFactory commandProcessor = getOsgiService(SessionFactory.class)
        final Session commandSession = commandProcessor.create(System.in, System.out, System.err)
        FutureTask<Object> commandFuture = new FutureTask<>({
            try {
                if (!silent) {
                    System.err.println(command)
                }
                return commandSession.execute(command)
            } catch (Exception e) {
                return e
            }
        })

        try {
            executor.submit(commandFuture)
            return commandFuture.get(timeout, TimeUnit.MILLISECONDS)
        } catch (Exception e) {
            return e
        }
    }

    /**
     * Executes a shell command and returns <code>System.out</code> as a String.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command the command to execute
     * @return execution result that is written to <code>System.out</code>
     */
    protected String executeVoidCommand(final String command) {
        return executeVoidCommand(command, COMMAND_TIMEOUT, false)
    }

    /**
     * Executes a shell command and returns <code>System.out</code> as a String.
     *
     * @param command The command to execute.
     * @param timeout The amount of time in millis to wait for the command to execute.
     * @param silent Specifies if the command should be displayed in the screen.
     * @return execution result that is written to <code>System.out</code>
     */
    protected String executeVoidCommand(final String command, final Long timeout, final Boolean silent) {
        String response
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        final PrintStream printStream = new PrintStream(byteArrayOutputStream)
        final SessionFactory commandProcessor = getOsgiService(SessionFactory.class)
        final Session commandSession = commandProcessor.create(System.in, printStream, System.err)
        FutureTask<String> commandFuture = new FutureTask<>({
            try {
                if (!silent) {
                    System.err.println(command)
                }
                commandSession.execute(command)
            } catch (Exception e) {
                e.printStackTrace(System.err)
            }
            printStream.flush()
            return byteArrayOutputStream.toString()
        })

        try {
            executor.submit(commandFuture)
            response = commandFuture.get(timeout, TimeUnit.MILLISECONDS)
        } catch (Exception e) {
            e.printStackTrace(System.err)
            response = "SHELL COMMAND TIMED OUT: "
        }

        return response
    }


    @SuppressWarnings("GroovyUnusedDeclaration")
    protected <T> T getOsgiService(Class<T> type, long timeout) {
        return getOsgiService(type, null, timeout)
    }

    protected <T> T getOsgiService(Class<T> type) {
        return getOsgiService(type, null, SERVICE_TIMEOUT)
    }

    protected <T> T getOsgiService(Class<T> type, String filter, long timeout) {
        try {
            String flt
            if (filter != null) {
                if (filter.startsWith("(")) {
                    flt = "(&(${Constants.OBJECTCLASS}=${type.getName()})$filter)"
                } else {
                    flt = "(&(${Constants.OBJECTCLASS}=${type.getName()})($filter))"
                }
            } else {
                flt = "(${Constants.OBJECTCLASS}=${type.getName()})"
            }
            Filter osgiFilter = FrameworkUtil.createFilter(flt)
            ServiceTracker tracker = new ServiceTracker(bundleContext, osgiFilter, null)
            tracker.open(true)
            // Note that the tracker is not closed to keep the reference
            // This is buggy, as the service reference may change i think
            Object svc = type.cast(tracker.waitForService(timeout))
            if (svc == null) {
                Dictionary dic = bundleContext.getBundle().getHeaders()
                System.err.println("Test bundle headers: ${explode(dic)}")

                for (ServiceReference ref : asCollection(bundleContext.getAllServiceReferences(null, null))) {
                    System.err.println("ServiceReference: $ref")
                }

                for (ServiceReference ref : asCollection(bundleContext.getAllServiceReferences(null, flt))) {
                    System.err.println("Filtered ServiceReference: $ref")
                }

                throw new RuntimeException("Gave up waiting for service $flt")
            }
            return type.cast(svc)
        } catch (InvalidSyntaxException e) {
            throw new IllegalArgumentException("Invalid filter", e)
        } catch (InterruptedException e) {
            throw new RuntimeException(e)
        }
    }

    /**
     * Explode the dictionary into a ,-delimited list of key=value pairs
     */
    @SuppressWarnings("GrMethodMayBeStatic")
    private String explode(Dictionary dictionary) {
        Enumeration keys = dictionary.keys()
        StringBuilder result = new StringBuilder()
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement()
            result.append("$key=${dictionary.get(key)}")
            if (keys.hasMoreElements()) {
                result.append(", ")
            }
        }
        return result.toString()
    }

    /**
     * Provides an iterable collection of references, even if the original array is null
     */
    @SuppressWarnings("GrMethodMayBeStatic")
    private Collection<ServiceReference> asCollection(ServiceReference[] references) {
        return references != null ? Arrays.asList(references) : Collections.<ServiceReference> emptyList()
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    protected void assertFeatureInstalled(String featureName) {
        def feature = featuresService.listInstalledFeatures().find { it.name == featureName }
        if (feature == null) {
            Assert.fail("Feature $featureName should be installed but is not")
        }
    }
}