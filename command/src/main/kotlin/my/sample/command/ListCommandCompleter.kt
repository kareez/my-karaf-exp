package my.sample.command

import org.apache.karaf.shell.api.console.CommandLine
import org.apache.karaf.shell.api.console.Completer
import org.apache.karaf.shell.api.console.Session
import org.apache.karaf.shell.support.completers.StringsCompleter

/**
 * @author Mohammad Shamsi <mohammad.shamsi@zimory.com>
 */
public class ListCommandCompleter : Completer {

    override fun complete(session: Session, commandLine: CommandLine, candidates: List<String>): Int {
        val delegate = StringsCompleter()

        listOf("one", "two", "three", "four", "five").forEach { delegate.strings.add(it) }

        return delegate.complete(session, commandLine, candidates)
    }
}
