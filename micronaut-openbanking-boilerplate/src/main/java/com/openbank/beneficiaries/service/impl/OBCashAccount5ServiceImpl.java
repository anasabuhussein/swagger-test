package com.openbank.beneficiaries.service.impl;

import com.openbank.beneficiaries.model.OBCashAccount5;
import com.openbank.beneficiaries.repository.OBCashAccount5Repository;
import com.openbank.beneficiaries.service.OBCashAccount5Service;
import io.micronaut.transaction.annotation.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Singleton
public class OBCashAccount5ServiceImpl implements OBCashAccount5Service {

    private static final Logger log = LoggerFactory.getLogger(OBCashAccount5ServiceImpl.class.getName());
    private final OBCashAccount5Repository obCashAccount5Repository;

    public OBCashAccount5ServiceImpl (OBCashAccount5Repository obCashAccount5Repository) {

        this.obCashAccount5Repository = obCashAccount5Repository;
    }

    @Override
    public OBCashAccount5 findOBCashAccountById(String accountId) throws NoSuchElementException {

        log.trace("# findOBCashAccountById(String accountId)  {}:" + accountId);
        return obCashAccount5Repository.findById(accountId).get();
    }

    public List<OBCashAccount5> findAllAccounts () {

        log.trace("# findAllAccounts () {}:");
        return obCashAccount5Repository.findAll();
    }

    @Override
    public OBCashAccount5 createOBCashAccount(OBCashAccount5 obCashAccount) throws PersistenceException {

        log.trace("# createOBCashAccount(OBCashAccount5 obCashAccount) {}:");
        return obCashAccount5Repository.save(obCashAccount);
    }

    @Override
    public OBCashAccount5 updateOBCashAccount(OBCashAccount5 obCashAccount) throws PersistenceException {

        log.trace("# updateOBCashAccount(OBCashAccount5 obCashAccount) {}:");
        return obCashAccount5Repository.update(obCashAccount);
    }

    @Override
    public void deleteOBCashAccount5(String accountId) throws PersistenceException, NoSuchElementException {

        log.trace("# deleteOBCashAccount5(String accountId) {}:" + accountId);
        obCashAccount5Repository.delete(findOBCashAccountById(accountId));
    }
}
