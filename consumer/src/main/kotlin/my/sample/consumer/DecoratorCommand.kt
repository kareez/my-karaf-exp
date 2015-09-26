package my.sample.consumer

import my.sample.provider.Decorator
import org.apache.karaf.shell.api.action.Action
import org.apache.karaf.shell.api.action.Argument
import org.apache.karaf.shell.api.action.Command
import org.apache.karaf.shell.api.action.lifecycle.Reference
import org.apache.karaf.shell.api.action.lifecycle.Service
import java.util.*

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Service
@Command(scope = "my", name = "decorate", description = "decorator command")
public class DecoratorCommand : Action {

    @Argument(index = 0, name = "arg", description = "message to decorate", required = false, multiValued = false)
    private var arg: String? = null

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Reference
    lateinit var decorators: java.util.List<Decorator>

    @Throws(Exception::class)
    override fun execute(): Any {
        val result = ArrayList<String>()
        for (decorator in decorators) {
            result.add(decorator.decorate(arg ?: "<not given>"))
        }
        return result
    }
}
