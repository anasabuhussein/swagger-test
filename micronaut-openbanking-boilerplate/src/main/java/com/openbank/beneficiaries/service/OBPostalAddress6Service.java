package com.openbank.beneficiaries.service;

import com.openbank.beneficiaries.model.OBPostalAddress6;

import javax.persistence.PersistenceException;
import java.util.NoSuchElementException;

public interface OBPostalAddress6Service {

    public OBPostalAddress6 findOBPostalAddress6 (long id) throws NoSuchElementException;
    public OBPostalAddress6 saveOBPostalAddress6 (OBPostalAddress6 obPostalAddress) throws PersistenceException;
    public OBPostalAddress6 updateOBPostalAddress6 (OBPostalAddress6 obPostalAddress) throws PersistenceException;
    public void deleteOBPostalAddress6 (long id) throws PersistenceException, NoSuchElementException;

}
