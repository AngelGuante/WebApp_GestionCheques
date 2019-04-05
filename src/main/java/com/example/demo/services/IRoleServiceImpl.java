package com.example.demo.services;

import com.example.demo.entities.SecurityRole;
import com.example.demo.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRoleServiceImpl implements  IRoleService {

    @Autowired
    IRoleRepository repo;

    @Override
    public void createRole(SecurityRole role) {
             if (role != null){
                 this.repo.save(role);
             }
    }

    @Override
    public Iterable<SecurityRole> readAll() {
        return  repo.findAll();
    }

    @Override
    public void updateRole(long id) {
          SecurityRole role = null;
          if ( id > 0){
              role = repo.findById(id).orElse(null);
              this.repo.save(role);
          }
    }

    @Override
    public void deleteRole(long id) {
        SecurityRole role = null;
        if ( id > 0){
            role = repo.findById(id).orElse(null);
            this.repo.delete(role);
        }
    }

    @Override
    public SecurityRole findById(long id) {
        return repo.findById(id).orElseGet(null);
    }
}
