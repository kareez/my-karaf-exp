package my.sample.consumer;

import my.sample.provider.SampleDecorator;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Service
@Command(scope = "my", name = "decorate", description = "decorator command")
public class DecoratorCommand implements Action {

    @Argument(index = 0, name = "arg", description = "message to decorate", required = false, multiValued = false)
    private String arg = null;

    @Reference
    private List<SampleDecorator> decorators;

    public void setDecorators(List<SampleDecorator> decorators) {
        this.decorators = decorators;
    }

    @Override
    public Object execute() throws Exception {

        if (decorators == null || decorators.size() == 0) {
            return "<no decorator found>";
        }

        String param = arg == null ? "<not given>" : arg;

        return decorators.stream().map(decorator -> decorator.decorate(param)).collect(Collectors.toList());
    }
}
