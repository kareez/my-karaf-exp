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
        find(MediaType.APPLICATION_JSON_TYPE)
    }

    @Test
    public void findXml() {
        find(MediaType.APPLICATION_XML_TYPE)
    }

    private void find(MediaType media) {
        Response response = client.accept(media).path('1', []).get()

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())

        String payload = response.readEntity(String.class)
        assertNotNull(payload)

        assertItem(payload, 1)
    }

    @Test
    public void findAllJson() {
        findAll(MediaType.APPLICATION_JSON_TYPE)
    }

    @Test
    public void findAllXml() {
        findAll(MediaType.APPLICATION_XML_TYPE)
    }

    private void findAll(MediaType media) {
        Response response = client.accept(media).get()

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())

        String payload = response.readEntity(String.class)
        assertNotNull(payload)

        assertItem(payload, 1)
        assertItem(payload, 2)
        assertItem(payload, 3)
    }

    @Test
    public void addJson() {
        add(4, MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
    }

    @Test
    public void addXml() {
        add(5, MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
    }

    private void add(int id, MediaType media, Closure generator) {

        Response response = client.accept(media).header(HttpHeaders.CONTENT_TYPE, media)
                .post(generator("$id", "Item #$id", "Description for item #$id"))

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus())

        def location = response.headers.getFirst("Location")
        assertNotNull(location)
        assertTrue(location.toString().endsWith("items/" + id))
    }

    @Test
    public void updateJson() {
        update(6, MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
    }

    @Test
    public void updateXml() {
        update(7, MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
    }

    private void update(int id, MediaType media, Closure generator) {

        int added = client.accept(media).header(HttpHeaders.CONTENT_TYPE, media)
                .post(generator("$id", "Item #$id", "Description for item #$id"))
                .getStatus()

        assertEquals(Response.Status.CREATED.getStatusCode(), added)

        Response response = client.accept(media).header(HttpHeaders.CONTENT_TYPE, media)
                .path("$id", [])
                .put(generator("$id", "Item #$id updaed", "Description for item #$id updated"))

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())

        String payload = response.readEntity(String.class)
        assertNotNull(payload)
    }

    @Test
    public void deleteJson() {
        delete(8, MediaType.APPLICATION_JSON_TYPE, JSON_GENERATOR)
    }

    @Test
    public void deleteXml() {
        delete(9, MediaType.APPLICATION_XML_TYPE, XML_GENERATOR)
    }

    private void delete(int id, MediaType media, Closure generator) {

        int added = client.accept(media).header(HttpHeaders.CONTENT_TYPE, media)
                .post(generator("$id", "Item #$id", "Description for item #$id"))
                .getStatus()

        assertEquals(Response.Status.CREATED.getStatusCode(), added)

        Response response = client.accept(media).path("$id", []).delete()

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus())
        assertEquals(media, response.getMediaType())
    }

    private static void assertItem(String payload, int id) {
        assertTrue(payload.contains(String.valueOf(id)))
        assertTrue(payload.contains("Item #" + id))
        assertTrue(payload.contains("Description for item #" + id))
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
