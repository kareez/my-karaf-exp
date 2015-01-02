package my.sample.integration;

import org.apache.karaf.shell.commands.CommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ListCommandSpec extends BaseKarafSupport {

    @Test
    public void list() {
        Object result = executeCommand("my:list foo");

        assertTrue(result instanceof String[]);
        String[] results = (String[]) result;

        assertEquals("Given parameter: ", results[0]);
        assertEquals("foo", results[1]);
    }

    @Test
    public void listWithSpacedParameter() {
        Object result = executeCommand("my:list 'foo bar'");

        assertTrue(result instanceof String[]);
        String[] results = (String[]) result;

        assertEquals("Given parameter: ", results[0]);
        assertEquals("foo bar", results[1]);
    }

    @Test
    public void listWithNoParameter() {
        Object result = executeCommand("my:list");

        assertTrue(result instanceof String[]);
        String[] results = (String[]) result;

        assertEquals("Given parameter: ", results[0]);
        assertEquals("<not given>", results[1]);
    }

    @Test
    public void listWithTooManyParameters() {
        Object result = executeCommand("my:list foo bar");

        assertTrue(result instanceof CommandException);
        CommandException exception = (CommandException) result;

        assertEquals("Too many arguments specified", exception.getMessage());
    }
}
