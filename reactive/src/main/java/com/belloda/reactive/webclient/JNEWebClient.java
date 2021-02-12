package com.belloda.reactive.webclient;

import com.belloda.reactive.webclient.response.JNEResponse;

import reactor.core.publisher.Flux;

public interface JNEWebClient {
    Flux<JNEResponse> getPoliticalParties(String electoralDistrict);
}
