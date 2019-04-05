package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
import com.example.demo.entities.SecurityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<SecurityUser, Long> {
    SecurityUser findByUsername(String username);

    // QUERY QUE BUSCA A TODOS LOS USUARIOS
    @Query(value = "SELECT * FROM security_user", nativeQuery = true)
    public Page<SecurityUser> FindAllUsers(Pageable pageable);

    // QUERY QUE BUSCA A TODOS LOS USUARIOS POR SU NOMBRE, APELLIDO y NOMBRE DE
    // USUARIO
    @Query(value = "SELECT * FROM security_user WHERE firts_name LIKE %?1% || last_name LIKE %?1% || username LIKE %?1%", nativeQuery = true)
    public Page<SecurityUser> FindUsersByParameters(String texto, Pageable pageable);
}