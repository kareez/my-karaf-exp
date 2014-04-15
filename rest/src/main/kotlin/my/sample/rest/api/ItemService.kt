package my.sample.rest.api

<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
import my.sample.model.api.Item;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
=======
import javax.ws.rs.core.Response
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
@Path("/items")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface ItemService {
=======
public trait ItemService {
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt
    /**
     * To get all items.
     *
     * @return all available items
     */
<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
    @GET
    @Path("/")
    List<Item> findAll();
=======
    fun findAll(): List<Item>
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt

    /**
     * To get a specific item.
     *
     * @param id item id
     * @return the item with given id
     */
<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
    @GET
    @Path("/{id}")
    Item find(@PathParam("id") String id);
=======
    fun find(id: String): Item
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt

    /**
     * To update a specific item.
     *
     * @param id   item id
     * @param item updated item
     * @return ok if updated was successful, not modified if item was not found
     */
<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") String id, Item item);
=======
    fun update(id: String, item: Item): Response
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt

    /**
     * To add new item.
     *
     * @param item new item to be added
     * @return ok if add was successful, not modified if item was duplicated
     */
<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
    @POST
    @Path("/")
    Response add(Item item);
=======
    fun add(item: Item): Response
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt

    /**
     * To delete a specific item.
     *
     * @param id item id
     * @return ok if delete was successful, not modified if item was not found
     */
<<<<<<< f2d77d516a7f88d9f97cfef9f422bf876140601a:rest/src/main/java/my/sample/rest/api/ItemService.java
    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") String id);
=======
    fun delete(id: String): Response
>>>>>>> ported rest to kotlin:rest/src/main/kotlin/my/sample/rest/api/ItemService.kt
}