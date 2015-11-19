package my.sample.rest.impl

import my.sample.model.api.Item
import my.sample.rest.api.ItemRepository

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Transactional
public class JpaItemRepository : ItemRepository {
    @PersistenceContext(unitName = "sampleDB")
    lateinit var em: EntityManager

    override fun get(): List<Item> {
        return em.createQuery("select i from Item as i", Item::class.java).resultList
    }

    override fun get(id: String): Item {
        val item = em.find(Item::class.java, id)
        if (item != null) {
            return item
        }
        throw IllegalArgumentException("Item not found. (item #$id)")
    }

    override fun update(item: Item): Item {
        val i = em.find(Item::class.java, item.id)
        if (i != null) {
            return em.merge(item)
        }
        throw IllegalArgumentException("Item not found. (item #" + item.id + ")")
    }

    override fun add(item: Item): Item {
        if (em.find(Item::class.java, item.id) != null) {
            throw IllegalArgumentException("Duplicate item id. (item #" + item.id + ")")
        }
        em.persist(item)
        return item
    }

    override fun delete(id: String): Item {
        val item = em.find(Item::class.java, id)
        if (item != null) {
            em.remove(item)
            return item
        }
        throw IllegalArgumentException("Item not found. (item #$id)")
    }
}
