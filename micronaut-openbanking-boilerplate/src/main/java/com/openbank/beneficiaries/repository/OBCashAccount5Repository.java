package com.openbank.beneficiaries.repository;

import com.openbank.beneficiaries.model.OBCashAccount5;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Repository
public interface OBCashAccount5Repository extends CrudRepository<OBCashAccount5, String> {

    @Override
    public List<OBCashAccount5> findAll() ;
}
