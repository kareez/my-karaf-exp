package my.sample.rest.impl;

import my.sample.rest.api.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

class ItemRepository {
    Map<String, Item> items = new HashMap<>();

    ItemRepository() {
        for (int i = 1; i < 4; i++) {
            String id = String.valueOf(i);
            items.put(id, new Item(id, "Item #" + id, "Description for item #" + id));
        }
    }

    List<Item> get() {
        return new ArrayList<>(items.values());
    }

    Item get(String id) {
        if (items.containsKey(id)) {
            return items.get(id);
        }
        throw new IllegalArgumentException("Item not found. (item #" + id + ")");
    }

    Item update(Item item) {
        if (items.containsKey(item.getId())) {
            items.put(item.getId(), item);
            return items.get(item.getId());
        }
        throw new IllegalArgumentException("Item not found. (item #" + item.getId() + ")");
    }

    Item add(Item item) {
        if (items.containsKey(item.getId())) {
            throw new IllegalArgumentException("Duplicate item id. (item #" + item.getId() + ")");
        }
        items.put(item.getId(), item);
        return items.get(item.getId());
    }

    Item delete(String id) {
        if (items.containsKey(id)) {
            return items.remove(id);
        }
        throw new IllegalArgumentException("Item not found. (item #" + id + ")");
    }
}
