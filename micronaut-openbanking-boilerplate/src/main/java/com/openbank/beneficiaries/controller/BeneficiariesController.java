package com.openbank.beneficiaries.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.openbank.beneficiaries.model.Headers;
import com.openbank.beneficiaries.model.OBBeneficiary5;
import com.openbank.beneficiaries.model.OBReadBeneficiary5;
import com.openbank.beneficiaries.service.OBBeneficiary5KafkaProducerService;
import com.openbank.beneficiaries.service.impl.OBBeneficiary5ServiceImpl;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.messaging.exceptions.MessagingClientException;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;


@Secured(SecurityRule.IS_ANONYMOUS)
@Validated
@Controller ("/")
public class BeneficiariesController {

    private final OBBeneficiary5ServiceImpl beneficiary5Service;
    private final OBBeneficiary5KafkaProducerService beneficiary5KafkaProducerService;

    public BeneficiariesController(OBBeneficiary5ServiceImpl beneficiary5Service,
                                   OBBeneficiary5KafkaProducerService beneficiary5KafkaProducerService) {

        this.beneficiary5Service = beneficiary5Service;
        this.beneficiary5KafkaProducerService = beneficiary5KafkaProducerService;
    }

    @ExecuteOn(TaskExecutors.IO)
    @Status(value = HttpStatus.OK)
    @Get(value = "/beneficiaries{?page}",
            consumes = { MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_JSON })
    public HttpResponse<OBReadBeneficiary5> getBeneficiaries (@QueryValue Optional<Integer> page,
                                                              /*Principal principal,*/
                                                              HttpRequest httpRequest) {

        int _page = page.orElse(0);
        String xInteractionId = httpRequest.getHeaders().get(Headers.X_INTERACTION_ID.getValue());
        String xRequestId = httpRequest.getHeaders().get(Headers.X_REQUEST_ID.getValue());

//        return  HttpResponse.ok()
//                .header(Headers.X_INTERACTION_ID.getValue(), xInteractionId == null? "_": xInteractionId)
//                .header(Headers.X_REQUEST_ID.getValue(), xRequestId == null? "_": xRequestId)
//                .body(beneficiary5Service.findAll(Pageable.from(_page), principal));

        return  HttpResponse.ok()
                .header(Headers.X_INTERACTION_ID.getValue(), xInteractionId == null? "_": xInteractionId)
                .header(Headers.X_REQUEST_ID.getValue(), xRequestId == null? "_": xRequestId)
                .body(beneficiary5Service.findAll(Pageable.from(_page)));
    }

    @ExecuteOn(TaskExecutors.IO)
    @Status(value = HttpStatus.CREATED)
    @Post(value = "/beneficiary{?topic,key}",
            consumes = { MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_JSON })
    public HttpResponse createBeneficiary (
            @QueryValue Optional<String> topic,
            @QueryValue Optional<String> key,
            @Valid @Body OBBeneficiary5 beneficiary) {

        String _topic = topic.orElse("");
        String _key = key.orElse("");
        OBBeneficiary5 _beneficiary = null;

        try{
            _beneficiary = beneficiary5KafkaProducerService
                    .createOBBeneficiaryWithKafkaProducer(
                            _topic,
                            _key,
                            beneficiary);

            return HttpResponse
                    .created(_beneficiary);

        } catch (NoSuchElementException| ConstraintViolationException |
                MessagingClientException | JsonProcessingException e) {
            return HttpResponse.badRequest(e);
        } catch (PersistenceException e) {
            return HttpResponse
                    .status(HttpStatus.CONFLICT)
                    .body(e.fillInStackTrace()
                        .getCause());
        } catch (Exception e) {
            return HttpResponse.serverError(e);
        }
    }


    @ExecuteOn(TaskExecutors.IO)
    @Status(HttpStatus.OK)
    @Put(value = "/beneficiary{/BeneficiaryId}{?topic,key}",
            consumes = { MediaType.APPLICATION_JSON },
            produces = { MediaType.APPLICATION_JSON })
    public HttpResponse updateBeneficiary (
            @QueryValue Optional<String> topic,
            @QueryValue Optional<String> key,
            @NotNull @NotEmpty @PathVariable Optional<String> BeneficiaryId,
            @Valid @Body OBBeneficiary5 beneficiary) {

        String _topic = topic.orElse("");
        String _key = key.orElse("");
        String _BeneficiaryId = BeneficiaryId.get();
        OBBeneficiary5 _beneficiary = null;

        try{
            _beneficiary = beneficiary5KafkaProducerService
                    .updateOBBeneficiaryWithKafkaProducer(
                            _topic,
                            _key,
                            _BeneficiaryId,
                            beneficiary);

            return HttpResponse
                    .ok(_beneficiary);

        } catch (NoSuchElementException| ConstraintViolationException |
                MessagingClientException | JsonProcessingException e) {
            return HttpResponse.badRequest(e);
        } catch (PersistenceException e) {
            return HttpResponse
                    .status(HttpStatus.CONFLICT)
                    .body(e.fillInStackTrace()
                        .getCause());
        } catch (Exception e) {
            return HttpResponse.serverError(e);
        }
    }


    @ExecuteOn(TaskExecutors.IO)
    @Status(HttpStatus.NO_CONTENT)
    @Delete(value = "/beneficiary{/BeneficiaryId}")
    public HttpResponse deleteBeneficiary (
            @NotNull @NotEmpty @PathVariable Optional<String> BeneficiaryId) {

        String _BeneficiaryId = BeneficiaryId.get();

        try{
            beneficiary5Service.deleteBeneficiaryById(_BeneficiaryId);
            return HttpResponse.noContent();
        } catch (NoSuchElementException| ConstraintViolationException |
                MessagingClientException e) {
            return HttpResponse.badRequest(e);
        } catch (PersistenceException e) {
            return HttpResponse
                    .status(HttpStatus.CONFLICT)
                    .body(e.fillInStackTrace()
                        .getCause()
                        .fillInStackTrace()
                        .getCause());
        } catch (Exception e) {
            return HttpResponse.serverError(e);
        }
    }
}












