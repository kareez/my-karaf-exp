package my.sample.consumer

import my.sample.provider.SampleDecorator
import org.apache.felix.gogo.commands.Argument as argument
import org.apache.felix.gogo.commands.Command as command
import org.apache.karaf.shell.console.OsgiCommandSupport

import java.util.stream.Collectors

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
command(scope = "my", name = "decorate", description = "decorator command")
public class DecoratorCommand : OsgiCommandSupport() {

    argument(index = 0, name = "arg", description = "message to decorate", required = false, multiValued = false)
    var arg: String? = null

    var decorators: List<SampleDecorator>? = null

    override fun doExecute(): Any? {

        if (decorators == null || decorators?.size() == 0) {
            return "<no decorator found>"
        }

        val param = if (arg == null) "<not given>" else arg

        return decorators?.map { it.decorate(param) }?.toArrayList()
    }
}
