package com.openbank.beneficiaries.model;


/*
 * Created by Anas Bassam Hussein
 * on 23/05/2021
 * */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"data", "mate"})
public class OBReadBeneficiary5 {

    private final Mate mate;
    private final OBReadDataBeneficiary5 data;

    public OBReadBeneficiary5 () {

        this.data = new OBReadDataBeneficiary5();
        this.mate = new Mate();
    }
}
