package my.sample.rest.impl

import my.sample.activator.SampleLogger
import my.sample.rest.api.Item
import my.sample.rest.api.ItemService

import javax.ws.rs.Produces as produces
import javax.ws.rs.GET as get
import javax.ws.rs.PUT as put
import javax.ws.rs.POST as post
import javax.ws.rs.DELETE as delete
import javax.ws.rs.Path as path
import javax.ws.rs.PathParam as pathParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
//produces(array(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON))
public class ItemServiceImpl : ItemService {
    private val repository = ItemRepository()

    private var logger: SampleLogger? = null;

    get
    path("/")
    override fun findAll(): List<Item> {
        logger?.warn("GET:: findAll")
        return repository.get()
    }

    get
    path("/{id}")
    override fun find(pathParam("id") id: String): Item {
        logger?.warn("GET:: find/$id")
        return repository.get(id)
    }

    put
    path("/{id}")
    override fun update(pathParam("id") id: String, item: Item): Response {
        logger?.warn("PUT:: update/$id")
        item.id = id

        try {
            val updated = repository.update(item)
            return Response.ok(updated)!!.build()!!

        } catch (e: IllegalArgumentException) {
            return Response.notModified(e.getMessage())!!.build()!!
        }
    }

    post
    path("/")
    override fun add(item: Item): Response {
        logger?.warn("POST:: add/${item.id}")

        try {
            val added = repository.add(item)
            return Response.ok(added)!!.build()!!

        } catch (e: IllegalArgumentException) {
            return Response.notModified(e.getMessage())!!.build()!!
        }
    }

    delete
    path("/{id}")
    override fun delete(pathParam("id") id: String): Response {
        logger?.warn("DELETE:: delete/$id")

        try {
            val item = repository.delete(id)
            return Response.ok(item)!!.build()!!

        } catch (e: IllegalArgumentException) {
            return Response.notModified(e.getMessage())!!.build()!!
        }
    }
}