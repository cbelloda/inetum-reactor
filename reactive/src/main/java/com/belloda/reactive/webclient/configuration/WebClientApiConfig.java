package com.belloda.reactive.webclient.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
@Slf4j
public class WebClientApiConfig {

    @Value("${api.jne.base.url}")
    private String apiJNEBaseUrl;

    @Bean("jneWebClient")
    public WebClient jneWebClient(WebClient.Builder webClientBuilder) {

        HttpClient httpClient = HttpClient.create().tcpConfiguration(tcpClient -> {
            tcpClient = tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000);
            tcpClient = tcpClient
                    .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS)));
            return tcpClient;
        });

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 500)).build();

        return webClientBuilder.baseUrl(apiJNEBaseUrl).clientConnector(connector).filter(logRequest())
                .exchangeStrategies(exchangeStrategies).build();
    }

    private static ExchangeFilterFunction logRequest() {

        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

}
