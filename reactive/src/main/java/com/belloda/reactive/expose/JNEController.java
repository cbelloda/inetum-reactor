package com.belloda.reactive.expose;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

import com.belloda.reactive.webclient.JNEWebClient;
import com.belloda.reactive.webclient.response.JNEResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/jne/political")
@CrossOrigin
@RequiredArgsConstructor
public class JNEController {

    private final JNEWebClient jneWebClient;


    @GetMapping(path = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {


        return Flux.interval(Duration.ofSeconds(1)).map(sequence -> "Flux - " + LocalTime.now().toString());

    }

    @GetMapping(path = "/test/integer")
    public Flux<Integer> integerFlux() {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Integer> values = Flux.fromStream(Arrays.asList(1, 2, 3, 4, 5).stream());

        return Flux.zip(values, interval, (key, value) -> key).log();
    
    }

    @GetMapping("/parties/{electoral-district}")
    public Flux<JNEResponse> getPoliticalParties(@PathVariable("electoral-district") String electoralDistrict) {
        return jneWebClient.getPoliticalParties(electoralDistrict);
    }


}
