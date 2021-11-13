package com.openbank.beneficiaries;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.function.Consumer;

@OpenAPIDefinition(
        info = @Info(
                title = "Beneficiary",
                version = "3.1.7",
                description = "The beneficiaries resource is used by an AISP to retrieve the account beneficiaries " +
                        "information for a specific AccountId or to " +
                        "retrieve the beneficiaries information in bulk for account(s) " +
                        "that the PSU has authorised to access."
        )
)
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class.getName());

    public static void main(String[] args) {

        ApplicationContext applicationContext = Micronaut
                .run(args)
                .start();

        log.info("Server Starting Information ::");

        log.info("Database Url: " + applicationContext.getEnvironment()
                .get("datasources.default.url", String.class).orElse(""));

        log.info("Database userName: " + applicationContext.getEnvironment()
                .get("datasources.default.username", String.class).orElse(""));

        log.info("Database password: " + applicationContext.getEnvironment()
                .get("datasources.default.password", String.class).orElse(""));

        log.info("Kafka Url: " + applicationContext.getEnvironment()
                .get("kafka.bootstrap.servers", String.class).orElse(null));

        log.info("JWT secret: " + applicationContext.getEnvironment()
                .get("micronaut.security.token.jwt.signatures.secret.generator.secret"
                        , String.class).orElse(null));

    }
}
