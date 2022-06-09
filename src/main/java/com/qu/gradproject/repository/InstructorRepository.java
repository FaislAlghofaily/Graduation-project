package com.qu.gradproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.InstructorEntity;

@Repository
public interface InstructorRepository extends CrudRepository<InstructorEntity,Long> {

}
