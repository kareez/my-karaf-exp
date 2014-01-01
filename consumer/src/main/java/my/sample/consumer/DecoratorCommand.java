package my.sample.consumer;

import my.sample.provider.SampleDecorator;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Command(scope = "my", name = "decorate", description = "decorator command")
public class DecoratorCommand extends OsgiCommandSupport {

    @Argument(index = 0, name = "arg", description = "message to decorate", required = false, multiValued = false)
    private String arg = null;

    private List<SampleDecorator> decorators;

    public void setDecorators(List<SampleDecorator> decorators) {
        this.decorators = decorators;
    }

    protected Object doExecute() throws Exception {
        int size = decorators != null ? decorators.size() : 0;
        String[] results = new String[size];

        int i = 0;
        for (SampleDecorator decorator : decorators) {
            results[i++] = decorator.decorate(arg == null ? "<not given>" : arg);
        }
        return results;
    }
}
