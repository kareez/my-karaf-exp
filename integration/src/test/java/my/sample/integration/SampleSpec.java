package my.sample.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.TabularData;
import javax.management.remote.JMXConnector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SampleSpec extends BaseKarafSupport {

    @Test
    public void listCommand() throws Exception {
        String listOutput = executeCommand("features:list");
        System.out.println(listOutput);
        assertFalse(listOutput.isEmpty());
        listOutput = executeCommand("features:list -i");
        System.out.println(listOutput);
        assertFalse(listOutput.isEmpty());
    }

    @Test
    public void listViaMBean() throws Exception {
        JMXConnector connector = null;
        try {
            connector = this.getJMXConnector();
            MBeanServerConnection connection = connector.getMBeanServerConnection();
            ObjectName name = new ObjectName("org.apache.karaf:type=features,name=root");
            TabularData features = (TabularData) connection.getAttribute(name, "Features");
            assertTrue(features.size() > 0);
        } finally {
            if (connector != null)
                connector.close();
        }
    }

    @Test
    public void repoAddRemoveCommand() throws Exception {
        String featureUrl = "mvn:my.sample/feature/1.1-SNAPSHOT/xml/features";

        //Add feature repository
        System.out.println(executeCommand("features:addurl " + featureUrl));
        String repoListOutput = executeCommand("features:listurl");
        System.out.println(repoListOutput);
        assertTrue(repoListOutput.contains(featureUrl));

        //Install feature
        String featureInstallOutput = executeCommand("features:install -v feature", 3 * 60 * 1000L, false);
        System.out.println(featureInstallOutput);
        assertFalse(featureInstallOutput.isEmpty());

        //Check if feature is installed
        String listOutput = executeCommand("features:list -i");
        System.out.println(listOutput);
        assertTrue(listOutput.contains("feature"));

        //Check bundles
        String bundlesOutput = executeCommand("osgi:list");
        System.out.println(bundlesOutput);
    }
}
