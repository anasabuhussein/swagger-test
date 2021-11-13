package com.openbank.beneficiaries.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.annotation.Nullable;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"accountId",
        "beneficiaryId",
        "beneficiaryType",
        "reference",
        "creditorAccount",
        "creditorAgent"})

public class OBBeneficiary5 {

    @Id
    @Size(max = 40)
    @NotNull @NotEmpty
    private String beneficiaryId;

    @Size(max = 40)
    @NotNull @NotEmpty
    private String accountId;

    private OBBeneficiaryType1Code beneficiaryType;

    @Size(max = 35)
    private String reference;

    @OneToOne
    private OBBranchAndFinancialInstitutionIdentification6 creditorAgent;

    @OneToOne
    private OBCashAccount5 creditorAccount;

    @OneToOne
    private OBSupplementaryData1 supplementaryData;

}

