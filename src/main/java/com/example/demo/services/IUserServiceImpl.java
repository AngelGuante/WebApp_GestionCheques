package com.example.demo.services;

import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import com.example.demo.entities.SecurityUser;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    IUserRepository repo;

    @Override
    public void createUser(SecurityUser user) {
        if (user != null) {
            this.repo.save(user);
        }
    }

    @Override
    public Iterable<SecurityUser> readAll() {
        return this.repo.findAll();
    }

    @Override
    public void updateUser(long id) {
        SecurityUser user = null;

        if (id > 0) {
            user = this.repo.findById(id).orElse(null);
            this.repo.save(user);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void deleteUser(long id) {
        SecurityUser user = null;
        if (user != null) {
            user = this.repo.findById(id).orElse(null);
            this.repo.delete(user);
        }
    }

    @Override
    public SecurityUser findById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Page<SecurityUser> FindAllUsers(Pageable pageable) {
        return repo.FindAllUsers(pageable);
    }

    @Override
    @Transactional
    public Page<SecurityUser> FindUsersByParameters(String texto, Pageable pageRequest) {
        return repo.FindUsersByParameters(texto, pageRequest);
    }
}
