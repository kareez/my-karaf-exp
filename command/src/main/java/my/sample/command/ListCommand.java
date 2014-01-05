package my.sample.command;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.service.log.LogService;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Command(scope = "my", name = "list", description = "a sample command")
public class ListCommand extends OsgiCommandSupport {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg = null;

    private LogService logger;

    public void setLogger(LogService logger) {
        this.logger = logger;
    }

    protected Object doExecute() throws Exception {
         log("Executing command: ListCommand");

         return new String[] {"Given parameter: ", arg == null ? "<not given>" : arg};
    }

    private void log(String message) {
        if (logger != null) {
            logger.log(LogService.LOG_WARNING, message);
        } else {
            System.out.println(message);
        }
    }
}
