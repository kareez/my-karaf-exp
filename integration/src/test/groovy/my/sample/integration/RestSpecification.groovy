package my.sample.integration

import org.apache.cxf.jaxrs.client.WebClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerClass

import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import static org.junit.Assert.*

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class RestSpecification extends BaseKarafSupport {
    private WebClient client

    @Before
    public void before() {
        client = WebClient.create("http://localhost:8181/cxf/items")
    }

    @Test
    public void findJson() {
        def id = add(MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
        find(id, MediaType.APPLICATION_JSON_TYPE)
    }

    @Test
    public void findXml() {
        def id = add(MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
        find(id, MediaType.APPLICATION_XML_TYPE)
    }

    @Test
    public void findAllJson() {
        def ids = (1..3).collect { add(MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR) }

        findAll(ids, MediaType.APPLICATION_JSON_TYPE)
    }

    @Test
    public void findAllXml() {
        def ids = (1..3).collect { add(MediaType.APPLICATION_XML_TYPE, XML_GENERATOR) }

        findAll(ids, MediaType.APPLICATION_XML_TYPE)
    }

    @Test
    public void updateJson() {
        def id = add(MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
        update(id, MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
    }

    @Test
    public void updateXml() {
        def id = add(MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
        update(id, MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
    }

    @Test
    public void deleteJson() {
        def id = add(MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
        delete(id, MediaType.APPLICATION_JSON_TYPE)
    }

    @Test
    public void deleteXml() {
        def id = add(MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
        delete(id, MediaType.APPLICATION_XML_TYPE)
    }

    private void delete(String id, MediaType media) {
        Response response = client.accept(media).path("$id", []).delete()

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())
    }

    private static void assertItem(String payload, String id) {
        assertTrue(payload.contains(id))
        assertTrue(payload.contains("Item #$id"))
        assertTrue(payload.contains("Description for item #$id"))
    }

    private void find(String id, MediaType media) {
        Response response = client.accept(media).path(id, []).get()

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())

        String payload = response.readEntity(String.class)
        assertNotNull(payload)

        assertItem(payload, id)
    }

    private void findAll(List<String> ids, MediaType media) {
        Response response = client.accept(media).get()

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())

        String payload = response.readEntity(String.class)
        assertNotNull(payload)

        ids.each { assertItem(payload, it) }
    }

    private String add(MediaType media, Closure generator) {
        def id = UUID.randomUUID().toString()

        Response response = client.accept(media).header(HttpHeaders.CONTENT_TYPE, media)
                .post(generator("$id", "Item #$id", "Description for item #$id"))

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus())

        def location = response.headers.getFirst("Location")
        assertNotNull(location)
        assertTrue(location.toString().endsWith("items/" + id))
        return id
    }

    private void update(String id, MediaType media, Closure generator) {

        Response response = client.accept(media).header(HttpHeaders.CONTENT_TYPE, media)
                .path("$id", [])
                .put(generator("$id", "Item #$id updaed", "Description for item #$id updated"))

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())

        String payload = response.readEntity(String.class)
        assertNotNull(payload)
    }

    private static def JSON_GENERATOR = { id, name, desc ->
        """
        { "item": {
                "id":$id,
                "name":"$name",
                "desc":"$desc"
            }
        }
        """.toString()
    }

    private static def XML_GENERATOR = { id, name, desc ->
        """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <item>
                <id>$id</id>
                <name>Item #$id</name>
                <desc>Description for item #$id</desc>
            </item>
        """.toString()
    }
}
