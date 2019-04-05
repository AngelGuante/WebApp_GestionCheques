package com.example.demo.services;

import org.springframework.data.domain.Pageable;
import com.example.demo.entities.SecurityUser;
import org.springframework.data.domain.Page;

public interface IUserService {

    public void createUser(SecurityUser user);
    public Iterable<SecurityUser> readAll();
    public void updateUser(long id);
    public void deleteUser(long id);
    public SecurityUser findById(long id);

    public Page<SecurityUser> FindAllUsers(Pageable pageRequest);
    public Page<SecurityUser> FindUsersByParameters(String texto, Pageable pageRequest);
}
