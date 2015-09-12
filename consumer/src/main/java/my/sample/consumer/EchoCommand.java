package my.sample.consumer;

import my.sample.activator.Logger;
import my.sample.provider.Service;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@org.apache.karaf.shell.api.action.lifecycle.Service
@Command(scope = "my", name = "echo", description = "echo command")
public class EchoCommand implements Action {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg = null;

    @Reference
    private Service service;

    @Reference
    private Logger logger;

    @Override
    public Object execute() throws Exception {
        logger.warn("Executing command: Echo");

        return service.echo(arg == null ? "<not given>" : arg);
    }
}
