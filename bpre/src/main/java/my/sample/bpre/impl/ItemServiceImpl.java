package my.sample.bpre.impl;

import my.sample.bpre.api.Item;
import my.sample.bpre.api.ItemService;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Produces(MediaType.APPLICATION_XML)
@WebService
public class ItemServiceImpl implements ItemService {
    private ItemRepository repository = new ItemRepository();

    @Override
    @GET
    @Path("/")
    public List<Item> find() {
        System.out.println("find/");
        return repository.get();
    }

    @Override
    @GET
    @Path("/{id}")
    public Item findAll(@PathParam("id") String id) {
        System.out.println("findAll/" + id);
        return repository.get(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Item update(@PathParam("id") String id, Item item) {
        System.out.println("update/" + id);
        item.setId(id);
        return repository.update(item);
    }

    @Override
    @POST
    @Path("/")
    public Item add(Item item) {
        System.out.println("add/" + item.getId());
        return repository.add(item);
    }
}