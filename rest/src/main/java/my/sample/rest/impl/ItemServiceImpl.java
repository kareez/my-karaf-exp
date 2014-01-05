package my.sample.rest.impl;

import my.sample.rest.api.Item;
import my.sample.rest.api.ItemService;
import org.osgi.service.log.LogService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ItemServiceImpl implements ItemService {
    private ItemRepository repository = new ItemRepository();

    private LogService logger;

    public void setLogger(LogService logger) {
        this.logger = logger;
    }

    private void log(String message) {
        if (logger != null) {
            logger.log(LogService.LOG_WARNING, message);
        } else {
            System.out.println(message);
        }
    }

    @Override
    @GET
    @Path("/")
    public List<Item> findAll() {
        log("GET:: findAll");
        return repository.get();
    }

    @Override
    @GET
    @Path("/{id}")
    public Item find(@PathParam("id") String id) {
        log("GET:: find/" + id);
        return repository.get(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, Item item) {
        log("PUT:: update/" + id);
        item.setId(id);

        try {
            Item updated = repository.update(item);
            return Response.ok(updated).build();

        } catch (IllegalArgumentException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @Override
    @POST
    @Path("/")
    public Response add(Item item) {
        log("POST:: add/" + item.getId());

        try {
            Item added = repository.add(item);
            return Response.ok(added).build();

        } catch (IllegalArgumentException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @Override
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        log("DELETE:: delete/" + id);

        try {
            Item item = repository.delete(id);
            return Response.ok(item).build();

        } catch (IllegalArgumentException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }
}