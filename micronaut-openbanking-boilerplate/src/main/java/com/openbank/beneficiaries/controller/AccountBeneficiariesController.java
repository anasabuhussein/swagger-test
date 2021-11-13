package com.openbank.beneficiaries.controller;

import com.openbank.beneficiaries.model.Headers;
import com.openbank.beneficiaries.model.OBReadBeneficiary5;
import com.openbank.beneficiaries.service.impl.OBBeneficiary5ServiceImpl;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Optional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Validated
@Controller("/account")
public class AccountBeneficiariesController {

    private final OBBeneficiary5ServiceImpl beneficiary5Service;

    public AccountBeneficiariesController(OBBeneficiary5ServiceImpl beneficiary5Service) {
        this.beneficiary5Service = beneficiary5Service;
    }

    @ExecuteOn(TaskExecutors.IO)
    @Status(HttpStatus.OK)
    @Get(value = "{/AccountId}/beneficiaries",
            consumes = { MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_JSON })
    public HttpResponse<OBReadBeneficiary5> getAccountBeneficiaries (
            @NotNull @NotEmpty @PathVariable Optional<String> AccountId,
            /*Principal principal,*/
            HttpRequest httpRequest) {

        String _AccountId = AccountId.get();
        String xInteractionId = httpRequest.getHeaders().get(Headers.X_INTERACTION_ID.getValue());
        String xRequestId = httpRequest.getHeaders().get(Headers.X_REQUEST_ID.getValue());

//        return  HttpResponse.ok()
//                .header(Headers.X_INTERACTION_ID.getValue(), xInteractionId == null? "_": xInteractionId)
//                .header(Headers.X_REQUEST_ID.getValue(), xRequestId == null? "_": xRequestId)
//                .body(beneficiary5Service.findByAccountId(_AccountId, principal));

        return  HttpResponse.ok()
                .header(Headers.X_INTERACTION_ID.getValue(), xInteractionId == null? "_": xInteractionId)
                .header(Headers.X_REQUEST_ID.getValue(), xRequestId == null? "_": xRequestId)
                .body(beneficiary5Service.findByAccountId(_AccountId));
    }
}
