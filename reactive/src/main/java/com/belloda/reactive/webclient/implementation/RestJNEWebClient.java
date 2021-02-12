package com.belloda.reactive.webclient.implementation;

import com.belloda.reactive.webclient.JNEWebClient;
import com.belloda.reactive.webclient.response.JNEResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class RestJNEWebClient implements JNEWebClient {

    @Value("${api.jne.base.path}")
    private String politicalPartyURI;

    private final WebClient jneWebClient;

    @Override
    public Flux<JNEResponse> getPoliticalParties(String electoralDistrict) {
        
        return jneWebClient.get().uri(uriBuilder -> uriBuilder.path(politicalPartyURI).build("110-2-"+electoralDistrict+"------0-"))
        .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON)).retrieve()
        .bodyToFlux(JNEResponse.class);
    }
    
}
