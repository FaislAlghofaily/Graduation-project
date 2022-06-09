package com.qu.gradproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qu.gradproject.entity.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity,Long> {
	

}
