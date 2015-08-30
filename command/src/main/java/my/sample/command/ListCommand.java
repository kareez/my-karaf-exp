package my.sample.command;

import my.sample.activator.SampleLogger;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Service
@Command(scope = "my", name = "list", description = "a sample command")
public class ListCommand implements Action {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    @Completion(ListCommandCompleter.class)
    private String arg = null;

    @Reference
    private SampleLogger logger;

    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

    @Override
    public Object execute() throws Exception {
        logger.warn("Executing command: ListCommand");

        return new String[]{"Given parameter: ", arg == null ? "<not given>" : arg};
    }
}
