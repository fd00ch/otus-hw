package org.akimov.asic.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class IntegrationsConfig {
    private final AsicConfig asicConfig;

    @Bean
    public WebClient asicServiceWebClient() {
        WebClient.Builder builder = WebClient.builder();
        HttpClient httpClient = HttpClient.create();
        httpClient
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .doOnConnected(it -> it
                        .addHandlerLast(new WriteTimeoutHandler(2500, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new ReadTimeoutHandler(30000, TimeUnit.MILLISECONDS))
                );
        builder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(asicConfig.getAsicUrl());

        return builder.build();
    }
}
