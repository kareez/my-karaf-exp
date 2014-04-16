package my.sample.factory.impl

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class InternalBean {
    private var foo: String? = null
    private var bar: String? = null
    private var baz: String? = null
    {
        println("new InternalBean: $this")
    }

    public fun echo(message: String): String = "Echo processed: $message"

    public fun update(config: Map<String, *> ) {
        println("updating....: $this")
    }

    public fun setFoo(foo: String) {
        println("setting new foo: $foo")
        this.foo = foo
    }

    public fun setBar(bar: String) {
        println("setting new bar: $bar")
        this.bar = bar
    }

    public fun setBaz(baz: String) {
        println("setting new baz: $baz")
        this.baz = baz
    }

    override public fun toString() = " InternalBean { foo = '$foo' , bar = '$bar', baz = '$baz' }"
}
