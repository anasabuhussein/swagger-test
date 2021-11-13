package com.openbank.beneficiaries.service;

import com.openbank.beneficiaries.model.OBBeneficiary5;
import com.openbank.beneficiaries.model.OBReadBeneficiary5;
import io.micronaut.data.model.Pageable;

import javax.persistence.PersistenceException;
import java.security.Principal;
import java.util.NoSuchElementException;


/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

public interface OBBeneficiary5Service {

    public OBReadBeneficiary5 findAll () throws PersistenceException;

    public OBReadBeneficiary5 findAll (Pageable pageable, Principal principal) throws PersistenceException;

    public OBReadBeneficiary5 findByAccountId (String accountId, Principal principal) throws NoSuchElementException;

    public OBReadBeneficiary5 findAll (Pageable pageable) throws PersistenceException;

    public OBReadBeneficiary5 findByAccountId (String accountId) throws NoSuchElementException;

    public OBReadBeneficiary5 findByBeneficiaryId (String beneficiaryId) throws NoSuchElementException;

    public OBBeneficiary5 createBeneficiary(OBBeneficiary5 beneficiary) throws PersistenceException;

    public OBBeneficiary5 updateBeneficiary (OBBeneficiary5 beneficiary) throws PersistenceException, NoSuchElementException;

    public OBBeneficiary5 updateBeneficiary(String beneficiaryId, OBBeneficiary5 beneficiary) throws PersistenceException, NoSuchElementException;

    public void deleteBeneficiaryById (String beneficiaryId) throws PersistenceException, NoSuchElementException;

}
