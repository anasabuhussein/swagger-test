package com.openbank.beneficiaries;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import lombok.Getter;


public class TestServer {

    @Getter
    private static EmbeddedServer embeddedServer;

    @Getter
    private static HttpClient httpClient;

    @Getter
    private static ApplicationContext applicationContext;

    public static void setupServer() {

        embeddedServer = ApplicationContext
                .run(EmbeddedServer.class);

        httpClient = embeddedServer
                .getApplicationContext()
                .createBean(HttpClient.class, embeddedServer.getURL());

        applicationContext = embeddedServer.getApplicationContext();

    }

    public static void stopServer() {

        if (embeddedServer != null)
            embeddedServer.stop();

        if (httpClient != null)
            httpClient.stop();
    }
}
