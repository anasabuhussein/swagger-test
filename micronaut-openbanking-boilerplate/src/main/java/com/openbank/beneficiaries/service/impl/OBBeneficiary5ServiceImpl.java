package com.openbank.beneficiaries.service.impl;

import com.openbank.beneficiaries.model.*;
import com.openbank.beneficiaries.repository.OBBeneficiary5Repository;
import com.openbank.beneficiaries.service.OBBeneficiary5Service;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Singleton
public class OBBeneficiary5ServiceImpl implements OBBeneficiary5Service {

    private static final Logger log = LoggerFactory.getLogger(OBBeneficiary5ServiceImpl.class.getName());
    private final OBBeneficiary5Repository beneficiary5Repository;
    private final OBCashAccount5ServiceImpl cashAccount5Service;
    private final OBBranchAndFinancialInstitutionIdentification6ServiceImpl branchAndFinancialInstitutionIdentification6Service;
    private final UserServiceImpl userService;

    public OBBeneficiary5ServiceImpl(OBBeneficiary5Repository beneficiary5Repository,
                                     OBCashAccount5ServiceImpl cashAccount5Service,
                                     OBBranchAndFinancialInstitutionIdentification6ServiceImpl branchAndFinancialInstitutionIdentification6Service,
                                     UserServiceImpl userService) {

        this.beneficiary5Repository = beneficiary5Repository;
        this.cashAccount5Service = cashAccount5Service;
        this.branchAndFinancialInstitutionIdentification6Service = branchAndFinancialInstitutionIdentification6Service;
        this.userService = userService;
    }

    @Override
    public OBReadBeneficiary5 findAll() throws PersistenceException {

        OBReadBeneficiary5 readBeneficiary5 = createOBReadBeneficiary5Object();
        beneficiary5Repository.findAll().forEach(new Consumer<OBBeneficiary5>() {
            @Override
            public void accept(OBBeneficiary5 beneficiary5) {
                readBeneficiary5.getData()
                        .getBeneficiary()
                        .add(beneficiary5);
            }
        });

        log.trace("# findAll () {} :" + readBeneficiary5.getData().getBeneficiary().size());
        return readBeneficiary5;
    }

    @Override
    public OBReadBeneficiary5 findAll(Pageable pageable, Principal principal) throws PersistenceException {

        OBReadBeneficiary5 readBeneficiary5 = createOBReadBeneficiary5Object();
        Slice<OBBeneficiary5> beneficiaries = beneficiary5Repository.findAll(pageable);
        User user = userService.findUser(principal.getName());

        beneficiaries.forEach(new Consumer<OBBeneficiary5>() {
            @Override
            public void accept(OBBeneficiary5 beneficiary5) {

                // Don' return data when user dose bot have Privileges.
                if(user.getPrivileges() == UserPrivileges.UnReadBeneficiariesDetail){
                    beneficiary5.setCreditorAccount(null);
                    beneficiary5.setCreditorAgent(null);
                }

                // Fill Data to List
                readBeneficiary5.getData()
                        .getBeneficiary()
                        .add(beneficiary5);

                // get Total Pages
                readBeneficiary5.getMate()
                        .setTotalPage(beneficiaries
                                .nextPageable()
                                .getNumber());
            }
        });

        log.trace("# findAll(Pageable pageable) {}: PageNumber: " + pageable.getNumber()
                + " size: " + getOBReadBeneficiary5DataSize (readBeneficiary5));

        return readBeneficiary5;
    }

    @Override
    public OBReadBeneficiary5 findByAccountId(String accountId, @NotNull Principal principal) throws NoSuchElementException{

        OBReadBeneficiary5 readBeneficiary5 = createOBReadBeneficiary5Object();
        User user = userService.findUser(principal.getName());

        beneficiary5Repository.findAll()
                .stream()
                .filter(beneficiary5 -> beneficiary5.getAccountId().equals(accountId))
                .forEach(new Consumer<OBBeneficiary5>() {
                    @Override
                    public void accept(OBBeneficiary5 beneficiary) {

                        // Don' return data when user dose bot have Privileges.
                        if(user.getPrivileges() == UserPrivileges.UnReadBeneficiariesDetail){
                            beneficiary.setCreditorAccount(null);
                            beneficiary.setCreditorAgent(null);
                        }

                        readBeneficiary5.getData()
                                .getBeneficiary()
                                .add(beneficiary);
                    }
                });

        log.trace("# findByAccountId(String accountId) {}: Account Id: " + accountId
        + " size of beneficiaries: " + getOBReadBeneficiary5DataSize (readBeneficiary5));

        return readBeneficiary5;
    }

