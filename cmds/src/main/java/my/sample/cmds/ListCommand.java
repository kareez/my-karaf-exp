package my.sample.cmds;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;


/**
 * Displays the last log entries
 */
@Command(scope = "my", name = "list", description = "a sample command")
public class ListCommand extends OsgiCommandSupport {

    @Argument(index = 0, name = "arg", description = "a sample argument", required = false, multiValued = false)
    private String arg;

    protected Object doExecute() throws Exception {
         System.out.println("Executing command: ListCommand");

         return new String[] {"A...", "B...", "C...", arg == null ? "no argument" : arg};
    }
}
