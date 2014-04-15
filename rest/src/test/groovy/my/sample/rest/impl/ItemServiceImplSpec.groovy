package my.sample.rest.impl

import my.sample.activator.SampleLogger
import my.sample.rest.api.Item
import spock.lang.Specification

import javax.ws.rs.core.Response

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
class ItemServiceImplSpec extends Specification {

    def service = new ItemServiceImpl()

    def setup() {
        service.setLogger(Mock(SampleLogger))
    }

    def "check findAll"() {
        when:
        def r = service.findAll()

        then:
        r.size() == 3
    }

    def "check find(id)"() {
        when:
        def r = service.find(id)

        then:
        r != null
        r.id == id
        r.name == name
        r.desc == desc

        where:
        id  | name      | desc
        "1" | "Item #1" | "Description for item #1"
        "2" | "Item #2" | "Description for item #2"
        "3" | "Item #3" | "Description for item #3"
    }

    def "check successful update(id, item)"() {
        def item = Item.object$.newItem("1", "ok", "ok desc")
        when:
        def r = service.update(item.id, item)

        then:
        r.status == Response.Status.OK.statusCode
        r.entity == item
    }

    def "check failed update(id, item)"() {
        def item = Item.object$.newItem("unknown", "ok", "ok desc")
        when:
        def r = service.update(item.id, item)

        then:
        r.status == Response.Status.NOT_MODIFIED.statusCode
    }

    def "check successful add(item)"() {
        def item = Item.object$.newItem("33", "ok", "ok desc")

        when:
        def r = service.add(item)

        then:
        r.status == Response.Status.OK.statusCode
        r.entity == item
    }

    def "check failed add(item)"() {
        def item = Item.object$.newItem("1", "ok", "ok desc")
        when:
        def r = service.add(item)

        then:
        r.status == Response.Status.NOT_MODIFIED.statusCode
    }

    def "check delete(id)"() {
        when:
        def r = service.delete("1")

        then:
        r.status == Response.Status.OK.statusCode
        r.entity.id == "1"
        r.entity.name == "Item #1"
    }

    def "check failed delete(id)"() {
        when:
        def r = service.delete("invalid")

        then:
        r.status == Response.Status.NOT_MODIFIED.statusCode
    }
}
