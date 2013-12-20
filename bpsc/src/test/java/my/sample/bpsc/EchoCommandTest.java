
package my.sample.bpsc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Test cases for {@link EchoCommand}
 */
@SuppressWarnings("unchecked")
public class EchoCommandTest {

    private EchoCommand command;

    @Before
    public void setup() {
        command = new EchoCommand();
    }

    @Ignore
    @Test
    public void execute() throws Exception {
        Object object = command.execute(null);

        assertTrue(object != null);
        assertTrue(object instanceof String[]);
        String[] result = (String[]) object;

        assertEquals("A...", result[0]);
        assertEquals("B...", result[1]);
        assertEquals("C...", result[2]);
    }
}
