package my.sample.rest.api

import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public trait ItemService {
    /**
     * To get all items.
     *
     * @return all available items
     */
    fun findAll(): List<Item>

    /**
     * To get a specific item.
     *
     * @param id item id
     * @return the item with given id
     */
    fun find(id: String): Item

    /**
     * To update a specific item.
     *
     * @param id item id
     * @param item updated item
     * @return ok if updated was successful, not modified if item was not found
     */
    fun update(id: String, item: Item): Response

    /**
     * To add new item.
     *
     * @param item new item to be added
     * @return ok if add was successful, not modified if item was duplicated
     */
    fun add(item: Item): Response

    /**
     * To delete a specific item.
     *
     * @param id item id
     * @return ok if delete was successful, not modified if item was not found
     */
    fun delete(id: String): Response
}