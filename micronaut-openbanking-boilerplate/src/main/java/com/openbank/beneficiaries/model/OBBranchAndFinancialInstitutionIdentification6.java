package com.openbank.beneficiaries.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Created by Anas Abu-Hussein
 * on 23/05/2021
 * */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"identification",
                    "name",
                    "schemeName",
                    "postalAddress"})
public class OBBranchAndFinancialInstitutionIdentification6 {

    @Id
    @Size(max = 35)
    @NotNull @NotEmpty
    private String identification;

    @Size(max = 140)
    private String name;

    private OBExternalFinancialInstitutionIdentification4Code schemeName;

    @OneToOne
    private OBPostalAddress6 postalAddress;
}
