package my.sample.rest.impl;

import my.sample.rest.api.Item;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

class ItemRepository {
    EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    ItemRepository() {
        for (int i = 1; i < 4; i++) {
            String id = String.valueOf(i);
            entityManager.persist(new Item(id, "Item #" + id, "Description for item #" + id));
        }
    }

    List<Item> get() {
        return entityManager.createQuery("select i from Item as i", Item.class).getResultList();
    }

    Item get(String id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            return item;
        }
        throw new IllegalArgumentException("Item not found. (item #" + id + ")");
    }

    Item update(Item item) {
        Item i = entityManager.find(Item.class, item.getId());
        if (i != null) {
            return entityManager.merge(item);
        }
        throw new IllegalArgumentException("Item not found. (item #" + item.getId() + ")");
    }

    Item add(Item item) {
        if (entityManager.find(Item.class, item.getId()) != null) {
            throw new IllegalArgumentException("Duplicate item id. (item #" + item.getId() + ")");
        }
        entityManager.persist(item);
        return item;
    }

    Item delete(String id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(id);
            return item;
        }
        throw new IllegalArgumentException("Item not found. (item #" + id + ")");
    }
}
