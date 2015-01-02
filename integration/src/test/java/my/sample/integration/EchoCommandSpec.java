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
public class EchoCommandSpec extends BaseKarafSupport {

    @Test
    public void list() {
        Object result = executeCommand("my:echo foo");

        assertTrue(result instanceof String);
        assertEquals("Echo processed: foo", result);
    }

    @Test
    public void listWithNoParameter() {
        Object result = executeCommand("my:echo");

        assertTrue(result instanceof String);
        assertEquals("Echo processed: <not given>", result);
    }

    @Test
    public void listWithTooManyParameters() {
        Object result = executeCommand("my:echo foo bar");

        assertTrue(result instanceof CommandException);
        CommandException exception = (CommandException) result;

        assertEquals("Too many arguments specified", exception.getMessage());
    }
}
