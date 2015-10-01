package my.sample.command

import my.sample.activator.Logger
import org.apache.karaf.shell.api.action.Action
import org.apache.karaf.shell.api.action.Argument
import org.apache.karaf.shell.api.action.Command
import org.apache.karaf.shell.api.action.Completion
import org.apache.karaf.shell.api.action.lifecycle.Reference
import org.apache.karaf.shell.api.action.lifecycle.Service


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Service
@Command(scope = "my", name = "list", description = "a sample command")
public class ListCommand : Action {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    @Completion(ListCommandCompleter::class)
    private var arg: String? = null

    @Reference
    private lateinit var logger: Logger

    @Throws(Exception::class)
    override fun execute(): Any {
        logger.warn("Executing command: ListCommand")

        return arrayOf("Given parameter: ", arg ?: "<not given>")
    }
}
