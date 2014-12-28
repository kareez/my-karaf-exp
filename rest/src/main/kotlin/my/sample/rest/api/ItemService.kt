package my.sample.rest.api

import my.sample.model.api.Item
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Path("/items")
@Produces(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON)
interface ItemService {
    /**
     * To get all items.
     *
     * @return all available items
     */
    @GET
    @Path("/")
    fun findAll(): List<Item>

    /**
     * To get a specific item.
     *
     * @param id item id
     * @return the item with given id
     */
    @GET
    @Path("/{id}")
    fun find(@PathParam("id") id: String): Item

    /**
     * To update a specific item.
     *
     * @param id   item id
     * @param item updated item
     * @return ok if updated was successful, not modified if item was not found
     */
    @PUT
    @Path("/{id}")
    fun update(@PathParam("id") id: String, item: Item): Response

    /**
     * To add new item.
     *
     * @param item new item to be added
     * @return ok if add was successful, not modified if item was duplicated
     */
    @POST
    @Path("/")
    fun add(item: Item): Response

    /**
     * To delete a specific item.
     *
     * @param id item id
     * @return ok if delete was successful, not modified if item was not found
     */
    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: String): Response
}