package be.more.reactive;

import be.more.reactive.util.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class S05_Merging extends BaseTest {

	private static final Logger log = LoggerFactory.getLogger(S05_Merging.class);

	@Test
	public void merge() throws Exception {
		final Observable<Integer> smallNumbers = Observable.just(1, 2, 3, 4, 5);
		final Observable<Integer> biggerNumbers = Observable.just(100, 200, 300, 400, 500);

		Observable.merge(smallNumbers, biggerNumbers)
				.forEach(s -> log.debug(s.toString()));
	}

	@Test
	public void mergeAsync() throws Exception {
		final Observable<Long> smallNumbers = getSmallNumbersEmittedAsync();  //1,2,3,4
		final Observable<Long> biggerNumbers = getBiggerNumbersEmittedAsync();  //100,200,300,400

		Observable.merge(smallNumbers, biggerNumbers)
				.toBlocking()    //blocks until all numbers are emitted
				.forEach(s -> log.debug(s.toString()));
	}

}
