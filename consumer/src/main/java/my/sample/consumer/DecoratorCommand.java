package my.sample.consumer;

import my.sample.provider.SampleDecorator;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import java.util.List;
import java.util.stream.Collectors;

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

        if (decorators == null || decorators.size() == 0) {
            return "<no decorator found>";
        }

        String param = arg == null ? "<not given>" : arg;

        return decorators.stream().map(decorator -> decorator.decorate(param)).collect(Collectors.toList());
    }
}
