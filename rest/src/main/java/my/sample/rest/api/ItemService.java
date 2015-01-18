package my.sample.rest.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface ItemService {
    /**
     * To get all items.
     *
     * @return all available items
     */
    @GET
    @Path("/")
    List<Item> findAll();

    /**
     * To get a specific item.
     *
     * @param id item id
     * @return the item with given id
     */
    @GET
    @Path("/{id}")
    Item find(@PathParam("id") String id);

    /**
     * To update a specific item.
     *
     * @param id   item id
     * @param item updated item
     * @return ok if updated was successful, not modified if item was not found
     */
    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") String id, Item item);

    /**
     * To add new item.
     *
     * @param item new item to be added
     * @return ok if add was successful, not modified if item was duplicated
     */
    @POST
    @Path("/")
    Response add(Item item);

    /**
     * To delete a specific item.
     *
     * @param id item id
     * @return ok if delete was successful, not modified if item was not found
     */
    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") String id);
}