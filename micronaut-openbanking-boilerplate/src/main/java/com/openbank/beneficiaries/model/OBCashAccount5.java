package com.openbank.beneficiaries.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Created by Anas Bassam Hussein
 * on 23/05/2021
 * */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"identification",
        "name",
        "schemeName",
        "secondaryIdentification"})
public class OBCashAccount5 {

    @Id
    @NotNull @NotEmpty @Size(max = 256)
    private String identification;

    @NotNull @NotEmpty
    private OBExternalAccountIdentification4Code schemeName;

    @Size(max = 350)
    private String name;

    @Size(max = 34)
    private String secondaryIdentification;
}
