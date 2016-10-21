package be.more.reactive;

import be.more.reactive.util.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class S04_Blocking extends BaseTest {

	private static final Logger log = LoggerFactory.getLogger(S04_Blocking.class);

	@Test
	public void nonBlocking() throws Exception {
		final Observable<Long> smallNumbers = getSmallNumbersEmittedAsync();

		smallNumbers
				.forEach(s -> log.debug(s.toString()));

		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void blocking() throws Exception {
		final Observable<Long> smallNumbers = getSmallNumbersEmittedAsync();

		smallNumbers
				.toBlocking()    //blocks until all numbers are emitted
				.forEach(s -> log.debug(s.toString()));
	}

}
