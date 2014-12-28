package my.sample.factory.impl

import my.sample.activator.Logger
import my.sample.factory.FactoryService
import java.util.*

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultFactoryService : FactoryService {
    lateinit var logger: Logger

    private val config: MutableMap<String, Any>

    init {
        this.config = HashMap<String, Any>()
    }

    public fun init() {
        logger.info("Initializing DefaultFactoryService: $this")
    }

    public fun update(config: Map<String, Any>) {
        this.config.clear()
        this.config.putAll(config)

        logger.info("updating....: $this")
    }

    public fun destroy() {
        logger.info("Destroying DefaultFactoryService: $this")
    }

    override fun echo(message: String): String = "Echo processed: $message"

    override fun toString(): String {
        val builder = StringBuilder("{")
        for (key in config.keySet()) {
            builder.append("\n\t").append(key).append(": ").append(config.get(key))
        }
        builder.append("\n").append("}")

        return builder.toString()
    }
}