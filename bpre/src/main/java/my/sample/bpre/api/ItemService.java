package my.sample.bpre.api;

import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

public interface ItemService {
    List<Item> find();

    Item findAll(String id);

    Item update(String id, Item item);

    Item add(Item item);
}