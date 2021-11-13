package com.openbank.beneficiaries.service.impl;

import com.openbank.beneficiaries.model.OBPostalAddress6;
import com.openbank.beneficiaries.repository.OBPostalAddress6Repository;
import com.openbank.beneficiaries.service.OBPostalAddress6Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.util.NoSuchElementException;

@Singleton
public class OBPostalAddress6ServiceImpl implements OBPostalAddress6Service {

    private static final Logger log = LoggerFactory.getLogger(OBPostalAddress6ServiceImpl.class.getName());
    private final OBPostalAddress6Repository obPostalAddress6Repository;

    public OBPostalAddress6ServiceImpl(OBPostalAddress6Repository obPostalAddress6Repository) {
        this.obPostalAddress6Repository = obPostalAddress6Repository;
    }

    @Override
    public OBPostalAddress6 findOBPostalAddress6(long id) throws NoSuchElementException {

        log.trace("OBPostalAddress6 findOBPostalAddress6(long id) {} : " + id);
        return obPostalAddress6Repository.findById(id).get();
    }

    @Override
    public OBPostalAddress6 saveOBPostalAddress6(OBPostalAddress6 obPostalAddress) throws PersistenceException {

        log.trace("OBPostalAddress6 saveOBPostalAddress6(OBPostalAddress6 obPostalAddress) {} :");
        return obPostalAddress6Repository.save(obPostalAddress);
    }

    @Override
    public OBPostalAddress6 updateOBPostalAddress6(OBPostalAddress6 obPostalAddress) throws PersistenceException {

        log.trace("OBPostalAddress6 updateOBPostalAddress6(OBPostalAddress6 obPostalAddress) {} :");
        return obPostalAddress6Repository.update(obPostalAddress);
    }

    @Override
    public void deleteOBPostalAddress6(long id) throws PersistenceException, NoSuchElementException {

        log.trace("void deleteOBPostalAddress6(long id) {} : " + id );
        obPostalAddress6Repository
                .delete(obPostalAddress6Repository
                        .findById(id)
                        .get());
    }
}
