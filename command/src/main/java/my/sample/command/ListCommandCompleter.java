package my.sample.command;

import org.apache.karaf.shell.api.console.CommandLine;
import org.apache.karaf.shell.api.console.Completer;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.support.completers.StringsCompleter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohammad Shamsi <mohammad.shamsi@zimory.com>
 */
public class ListCommandCompleter implements Completer {

    @Override
    public int complete(Session session, CommandLine commandLine, List<String> candidates) {
        StringsCompleter delegate = new StringsCompleter();

        Arrays.asList("one", "two", "three", "four", "five").forEach(delegate.getStrings()::add);

        return delegate.complete(session, commandLine, candidates);
    }
}
