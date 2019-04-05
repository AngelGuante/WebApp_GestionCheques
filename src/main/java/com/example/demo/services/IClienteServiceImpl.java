package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.demo.entities.Cliente;
import com.example.demo.repository.IClienteRepository;

@Service
public class IClienteServiceImpl implements IClienteServices {

	@Autowired
	IClienteRepository repo;

	// @Override
	// public void setCliente(Cliente cliente) {
	// repo.save(cliente);

	// }

	@Override
	public Iterable<Cliente> readAllCliente() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void updateCliente(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCliente(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente getClienteById(long id) {

		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<Cliente> FindAll() {
		return (List<Cliente>) repo.findAll();
	}

	@Override
	@Transactional
	public Page<Cliente> FindAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	@Transactional
	public Page<Cliente> FindAllByParameters(String texto, Pageable pageable) {
		return repo.FindAllByParameters(texto, pageable);
	}

	@Override
	@Transactional
	public Cliente Save(Cliente cliente) {
		return repo.save(cliente);
	}

	@Override
	@Transactional
	public Cliente FindById(Long id) {
		return repo.findById(id).orElse(null);
	}
}
