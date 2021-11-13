package com.openbank.beneficiaries.model;

/*
 * Created by Anas Bassam Hussein
 * on 23/05/2021
 * */

public enum OBExternalAccountIdentification4Code {

    BICFI ("UK.OBIE.BICFI"),
    SortCodeAccountNumber ("UK.OBIE.SortCodeAccountNumber");

    private String value;

    OBExternalAccountIdentification4Code (String value) {
        this.value = value;
    }

    public String getValue (){
        return  value;
    }
}
