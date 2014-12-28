package my.sample.rest.impl

import my.sample.activator.Logger
import my.sample.factory.FactoryService
import my.sample.rest.api.EchoService
import java.util.*
import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class EchoServiceImpl : EchoService {
    lateinit var factory: FactoryService
    lateinit var logger: Logger

    override fun echo(): Response {
        logger.warn("GET:: echo")

        return Response.ok(factory.echo(Date().toString())).build()
    }
}