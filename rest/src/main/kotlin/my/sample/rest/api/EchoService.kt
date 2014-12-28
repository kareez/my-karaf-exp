package my.sample.rest.api

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Path("/echo")
@Produces(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON)
interface EchoService {
    /**
     * To echo a message.

     * @return a random message decorated by factory service
     */
    @GET
    @Path("/")
    fun echo(): Response
}
