package my.sample.factory.impl;

import java.util.Map;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class InternalBean {
    private String foo;
    private String bar;
    private String baz;

    public InternalBean() {
        System.out.println("new InternalBean: " + this);
    }

    public String echo(String message) {
        return "Echo processed: " + message;
    }

    public void update(Map<String, ?> config) {
        System.out.println("updating....: " + this);
    }

    public void setFoo(String foo) {
        System.out.println("setting new foo:" + foo);
        this.foo = foo;
    }

    public void setBar(String bar) {
        System.out.println("setting new bar:" + bar);
        this.bar = bar;
    }

    public void setBaz(String baz) {
        System.out.println("setting new baz:" + baz);
        this.baz = baz;
    }

    @Override
    public String toString() {
        return " InternalBean {" +
                " foo = '" + foo + '\'' +
                ", bar = '" + bar + '\'' +
                ", baz = '" + baz + '\'' +
                " }";
    }
}
