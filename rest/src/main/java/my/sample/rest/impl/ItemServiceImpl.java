package my.sample.rest.impl;

import my.sample.activator.SampleLogger;
import my.sample.rest.api.Item;
import my.sample.rest.api.ItemService;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class ItemServiceImpl implements ItemService {
    private ItemRepository repository;
    private SampleLogger logger;

    public void setLogger(SampleLogger logger) {
        this.logger = logger;
    }

    public void setRepository(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Item> findAll() {
        logger.warn("GET:: findAll");
        return repository.get();
    }

    @Override
    public Item find(String id) {
        logger.warn("GET:: find/" + id);
        try {
            return repository.get(id);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public Response update(String id, Item item) {
        logger.warn("PUT:: update/" + id);
        item.setId(id);

        try {
            Item updated = repository.update(item);
            return Response.ok(updated).build();

        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public Response add(Item item) {
        logger.warn("POST:: add/" + item.getId());

        try {
            Item added = repository.add(item);
            return Response.created(URI.create(added.getId())).build();

        } catch (IllegalArgumentException e) {
            return Response.notModified(e.getMessage()).build();
        }
    }

    @Override
    public Response delete(String id) {
        logger.warn("DELETE:: delete/" + id);

        try {
            Item item = repository.delete(id);
            return Response.ok(item).build();

        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage(), e);
        }
    }
}