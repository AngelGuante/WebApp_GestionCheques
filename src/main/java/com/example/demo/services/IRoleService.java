package com.example.demo.services;

import com.example.demo.entities.SecurityRole;

public interface IRoleService {

    public void createRole(SecurityRole role);
    public Iterable<SecurityRole> readAll();
    public void updateRole(long id);
    public void deleteRole(long id);

    public SecurityRole findById(long id);

}
