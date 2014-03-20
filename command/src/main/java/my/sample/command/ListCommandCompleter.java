package my.sample.command;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mohammad Shamsi <mohammad.shamsi@zimory.com>
 */
public class ListCommandCompleter implements Completer {

    @Override
    public int complete(String buffer, int cursor, List candidates) {
        StringsCompleter delegate = new StringsCompleter();

        Arrays.asList("one", "two", "three", "four", "five")
                .forEach(delegate.getStrings()::add);

        return delegate.complete(buffer, cursor, candidates);
    }
}
