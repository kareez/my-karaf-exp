package my.sample.command;

import my.sample.activator.SampleLogger;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Command(scope = "my", name = "list", description = "a sample command")
public class ListCommand extends OsgiCommandSupport {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg = null;

    private SampleLogger logger;

    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

    protected Object doExecute() throws Exception {
        logger.warn("Executing command: ListCommand");

        return new String[]{"Given parameter: ", arg == null ? "<not given>" : arg};
    }
}
