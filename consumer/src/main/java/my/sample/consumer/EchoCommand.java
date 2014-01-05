package my.sample.consumer;

import my.sample.provider.SampleService;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.service.log.LogService;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Command(scope = "my", name = "echo", description = "echo command")
public class EchoCommand extends OsgiCommandSupport {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg = null;

    private SampleService service;

    private LogService logger;

    public void setService(SampleService service) {
        this.service = service;
    }

    public void setLogger(LogService logger) {
        this.logger = logger;
    }

    protected Object doExecute() throws Exception {
         log("Executing command: Echo");

         return service.echo(arg == null ? "<not given>" : arg);
    }

    private void log(String message) {
        if (logger != null) {
            logger.log(LogService.LOG_WARNING, message);
        } else {
            System.out.println(message);
        }
    }
}
