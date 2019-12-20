package com.kotall.learn.metric;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/12/27 13:39
 * @since 1.0.0
 */
public class MetricTest {
    static final MetricRegistry metrics = new MetricRegistry();

    public static void main(String[] args) {
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        requests.mark();
        requests.mark();
        requests.mark();
        wait5Seconds();
    }

    static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

//        Slf4jReporter slf4jReporter = Slf4jReporter.forRegistry(metrics)
//                .outputTo(LoggerFactory.getLogger("com.example.metrics"))
//                .convertRatesTo(TimeUnit.SECONDS)
//                .convertDurationsTo(TimeUnit.MILLISECONDS)
//                .build();
//        slf4jReporter.start(1, TimeUnit.SECONDS);
    }

    static void wait5Seconds() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
        }
    }
}
