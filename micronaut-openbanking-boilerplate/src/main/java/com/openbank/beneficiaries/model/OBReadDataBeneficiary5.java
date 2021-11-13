package com.openbank.beneficiaries.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/*
 * Created by Anas Bassam Hussein
 * on 23/05/2021
 * */

@Data
@Getter @Setter
public class OBReadDataBeneficiary5 {

    private final List<OBBeneficiary5> beneficiary;

    public OBReadDataBeneficiary5 () {

        this.beneficiary = new ArrayList<>();
    }
}