    @Override
    public OBReadBeneficiary5 findAll(Pageable pageable) throws PersistenceException {
        OBReadBeneficiary5 readBeneficiary5 = createOBReadBeneficiary5Object();
        Slice<OBBeneficiary5> beneficiaries = beneficiary5Repository.findAll(pageable);

        beneficiaries.forEach(new Consumer<OBBeneficiary5>() {
            @Override
            public void accept(OBBeneficiary5 beneficiary5) {

                // Fill Data to List
                readBeneficiary5.getData()
                        .getBeneficiary()
                        .add(beneficiary5);

                // get Total Pages
                readBeneficiary5.getMate()
                        .setTotalPage(pageable
                                .next()
                                .getNumber());

            }
        });

        log.trace("# findAll(Pageable pageable) {}: PageNumber: " + pageable.getNumber()
                + " size: " + getOBReadBeneficiary5DataSize (readBeneficiary5));

        return readBeneficiary5;
    }

    @Override
    public OBReadBeneficiary5 findByAccountId(String accountId) throws NoSuchElementException {

        OBReadBeneficiary5 readBeneficiary5 = createOBReadBeneficiary5Object();
        beneficiary5Repository.findAll()
                .stream()
                .filter(beneficiary5 -> beneficiary5.getAccountId().equals(accountId))
                .forEach(new Consumer<OBBeneficiary5>() {
                    @Override
                    public void accept(OBBeneficiary5 beneficiary) {

                        readBeneficiary5.getData()
                                .getBeneficiary()
                                .add(beneficiary);
                    }
                });

        log.trace("# findByAccountId(String accountId) {}: Account Id: " + accountId
                + " size of beneficiaries: " + getOBReadBeneficiary5DataSize (readBeneficiary5));

        return readBeneficiary5;
    }

    @Override
    public OBReadBeneficiary5 findByBeneficiaryId(String beneficiaryId) throws NoSuchElementException {

        OBReadBeneficiary5 readBeneficiary5 = createOBReadBeneficiary5Object();
        Optional<OBBeneficiary5> beneficiary = beneficiary5Repository.findById(beneficiaryId);

        readBeneficiary5.getData()
                .getBeneficiary()
                .add(beneficiary.get());

        log.trace("# findByBeneficiaryId(String beneficiaryId) {}: beneficiaryId Id: " + beneficiaryId
        + " size: " + getOBReadBeneficiary5DataSize(readBeneficiary5));

        return readBeneficiary5;
    }

    @Override
    public OBBeneficiary5 createBeneficiary(OBBeneficiary5 beneficiary) throws PersistenceException {

        OBBeneficiary5 _beneficiary;

        OBCashAccount5 creditorAccount = beneficiary.getCreditorAccount();
        OBBranchAndFinancialInstitutionIdentification6 creditorAgent = beneficiary.getCreditorAgent();

       createBeneficiary_creditorAccount(creditorAccount);
       createBeneficiary_creditorAgent(creditorAgent);

        // TODO: Set Supplementary null always
        beneficiary.setSupplementaryData(null);

        _beneficiary = beneficiary5Repository.save(beneficiary);

        log.trace("# createBeneficiary(OBBeneficiary5 beneficiary) {}: Created _beneficiary with Id: " +
                _beneficiary.getBeneficiaryId());

        return _beneficiary;

    }

    @Override
    public OBBeneficiary5 updateBeneficiary(OBBeneficiary5 beneficiary) throws PersistenceException, NoSuchElementException {

        OBBeneficiary5 _beneficiary;
        OBCashAccount5 creditorAccount = beneficiary.getCreditorAccount();
        OBBranchAndFinancialInstitutionIdentification6 creditorAgent = beneficiary.getCreditorAgent();

        if(creditorAccount != null)
            cashAccount5Service.updateOBCashAccount(creditorAccount);

        if(creditorAgent != null)
            branchAndFinancialInstitutionIdentification6Service
                    .updateOBBranchAndFinancialInstitutionIdentification6(creditorAgent);

        // TODO: Set Supplementary null always
        beneficiary.setSupplementaryData(null);

        _beneficiary = beneficiary5Repository.update(beneficiary);

        log.trace("# updateBeneficiary(OBBeneficiary5 beneficiary) {}: updated _beneficiary with Id: " +
                _beneficiary.getBeneficiaryId());

        return _beneficiary;
    }

