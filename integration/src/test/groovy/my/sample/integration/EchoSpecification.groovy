package my.sample.integration

import org.apache.cxf.jaxrs.client.WebClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.ops4j.pax.exam.junit.PaxExam
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy
import org.ops4j.pax.exam.spi.reactors.PerClass

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class EchoSpecification extends BaseKarafSupport {
    private WebClient client

    @Before
    public void before() {
        client = WebClient.create("http://localhost:8181/cxf/echo")
    }

    @Test
    public void echoJson() {
        echo(MediaType.APPLICATION_JSON_TYPE)
    }

    @Test
    public void echoXml() {
        echo(MediaType.APPLICATION_XML_TYPE)
    }

    private void echo(MediaType media) {
        def response = client
                .accept(media)
                .get()

        assertEquals(Response.Status.OK.statusCode, response.status)
        assertEquals(media, response.mediaType)

        assertNotNull(response.readEntity(String))
    }
}
