package be.more.reactive;

import be.more.reactive.util.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class S06_Subscriber extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(S06_Subscriber.class);

    @Test
    public void forEach() {
        Observable.just(1, 2, 3, 4, 5)
                .forEach(i -> log.debug(i.toString()));
    }

    @Test
    public void subscriber() {
        Observable.just(1, 2, 3, 4, 5)
                .subscribe(
                        i -> log.debug(i.toString()),
                        t -> log.error("Ups", t),
                        () -> log.debug("Stream has completed")
                );
    }

}