    @Override
    public OBBeneficiary5 updateBeneficiary(String beneficiaryId, OBBeneficiary5 beneficiary)
            throws PersistenceException, NoSuchElementException {

        OBBeneficiary5 _beneficiary = beneficiary5Repository.findById(beneficiaryId).get();
        OBCashAccount5 creditorAccount = beneficiary.getCreditorAccount();
        OBBranchAndFinancialInstitutionIdentification6 creditorAgent = beneficiary.getCreditorAgent();

        if(beneficiary.getAccountId() != null)
            _beneficiary.setAccountId(beneficiary.getAccountId());

        if(beneficiary.getBeneficiaryType() != null)
            _beneficiary.setBeneficiaryType(beneficiary.getBeneficiaryType());

        if(beneficiary.getReference() != null)
            _beneficiary.setReference(beneficiary.getReference());

        if (creditorAccount != null)
            cashAccount5Service.updateOBCashAccount(creditorAccount);

        if (creditorAgent != null)
            branchAndFinancialInstitutionIdentification6Service
                    .updateOBBranchAndFinancialInstitutionIdentification6(creditorAgent);

        // TODO: Set Supplementary null always
        beneficiary.setSupplementaryData(null);
        _beneficiary.setCreditorAccount(creditorAccount);
        _beneficiary.setCreditorAgent(creditorAgent);

        _beneficiary = beneficiary5Repository.update(_beneficiary);

        log.trace("# updateBeneficiary(String beneficiaryId, OBBeneficiary5 beneficiary) {}: updated _beneficiary with Id: " +
                _beneficiary.getBeneficiaryId());

        return _beneficiary;
    }

    @Override
    public void deleteBeneficiaryById(String beneficiaryId) throws PersistenceException, NoSuchElementException {

        beneficiary5Repository.delete(beneficiary5Repository
                .findById(beneficiaryId)
                .get());

        log.trace("# deleteBeneficiaryById(String beneficiaryId) {}: delete _beneficiary with Id: " + beneficiaryId);
    }

    public OBReadBeneficiary5 createOBReadBeneficiary5Object () {
        OBReadBeneficiary5 obReadBeneficiary5 = new OBReadBeneficiary5();
        return  obReadBeneficiary5;
    }

    public boolean OBReadBeneficiary5DataNotEmpty (List<OBBeneficiary5> beneficiaries) {
        if(beneficiaries.size() > 0)
            return true;

        return false;
    }

    public int getOBReadBeneficiary5DataSize (OBReadBeneficiary5 readBeneficiary) {
        return  readBeneficiary.getData().getBeneficiary().size();
    }

    public void createBeneficiary_creditorAccount(OBCashAccount5 creditorAccount) {

        OBCashAccount5 findCreditorAccount = null;
        if(creditorAccount != null){

            try{
                findCreditorAccount  = cashAccount5Service.findOBCashAccountById(creditorAccount.getIdentification());
            }catch (Exception e) {
                log.trace("# createBeneficiary_creditorAccount(OBCashAccount5 creditorAccount) {}: " + e.getLocalizedMessage());
            }

            if(findCreditorAccount == null)
                cashAccount5Service.createOBCashAccount(creditorAccount);
            else
                cashAccount5Service.updateOBCashAccount(creditorAccount);
        }
    }

    public void createBeneficiary_creditorAgent(OBBranchAndFinancialInstitutionIdentification6 creditorAgent) {

        OBBranchAndFinancialInstitutionIdentification6 findCreditorAgent = null;
        if(creditorAgent != null){
            try{
                findCreditorAgent  = branchAndFinancialInstitutionIdentification6Service
                        .findOBBranchAndFinancialInstitutionIdentification6ById(creditorAgent.getIdentification());
            }catch (Exception e) {
                log.trace("# createBeneficiary_creditorAgent(OBBranchAndFinancialInstitutionIdentification6 " +
                        "creditorAgent) {}: " + e.getLocalizedMessage());
            }

            if(findCreditorAgent == null)
                branchAndFinancialInstitutionIdentification6Service
                        .createOBBranchAndFinancialInstitutionIdentification6(creditorAgent);
            else
                branchAndFinancialInstitutionIdentification6Service
                        .updateOBBranchAndFinancialInstitutionIdentification6(creditorAgent);
        }
    }

}
