package my.sample.provider.impl

import my.sample.provider.SampleService

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class DefaultSampleService : SampleService {

    override fun echo(message: String) = "Echo processed: $message"
}