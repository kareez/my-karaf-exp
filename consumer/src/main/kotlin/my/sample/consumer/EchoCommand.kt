package my.sample.consumer

import my.sample.activator.SampleLogger
import my.sample.provider.SampleService
import org.apache.felix.gogo.commands.Argument as argument
import org.apache.felix.gogo.commands.Command as command
import org.apache.karaf.shell.console.OsgiCommandSupport


/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
command(scope = "my", name = "echo", description = "echo command")
public class EchoCommand : OsgiCommandSupport() {

    argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    var arg: String? = null

    var service: SampleService? = null

    var logger: SampleLogger? = null

    override fun doExecute(): Any? {
        logger?.warn("Executing command: Echo")

        return service?.echo(if (arg != null) arg else "<not given>")
    }
}
