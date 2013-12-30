package my.sample.bpre.impl;

import my.sample.bpre.api.Item;
import my.sample.bpre.api.ItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Produces(MediaType.APPLICATION_XML)
public class ItemServiceImpl implements ItemService {
    private ItemRepository repository = new ItemRepository();

    @Override
    @GET
    @Path("/")
    public List<Item> findAll() {
        System.out.println("GET:: findAll");
        return repository.get();
    }

    @Override
    @GET
    @Path("/{id}")
    public Item find(@PathParam("id") String id) {
        System.out.println("GET:: find/" + id);
        return repository.get(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Item update(@PathParam("id") String id, Item item) {
        System.out.println("PUT:: update/" + id);
        item.setId(id);
        return repository.update(item);
    }

    @Override
    @POST
    @Path("/")
    public Item add(Item item) {
        System.out.println("POST:: add/" + item.getId());
        return repository.add(item);
    }
}