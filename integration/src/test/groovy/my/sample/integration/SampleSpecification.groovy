package my.sample.integration

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerClass

import javax.management.MBeanServerConnection
import javax.management.ObjectName
import javax.management.openmbean.TabularData
import javax.management.remote.JMXConnector

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@Ignore
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class SampleSpecification extends BaseKarafSupport {

    @Test
    public void listCommand() throws Exception {
        String listOutput = executeVoidCommand("feature:list")
        println(listOutput)
        assertFalse(listOutput.isEmpty())

        listOutput = executeVoidCommand("feature:list -i")
        println(listOutput)
        assertFalse(listOutput.isEmpty())
    }

    @Test
    public void listViaMBean() throws Exception {
        JMXConnector connector = null
        try {
            connector = this.getJMXConnector()
            MBeanServerConnection connection = connector.getMBeanServerConnection()
            ObjectName name = new ObjectName("org.apache.karaf:type=feature,name=root")
            TabularData features = (TabularData) connection.getAttribute(name, "Features")
            assertTrue(features.size() > 0)
        } finally {
            if (connector != null)
                connector.close()
        }
    }
}
