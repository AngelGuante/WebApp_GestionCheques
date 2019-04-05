package com.example.demo.services;

import com.example.demo.entities.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteServices {

	public Iterable<Cliente> readAllCliente();
	public void updateCliente(long id);
	public void deleteCliente(long id);
	
	public Cliente getClienteById(long id);

	public Cliente Save(Cliente cliente);
	public List<Cliente> FindAll();
	public Page<Cliente> FindAll(Pageable pageable);
	public Page<Cliente> FindAllByParameters(String texto, Pageable pageable);
	public Cliente FindById(Long id);
}
