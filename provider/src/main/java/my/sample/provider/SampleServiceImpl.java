package my.sample.provider;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class SampleServiceImpl implements SampleService {

    public String echo(String message) {
        return "Echo processed: " + message;
    }

}