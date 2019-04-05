package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.example.demo.entities.Pago;
import com.example.demo.repository.IPagosRepository;

@Service
public class IPagoServiceImpl implements IPagoService {

	@Autowired
	private IPagosRepository repo;

	@Override
	public void createPago(Pago pago) {
		if (pago != null) {
			repo.save(pago);
		}
	}

	@Override
	public Iterable<Pago> readAll() {

		return repo.findAll();
	}

	@Override
	public void update(long id) {

		Pago pago = null;

		if (id > 0) {
			pago = repo.findById(id).orElse(null);
			repo.save(pago);
		}

	}

	@Override
	public void delete(long id) {

		Pago pago = null;

		if (id > 0) {
			pago = repo.findById(id).orElse(null);
			repo.delete(pago);
		}

	}

	@Override
	public Pago findById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public int FindAllByChequeId(String chequeId) {
		return repo.FindAllByChequeId(chequeId);
	}

	@Override
	@Transactional
	public void Save(Pago pago) {
		repo.save(pago);
	}

	@Override
	@Transactional
	public Page<Pago> FindAllByChequeForUser(String chequeId, Pageable pageable) {
		return repo.FindAllByChequeForUser(chequeId, pageable);
	}

}
