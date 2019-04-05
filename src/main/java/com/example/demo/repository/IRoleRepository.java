package com.example.demo.repository;

import com.example.demo.entities.SecurityRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<SecurityRole , Long> {
}
