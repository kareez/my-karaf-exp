package my.sample.integration

import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerClass

import static org.junit.Assert.assertFalse

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class SampleSpecification extends BaseKarafSupport {

    @Test
    public void listCommand() throws Exception {
        String listOutput = executeVoidCommand("feature:list")
        assertFalse(listOutput.isEmpty())

        listOutput = executeVoidCommand("feature:list -i")
        assertFalse(listOutput.isEmpty())
    }
}
