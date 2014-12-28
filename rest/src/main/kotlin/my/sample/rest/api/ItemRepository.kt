package my.sample.rest.api

import my.sample.model.api.Item

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public interface ItemRepository {
    /**
     * To get the list of available items.
     *
     * @return the list of available items
     */
    fun get(): List<Item>

    /**
     * To get the item with given id.
     *
     * @param id the item id
     * @return the item
     */
    fun get(id: String): Item

    /**
     * Updates the given item and returns the updated item.
     *
     * @param item the item to be updated
     * @return the updated item
     */
    fun update(item: Item): Item

    /**
     * Adds the given item and returns the newly added item.
     *
     * @param item the item to be added
     * @return the newly added item
     */
    fun add(item: Item): Item

    /**
     * Deletes the item with given id.
     *
     * @param id the item id
     * @return the deleted item
     */
    fun delete(id: String): Item
}
