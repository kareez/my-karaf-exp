package my.sample.bpre.api;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

public interface ItemService {
    List<Item> findAll();

    Item find(String id);

    Response update(String id, Item item);

    Response add(Item item);

    Response delete(String id);
}