package my.sample.rest.impl

import my.sample.model.api.Item
import spock.lang.Unroll

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */

@Unroll
class ItemRepositorySpec extends RepoAware {

    def setup() {
        internalSetup()
    }

    def cleanup() {
        internalCleanup()
    }

    def "check get()"() {
        when:
        def r = repo.get()

        then:
        r.size() == 3
    }

    def "check get(#id)"() {
        when:
        def r = repo.get(id)

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

    def "check update(item)"() {
        given:
        def item = new Item("1", "ok", "ok desc")
        repo.update(item)

        when:
        def r = repo.get("1")

        then:
        r.id == item.id
        r.name == item.name
        r.desc == item.desc
    }

    def "check add(item)"() {
        given:
        def item = new Item("33", "ok", "ok desc")
        repo.add(item)

        when:
        def r = repo.get(item.id)

        then:
        r.id == item.id
        r.name == item.name
        r.desc == item.desc
    }

    def "check delete(item)"() {
        given:
        repo.delete("1")

        when:
        repo.get("1")

        then:
        def e = thrown(IllegalArgumentException)
        e.message.contains("Item not found")
    }
}
