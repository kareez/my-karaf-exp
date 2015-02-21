package my.sample.consumer;

import my.sample.provider.SampleDecorator;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import java.util.ArrayList;
import java.util.List;

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

        List<String> result = new ArrayList<>(decorators.size());
        for (SampleDecorator decorator : decorators) {
            result.add(decorator.decorate(param));
        }
        return result;
    }
}
