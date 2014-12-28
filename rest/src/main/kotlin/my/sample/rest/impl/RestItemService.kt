package my.sample.rest.impl

import my.sample.activator.Logger
import my.sample.model.api.Item
import my.sample.rest.api.ItemRepository
import my.sample.rest.api.ItemService
import java.net.URI
import javax.ws.rs.NotFoundException
import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class RestItemService : ItemService {
    lateinit var repository: ItemRepository
    lateinit var logger: Logger

    override fun findAll(): List<Item> {
        logger.warn("GET:: findAll")
        return repository.get()
    }

    override fun find(id: String): Item {
        logger.warn("GET:: find/$id")
        try {
            return repository.get(id)
        } catch (e: IllegalArgumentException) {
            throw NotFoundException(e.getMessage(), e)
        }

    }

    override fun update(id: String, item: Item): Response {
        logger.warn("PUT:: update/#$id")
        item.id = id

        try {
            val updated = repository.update(item)
            return Response.ok(updated).build()

        } catch (e: IllegalArgumentException) {
            throw NotFoundException(e.getMessage(), e)
        }

    }

    override fun add(item: Item): Response {
        logger.warn("POST:: add/${item.id}")

        try {
            val added = repository.add(item)
            return Response.created(URI.create(added.id)).build()

        } catch (e: IllegalArgumentException) {
            return Response.notModified(e.getMessage()).build()
        }

    }

    override fun delete(id: String): Response {
        logger.warn("DELETE:: delete/$id")

        try {
            val item = repository.delete(id)
            return Response.ok(item).build()

        } catch (e: IllegalArgumentException) {
            throw NotFoundException(e.getMessage(), e)
        }

    }
}