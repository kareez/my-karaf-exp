package my.sample.rest.impl

import my.sample.rest.api.Item

import java.util.ArrayList
import java.util.HashMap

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

class ItemRepository {
    val items = hashMapOf<String, Item>();

    {
        for (i in 1..4) {
            val id = "$i"
            items.put(id,  Item.newItem(id, "Item #$id", "Description for item #$id"))
        }
    }

    fun get() = ArrayList(items.values())

    fun get(id: String): Item {
        if (items.containsKey(id)) {
            return items.get(id)!!
        }
        throw IllegalArgumentException("Item not found. (item #$id)")
    }

    fun update(item: Item): Item {
        if (items.containsKey(item.id)) {
            items.put(item.id!!, item)
            return items.get(item.id)!!
        }
        throw IllegalArgumentException("Item not found. (item #${item.id})")
    }

    fun add(item: Item): Item {
        if (items.containsKey(item.id)) {
            throw IllegalArgumentException("Duplicate item id. (item #${item.id})")
        }
        items.put(item.id!!, item)
        return items.get(item.id)!!
    }

    fun delete(id: String): Item {
        if (items.containsKey(id)) {
            return items.remove(id)!!
        }
        throw IllegalArgumentException("Item not found. (item #$id)")
    }
}
