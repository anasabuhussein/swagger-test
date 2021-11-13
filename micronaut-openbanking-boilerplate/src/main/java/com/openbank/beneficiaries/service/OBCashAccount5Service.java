package com.openbank.beneficiaries.service;

import com.openbank.beneficiaries.model.OBCashAccount5;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.NoSuchElementException;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

public interface OBCashAccount5Service {

    public OBCashAccount5 findOBCashAccountById (String accountId) throws NoSuchElementException;
    public OBCashAccount5 createOBCashAccount (OBCashAccount5 obCashAccount) throws PersistenceException;
    public OBCashAccount5 updateOBCashAccount (OBCashAccount5 obCashAccount) throws PersistenceException;
    public void deleteOBCashAccount5 (String accountId) throws PersistenceException, NoSuchElementException;

}
