package my.sample.rest.api

import javax.xml.bind.annotation.XmlRootElement as xmlRootElement
import javax.xml.bind.annotation.XmlType as xmlType

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
xmlRootElement
xmlType(propOrder = array("id", "name", "desc"))
public class Item() {
    class object {
        fun newItem(id: String, name: String, desc: String): Item {
            val item = Item()
            item.id = id
            item.name = name
            item.desc = desc
            return item
        }
    }

    public var id: String? = null
    public var name: String? = null
    public var desc: String? = null
}