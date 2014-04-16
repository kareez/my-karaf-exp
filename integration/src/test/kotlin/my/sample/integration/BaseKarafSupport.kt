package my.sample.integration

import org.apache.felix.service.command.CommandProcessor
import org.apache.karaf.features.FeaturesService
import org.apache.karaf.tooling.exam.options.LogLevelOption
import org.junit.Assert
import org.ops4j.pax.exam.MavenUtils
import org.ops4j.pax.exam.Option
import org.ops4j.pax.exam.TestProbeBuilder
import org.ops4j.pax.exam.junit.Configuration as configuration
import org.ops4j.pax.exam.junit.ProbeBuilder as probeBuilder
import org.osgi.framework.*
import org.osgi.util.tracker.ServiceTracker

import javax.inject.Inject as inject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit
import org.apache.karaf.tooling.exam.options.KarafDistributionOption
import org.ops4j.pax.exam.CoreOptions
import org.osgi.util.tracker.ServiceTrackerCustomizer
import java.util.concurrent.Callable
import javax.management.remote.JMXConnector
import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public open class BaseKarafSupport {
    class object {
        val COMMAND_TIMEOUT = 10000L
        val SERVICE_TIMEOUT = 30000L
    }

    val executor = Executors.newCachedThreadPool()

    inject
    var bundleContext: BundleContext? = null

    inject
    var featuresService: FeaturesService? = null

    probeBuilder
    public fun probeConfiguration(probe: TestProbeBuilder): TestProbeBuilder {
        probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*,org.apache.felix.service.*status=provisional")
        return probe
    }

    configuration
    public fun config(): Array<Option> {
        return array<Option>(
                KarafDistributionOption.karafDistributionConfiguration()
                ?.frameworkUrl(CoreOptions
                        .maven()
                ?.groupId("org.apache.karaf")
                ?.artifactId("apache-karaf")
                ?.versionAsInProject()
                ?.`type`("tar.gz"))
                ?.karafVersion(MavenUtils.getArtifactVersion("org.apache.karaf", "apache-karaf"))
                ?.name("Apache Karaf")
                ?.unpackDirectory(File("target/exam"))!!,
                KarafDistributionOption.logLevel(LogLevelOption.LogLevel.ERROR)!!
        )
    }

    /**
     * Executes a shell command and returns output as a String.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command the command to execute
     * @return execution result
     */
    protected fun executeCommand(command: String): String {
        return executeCommand(command, COMMAND_TIMEOUT, false)
    }

    /**
     * Executes a shell command and returns output as a String.
     * Commands have a default timeout of 10 seconds.
     *
     * @param command The command to execute.
     * @param timeout The amount of time in millis to wait for the command to execute.
     * @param silent  Specifies if the command should be displayed in the screen.
     * @return execution result
     */
    protected fun executeCommand(command: String, timeout: Long, silent: Boolean): String {
        var response: String = ""
        val byteArrayOutputStream = ByteArrayOutputStream()
        val printStream = PrintStream(byteArrayOutputStream)
        val commandProcessor = getOsgiService(javaClass<CommandProcessor>())
        val commandSession = commandProcessor.createSession(System.`in`, printStream, System.err)
        val commandFuture = FutureTask<String>(object : Callable<String> {
            override fun call(): String? {
                try {
                    if (!silent) {
                        System.err.println(command)
                    }
                    commandSession?.execute(command)
                } catch (e: Exception) {
                    e.printStackTrace(System.err)
                }
                printStream.flush()
                return byteArrayOutputStream.toString()
            }
        }
        )

        try {
            executor.submit(commandFuture)
            response = commandFuture.get(timeout, TimeUnit.MILLISECONDS)
        } catch (e: Exception) {
            e.printStackTrace(System.err)
            response = "SHELL COMMAND TIMED OUT: "
        }

        return response
    }


    protected fun getOsgiService<T>(tipe: Class<T>, timeout: Long): T {
        return getOsgiService(tipe, null, timeout)
    }

    protected fun getOsgiService<T>(tipe: Class<T>): T {
        return getOsgiService(tipe, null, SERVICE_TIMEOUT)
    }

    protected fun getOsgiService<T>(tipe: Class<T>, filter: String?, timeout: Long): T {
        try {
            var flt: String? = null
            if (filter != null) {
                if (filter.startsWith("(")) {
                    flt = "(&(" + Constants.OBJECTCLASS + "=" + tipe.getName() + ")" + filter + ")"
                } else {
                    flt = "(&(" + Constants.OBJECTCLASS + "=" + tipe.getName() + ")(" + filter + "))"
                }
            } else {
                flt = "(" + Constants.OBJECTCLASS + "=" + tipe.getName() + ")"
            }
            val osgiFilter = FrameworkUtil.createFilter(flt)
            var x: ServiceTrackerCustomizer<T, T>? = null
            val tracker = ServiceTracker(bundleContext, osgiFilter, x)
            tracker.open(true)
            // Note that the tracker is not closed to keep the reference
            // This is buggy, as the service reference may change i think
            val svc = tipe.cast(tracker.waitForService(timeout))
            if (svc == null) {
                val dic = bundleContext?.getBundle()?.getHeaders()!!
                println("Test bundle headers: ${explode(dic)}")

                for (ref in asCollection(bundleContext?.getAllServiceReferences(null, null))) {
                    println("ServiceReference: $ref")
                }

                for (ref in asCollection(bundleContext?.getAllServiceReferences(null, flt))) {
                    println("Filtered ServiceReference: $ref")
                }

                throw RuntimeException("Gave up waiting for service $flt")
            }
            return tipe.cast(svc)!!
        } catch (e: InvalidSyntaxException) {
            throw IllegalArgumentException("Invalid filter", e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    /*
    * Explode the dictionary into a ,-delimited list of key=value pairs
    */
    private fun explode(dictionary: Dictionary<*, *>): String {
        val keys = dictionary.keys()

        val result = StringBuilder()
        while (keys.hasMoreElements()) {
            val key = keys.nextElement()
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
    private fun asCollection(references: Array<ServiceReference<*>>?): Collection<ServiceReference<*>> {
        return if (references != null) {
            references.toArrayList();
        } else {
            arrayListOf<ServiceReference<*>>()
        }
    }

    protected fun getJMXConnector(): JMXConnector {
        val env = hashMapOf<String, Any>()
        env.put("jmx.remote.credentials", array("karaf", "karaf"))

        return JMXConnectorFactory
                .connect(JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/karaf-root"), env)
    }

    protected fun assertFeatureInstalled(featureName: String) {
        val features = featuresService?.listInstalledFeatures()!!
        for (feature in features) {
            if (featureName.equals(feature.getName())) {
                return
            }
        }
        Assert.fail("Feature " + featureName + " should be installed but is not")
    }
}