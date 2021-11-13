package com.openbank.beneficiaries.repository;

import com.openbank.beneficiaries.model.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
