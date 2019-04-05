package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.demo.entities.Cheque;

public interface IChequeRepository extends PagingAndSortingRepository<Cheque, Long> {
    // OBTIENE LA CANTIDAD DE DIAS ENTRE DOS FECHAS
    @Query(value = "SELECT DATEDIFF(?2, ?1) as dias", nativeQuery = true)
    public int DaysBetweenDates(String fechaInicio, String fechaFin);

    // BUSCARA LOS CHEQUES POR SU CODIGO
    @Query(value = "SELECT * FROM Cheques where codigo like %?1% AND activo = 1", nativeQuery = true)
    public Page<Cheque> FindAllByParameters(String texto, Pageable pageable);

    // BUSCARA TODOS LOS CHEQUES ACTIVOS
    @Query(value = "SELECT * FROM Cheques where activo = 1", nativeQuery = true)
    public Page<Cheque> FindActivos(Pageable pageable);
}