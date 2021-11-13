package com.openbank.beneficiaries.repository;

import com.openbank.beneficiaries.model.OBBeneficiary5;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Slice;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/*
 * Created by Anas Abu-Hussein
 * on 24/05/2021
 * */

@Repository
public interface OBBeneficiary5Repository extends CrudRepository<OBBeneficiary5, String> {


    @Override
    public List<OBBeneficiary5> findAll();

    public Slice<OBBeneficiary5> findAll (Pageable pageable);

    public Optional<OBBeneficiary5> findByAccountId (@NotNull @NotEmpty String accountId);

}
