package com.example.demo.services;

import com.example.demo.entities.Cheque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IChequeService {

	public void createCheque(Cheque cliente);
	public Iterable<Cheque> readAllcheques();
	public void updateCheque(long id);
	public void deleteCheque(long id);
	
	public void save(Cheque cheque);
	public Cheque getClienteById(long id);
	public void Save(Cheque cliente);
	public Page<Cheque> FindActivos(Pageable pageable);
	public Page<Cheque> FindAllByParameters(String texto, Pageable pageable);
	public Cheque FindById(Long id);
	public int DaysBetweenDates(String fechaInicio, String fechaFin);
}
