package com.openbank.beneficiaries.repository;

import com.openbank.beneficiaries.model.OBBranchAndFinancialInstitutionIdentification6;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Repository
public interface OBBranchAndFinancialInstitutionIdentification6Repository extends
        CrudRepository<OBBranchAndFinancialInstitutionIdentification6, String> {

    @Override
    public List<OBBranchAndFinancialInstitutionIdentification6> findAll() ;
}
