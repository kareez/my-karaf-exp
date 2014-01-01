package my.sample.consumer;

import my.sample.provider.SampleService;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Command(scope = "my", name = "echo", description = "echo command")
public class EchoCommand extends OsgiCommandSupport {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg = null;

    private SampleService service;

    public void setService(SampleService service) {
        this.service = service;
    }

    protected Object doExecute() throws Exception {
         System.out.println("Executing command: Echo");

         return service.echo(arg == null ? "<not given>" : arg);
    }
}
