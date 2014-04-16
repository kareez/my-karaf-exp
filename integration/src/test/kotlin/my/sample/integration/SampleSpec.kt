package my.sample.integration

import org.junit.Test as test
import org.junit.runner.RunWith as runWith
import org.ops4j.pax.exam.junit.ExamReactorStrategy as examReactorStrategy
import org.ops4j.pax.exam.junit.JUnit4TestRunner
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory

import javax.management.ObjectName
import javax.management.openmbean.TabularData
import javax.management.remote.JMXConnector
import org.junit.Assert


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

runWith(javaClass<JUnit4TestRunner>())
examReactorStrategy(javaClass<AllConfinedStagedReactorFactory>())
public class SampleSpec : BaseKarafSupport() {

    test
    public fun listCommand() {
        var listOutput = executeCommand("features:list")
        System.out.println(listOutput)
        Assert.assertFalse(listOutput.isEmpty())
        listOutput = executeCommand("features:list -i")
        println(listOutput)
        Assert.assertFalse(listOutput.isEmpty())
    }

    test
    public fun listViaMBean() {
        var connector: JMXConnector? = null
        try {
            connector = this.getJMXConnector()
            val connection = connector?.getMBeanServerConnection()
            val name = ObjectName("org.apache.karaf:type=features,name=root")
            val features = connection?.getAttribute(name, "Features") as TabularData
            Assert.assertTrue(features.size() > 0)
        } finally {
            if (connector != null)
                connector?.close()
        }
    }

    test
    public fun repoAddRemoveCommand() {
        val featureUrl = "mvn:my.sample/feature/1.1-SNAPSHOT/xml/features"

        //Add feature repository
        println(executeCommand("features:addurl $featureUrl"))
        val repoListOutput = executeCommand("features:listurl")
        println(repoListOutput)
        Assert.assertTrue(repoListOutput.contains(featureUrl))

        //Install feature
        val featureInstallOutput = executeCommand("features:install -v feature", 3 * 60 * 1000L, false)
        println(featureInstallOutput)
        Assert.assertFalse(featureInstallOutput.isEmpty())

        //Check if feature is installed
        val listOutput = executeCommand("features:list -i")
        println(listOutput)
        Assert.assertTrue(listOutput.contains("feature"))

        //Check bundles
        val bundlesOutput = executeCommand("osgi:list")
        println(bundlesOutput)
    }
}
