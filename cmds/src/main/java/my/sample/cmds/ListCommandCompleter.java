package my.sample.cmds;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

import java.util.List;

/**
 * @author Mohammad Shamsi <mohammad.shamsi@zimory.com>
 */
public class ListCommandCompleter implements Completer {

    @Override
    public int complete(String buffer, int cursor, List candidates) {
        StringsCompleter delegate = new StringsCompleter();

        delegate.getStrings().add("one");
        delegate.getStrings().add("two");
        delegate.getStrings().add("three");
        delegate.getStrings().add("four");
        delegate.getStrings().add("five");

        return delegate.complete(buffer, cursor, candidates);
    }
}
