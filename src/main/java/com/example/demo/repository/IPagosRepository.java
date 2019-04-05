package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Pago;

@Repository
public interface IPagosRepository extends PagingAndSortingRepository<Pago, Long> {
   // BUSCA LA SUMATORIA DE TODOS LOS PAGOS DE UN CHEQUE EN ESPESIFICO
   @Query(value = "SELECT COUNT(*) FROM pagos WHERE cheeque = ?1", nativeQuery = true)
   public int FindAllByChequeId(String chequeId);

      // BUSCA TODOS LOS PAGOS HECHOS POS UN USUARIO EN ESPESIFICO
      @Query(value = "SELECT * FROM pagos WHERE creada_por = ?1", nativeQuery = true)
      public Page<Pago> FindAllByChequeForUser(String chequeId, Pageable pageable);
	
   Pago findByUsuario(String usuario);

}
