package my.sample.bpre.api;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface ItemService {
    /**
     * To get all items.
     *
     * @return all available items
     */
    List<Item> findAll();

    /**
     * To get a specific item.
     *
     * @param id item id
     * @return the item with given id
     */
    Item find(String id);

    /**
     * To update a specific item.
     *
     * @param id item id
     * @param item updated item
     * @return ok if updated was successful, not modified if item was not found
     */
    Response update(String id, Item item);

    /**
     * To add new item.
     *
     * @param item new item to be added
     * @return ok if add was successful, not modified if item was duplicated
     */
    Response add(Item item);

    /**
     * To delete a specific item.
     *
     * @param id item id
     * @return ok if delete was successful, not modified if item was not found
     */
    Response delete(String id);
}