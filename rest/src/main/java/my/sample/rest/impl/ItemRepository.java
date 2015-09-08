package my.sample.rest.impl;

import my.sample.model.api.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

public class ItemRepository {
    @PersistenceContext(unitName = "sampleDB")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Item> get() {
        return em.createQuery("select i from Item as i", Item.class).getResultList();
    }

    public Item get(String id) {
        Item item = em.find(Item.class, id);
        if (item != null) {
            return item;
        }
        throw new IllegalArgumentException("Item not found. (item #" + id + ")");
    }

    public Item update(Item item) {
        Item i = em.find(Item.class, item.getId());
        if (i != null) {
            return em.merge(item);
        }
        throw new IllegalArgumentException("Item not found. (item #" + item.getId() + ")");
    }

    public Item add(Item item) {
        if (em.find(Item.class, item.getId()) != null) {
            throw new IllegalArgumentException("Duplicate item id. (item #" + item.getId() + ")");
        }
        em.persist(item);
        return item;
    }

    public Item delete(String id) {
        Item item = em.find(Item.class, id);
        if (item != null) {
            em.remove(item);
            return item;
        }
        throw new IllegalArgumentException("Item not found. (item #" + id + ")");
    }
}
