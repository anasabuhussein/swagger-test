package com.openbank.beneficiaries.service.impl;

import com.openbank.beneficiaries.model.OBBranchAndFinancialInstitutionIdentification6;
import com.openbank.beneficiaries.model.OBPostalAddress6;
import com.openbank.beneficiaries.repository.OBBranchAndFinancialInstitutionIdentification6Repository;
import com.openbank.beneficiaries.service.OBBranchAndFinancialInstitutionIdentification6Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.util.NoSuchElementException;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Singleton
public class OBBranchAndFinancialInstitutionIdentification6ServiceImpl implements
        OBBranchAndFinancialInstitutionIdentification6Service {

    private static final Logger log = LoggerFactory
            .getLogger(OBBranchAndFinancialInstitutionIdentification6ServiceImpl
                    .class
                    .getName());

    private final OBBranchAndFinancialInstitutionIdentification6Repository
            branchAndFinancialInstitutionIdentification6Repository;

    private final OBPostalAddress6ServiceImpl postalAddress6Service;

    public OBBranchAndFinancialInstitutionIdentification6ServiceImpl(
            OBBranchAndFinancialInstitutionIdentification6Repository
                    branchAndFinancialInstitutionIdentification6Repository,
            OBPostalAddress6ServiceImpl postalAddress6Service) {

        this.branchAndFinancialInstitutionIdentification6Repository =
                branchAndFinancialInstitutionIdentification6Repository;
        this.postalAddress6Service = postalAddress6Service;
    }

    @Override
    public OBBranchAndFinancialInstitutionIdentification6
        findOBBranchAndFinancialInstitutionIdentification6ById(String creditorAgentId) throws NoSuchElementException {

        log.trace("# findOBBranchAndFinancialInstitutionIdentification6ById(String creditorAgentId) {}: " + creditorAgentId);

        return branchAndFinancialInstitutionIdentification6Repository
                .findById(creditorAgentId)
                .get();
    }

    @Override
    public OBBranchAndFinancialInstitutionIdentification6
        createOBBranchAndFinancialInstitutionIdentification6(OBBranchAndFinancialInstitutionIdentification6
                                                                     creditorAgent)  throws PersistenceException {

        log.trace("# createOBBranchAndFinancialInstitutionIdentification6(OBBranchAndFinancialInstitutionIdentification6 " +
                "creditorAgent) {}: " );
        postalAddress(creditorAgent.getPostalAddress());
        return branchAndFinancialInstitutionIdentification6Repository
                .save(creditorAgent);
    }

    @Override
    public OBBranchAndFinancialInstitutionIdentification6
        updateOBBranchAndFinancialInstitutionIdentification6(OBBranchAndFinancialInstitutionIdentification6
                                                                     creditorAgent) throws PersistenceException {

        log.trace("# updateOBBranchAndFinancialInstitutionIdentification6(OBBranchAndFinancialInstitutionIdentification6" +
                " creditorAgent) {}: " );
        postalAddress(creditorAgent.getPostalAddress());
        return branchAndFinancialInstitutionIdentification6Repository
                .update(creditorAgent);
    }

    @Override
    public void deleteOBBranchAndFinancialInstitutionIdentification6(String creditorAgentId)
            throws PersistenceException, NoSuchElementException{

        log.trace("# deleteOBBranchAndFinancialInstitutionIdentification6(String creditorAgentId) {}: " + creditorAgentId );

        branchAndFinancialInstitutionIdentification6Repository
                .delete(findOBBranchAndFinancialInstitutionIdentification6ById(creditorAgentId));
    }

    public void postalAddress (OBPostalAddress6 postalAddress6) {

        OBPostalAddress6 _postalAddress6 = null;
        if(postalAddress6 == null)
            return;

        try{
            _postalAddress6 = postalAddress6Service.findOBPostalAddress6(postalAddress6.getId());
        }catch (Exception e) {
            log.trace("updatePostalAddress (OBPostalAddress6 postalAddress6) {} " + e.getLocalizedMessage());
        }

        if (_postalAddress6 == null)
            postalAddress6Service.saveOBPostalAddress6(postalAddress6);
        else
            postalAddress6Service.updateOBPostalAddress6(postalAddress6);

    }
}
