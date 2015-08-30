package my.sample.consumer;

import my.sample.activator.SampleLogger;
import my.sample.provider.SampleService;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Service
@Command(scope = "my", name = "echo", description = "echo command")
public class EchoCommand implements Action {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg = null;

    @Reference
    private SampleService service;

    @Reference
    private SampleLogger logger;

    public void setService(SampleService service) {
        this.service = service;
    }

    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

    @Override
    public Object execute() throws Exception {
        logger.warn("Executing command: Echo");

        return service.echo(arg == null ? "<not given>" : arg);
    }
}
