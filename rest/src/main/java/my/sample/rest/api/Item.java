package my.sample.rest.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"id", "name", "desc"})
public class Item {
    @NotNull
    String id;

    @Max(5)
    @Min(2)
    String name;
    
    @Max(10)
    String desc;

    public Item() {
    }

    public Item(String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}