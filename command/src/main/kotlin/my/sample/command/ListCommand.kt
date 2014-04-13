package my.sample.command

import my.sample.activator.SampleLogger
import org.apache.felix.gogo.commands.Argument as argument
import org.apache.felix.gogo.commands.Command as command
import org.apache.karaf.shell.console.OsgiCommandSupport


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
command(scope = "my", name = "list", description = "a sample command")
public class ListCommand : OsgiCommandSupport() {

    argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    var arg: String? = null

    var logger: SampleLogger? = null


    override fun doExecute(): Any? {
        logger?.warn("Executing command: ListCommand")

        return array("Given parameter: ", if (arg != null) arg else "<not given>")
    }
}
