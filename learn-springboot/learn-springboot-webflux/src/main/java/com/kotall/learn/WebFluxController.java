package com.kotall.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Server Send Event (SSE)
 */
@CrossOrigin(origins = {"http://localhost:80", "null"})
@Slf4j
@RestController
@RequestMapping("/webflux")
public class WebFluxController {

    /**
     * 阻塞5秒钟
     *
     * @return
     */
    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return "some string";
    }

    /**
     * 普通的SpringMVC方法
     *
     * @return
     */
    @GetMapping("/1")
    public String get1() {
        log.info("get1 start");
        String result = createStr();
        log.info("get1 end.");
        return result;
    }

    /**
     * WebFlux(返回的是Mono)
     *
     * @return
     */
    @GetMapping("/2")
    public Mono<String> get2() {
        log.info("get2 start");
        Mono<String> result = Mono.fromSupplier(() -> createStr());
        log.info("get2 end.");
        return result;
    }

    /**
     * Flux: 返回 0~n 个元素
     * 注：需要制定MediaType
     *
     * @return
     */
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {

            }
            return "flux data--" + i;
        }));
        return result;
    }


}
