package my.sample.model.api

import javax.persistence.Entity
import javax.persistence.Id
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@Entity
@XmlRootElement
@XmlType(propOrder = arrayOf("id", "name", "desc"))
open public class Item() {
    @Id
    public var id: String = ""
    public var name: String = ""
    public var desc: String = ""

    public constructor(id: String, name: String, desc: String) : this() {
        this.id = id
        this.name = name
        this.desc = desc
    }
}