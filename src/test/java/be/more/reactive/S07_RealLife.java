package be.more.reactive;

import be.more.reactive.db.Query;
import be.more.reactive.util.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.util.async.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.naturalOrder;

public class S07_RealLife extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(S07_RealLife.class);

    @Test
    public void findByQuery() {
        final List<Query> queries = getQueries();

        final List<Observable<String>> resultObservables =
                queries.stream()
                        .map(query ->
                                Async.start(() -> {
                                    log.debug("Firing query {}", query.getId());
                                    return db.apply(query);
                                }, scheduler)
                        )
                        .collect(Collectors.toList());

        final List<String> results = new ArrayList<>();
        Observable.merge(resultObservables)
                .toBlocking()
                .subscribe(
                        result -> {
                            log.debug("Got result {}", result);
                            results.add(result);
                        },
                        t -> {
                            throw new RuntimeException(t);
                        },
                        () -> results.sort(naturalOrder())
                        );

        log.debug("Results are {}", results);
    }

}
