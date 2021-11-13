package com.openbank.beneficiaries.service;

import com.openbank.beneficiaries.model.OBBranchAndFinancialInstitutionIdentification6;

import javax.persistence.PersistenceException;
import java.util.NoSuchElementException;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

public interface OBBranchAndFinancialInstitutionIdentification6Service {

    public OBBranchAndFinancialInstitutionIdentification6
        findOBBranchAndFinancialInstitutionIdentification6ById (String creditorAgentId) throws NoSuchElementException;

    public OBBranchAndFinancialInstitutionIdentification6
        createOBBranchAndFinancialInstitutionIdentification6 (OBBranchAndFinancialInstitutionIdentification6 creditorAgent) throws PersistenceException;

    public OBBranchAndFinancialInstitutionIdentification6
        updateOBBranchAndFinancialInstitutionIdentification6 (OBBranchAndFinancialInstitutionIdentification6 creditorAgent) throws PersistenceException;

    public void deleteOBBranchAndFinancialInstitutionIdentification6 (String creditorAgentId) throws PersistenceException, NoSuchElementException;

}
