package com.example.demo.services;

import com.example.demo.entities.Pago;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagoService {
   
	public void createPago(Pago pago);
	public Iterable<Pago> readAll();
	public void update(long id);
	public void delete(long id);
	public Pago findById(long id);

	public int FindAllByChequeId(String chequeId);
	public void Save(Pago pago);
	public Page<Pago> FindAllByChequeForUser(String chequeId, Pageable pageable);
}
