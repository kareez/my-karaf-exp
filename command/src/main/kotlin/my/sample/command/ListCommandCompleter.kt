package my.sample.command

import org.apache.karaf.shell.console.Completer
import org.apache.karaf.shell.console.completer.StringsCompleter

import java.util.Arrays

/**
 * @author Mohammad Shamsi <mohammad.shamsi@zimory.com>
 */
public class ListCommandCompleter : Completer {

    override fun complete(buffer: String?, cursor: Int, candidates: List<String>?): Int {
        val delegate = StringsCompleter()
        val strings = delegate.getStrings()!!

        strings.add("one")
        strings.add("two")
        strings.add("three")
        strings.add("four")
        strings.add("five")

        return delegate.complete(buffer, cursor, candidates)
    }
}
