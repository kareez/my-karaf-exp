package my.sample.factory.impl

import my.sample.factory.SampleFactoryService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultSampleFactoryService : SampleFactoryService {
    private var foo: String? = null
    private var bar: String? = null
    private var baz: String? = null

    {
        println("new DefaultSampleFactoryService: $this")
    }

    public fun init() {
        println("Initializing DefaultSampleFactoryService: $this")
    }

    public fun update(config: Map<String, *>) {
        println("updating....: $this")
    }

    public fun destroy() {
        println("Destroying DefaultSampleFactoryService: $this")
    }

    override fun echo(message: String): String {
        return "Echo processed: $message"
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