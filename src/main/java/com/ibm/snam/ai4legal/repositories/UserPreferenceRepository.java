package com.ibm.snam.ai4legal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ibm.snam.ai4legal.model.UserPreference;

public interface UserPreferenceRepository extends CrudRepository<UserPreference, String>{

}
