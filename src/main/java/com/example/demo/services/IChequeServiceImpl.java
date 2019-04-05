package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import com.example.demo.entities.Cheque;
import com.example.demo.repository.IChequeRepository;

@Service
public class IChequeServiceImpl implements IChequeService {

	@Autowired
	IChequeRepository repo;

	@Override
	public void createCheque(Cheque cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Cheque> readAllcheques() {
		return repo.findAll();
	}

	@Override
	public void updateCheque(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCheque(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cheque getClienteById(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void Save(Cheque cliente) {
		repo.save(cliente);
	}

	@Override
	@Transactional
	public Page<Cheque> FindActivos(Pageable pageable) {
		return repo.FindActivos(pageable);
	}

	@Override
	@Transactional
	public Page<Cheque> FindAllByParameters(String texto, Pageable pageable) {
		return repo.FindAllByParameters(texto, pageable);
	}

	@Override
	@Transactional
	public Cheque FindById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public int DaysBetweenDates(String fechaInicio, String fechaFin) {
		return repo.DaysBetweenDates(fechaInicio, fechaFin);
	}

	@Override
	@Transactional
	public void save(Cheque cheque) {
		repo.save(cheque);
	}
}
