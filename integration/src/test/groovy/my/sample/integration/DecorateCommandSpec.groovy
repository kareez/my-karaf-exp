package my.sample.integration

import org.apache.karaf.shell.commands.CommandException
import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerClass

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class DecorateCommandSpec extends BaseKarafSupport {

    @Test
    public void decorate() {
        Object result = executeCommand("my:decorate foo")

        assertTrue(result instanceof List<?>)
        List<?> results = (List<?>) result
        assertTrue(results.contains("### foo ###"))
        assertTrue(results.contains("\"\"\" foo \"\"\""))
        assertTrue(results.contains("*** foo ***"))
    }

    @Test
    public void decorateWithSpacedParameter() {
        Object result = executeCommand("my:decorate 'foo bar'")

        assertTrue(result instanceof List<?>)
        List<?> results = (List<?>) result
        assertTrue(results.contains("### foo bar ###"))
        assertTrue(results.contains("\"\"\" foo bar \"\"\""))
        assertTrue(results.contains("*** foo bar ***"))
    }

    @Test
    public void decorateWithNoParameter() {
        Object result = executeCommand("my:decorate")

        assertTrue(result instanceof List<?>)
        List<?> results = (List<?>) result
        assertTrue(results.contains("### <not given> ###"))
        assertTrue(results.contains("\"\"\" <not given> \"\"\""))
        assertTrue(results.contains("*** <not given> ***"))
    }

    @Test
    public void decorateWithTooManyParameters() {
        Object result = executeCommand("my:decorate foo bar")

        assertTrue(result instanceof CommandException)
        CommandException exception = (CommandException) result

        assertEquals("Too many arguments specified", exception.getMessage())
    }
}
