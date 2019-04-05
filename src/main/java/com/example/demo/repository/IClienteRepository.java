package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Cliente;

@Repository
public interface IClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
    // QUERY QUE BUSCA A USUARIOS POR SU NOMBRE, APELLIDO Y CEDULA
    @Query(value = "SELECT * FROM Clientes where cedula LIKE %?1% || nombre LIKE %?1% || apellido LIKE %?1%", nativeQuery = true)
    public Page<Cliente> FindAllByParameters(String texto, Pageable pageable);
}
