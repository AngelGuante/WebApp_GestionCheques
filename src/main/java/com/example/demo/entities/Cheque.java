package com.example.demo.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="cheques")
@AllArgsConstructor
public class Cheque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String codigo;
	private String monto;

	private String montoRestante;

	@Column(name="fecha_de_emision")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private String fechaDeEmision;
	// @Column(name="no_cuenta")
    // private String noCuenta;
	
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="cliente_id" ,nullable=false)
    @OnDelete(action=OnDeleteAction.CASCADE)
	private Cliente cliente;
	
	private int activo;
    
    public Cheque() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the fechaDeEmision
	 */
	public String getFechaDeEmision() {
		return fechaDeEmision;
	}

	/**
	 * @param fechaDeEmision the fechaDeEmision to set
	 */
	public void setFechaDeEmision(String fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}

	/**
	 * @return the noCuenta
	 */
	// public String getNoCuenta() {
	// 	return noCuenta;
	// }

	/**
	 * @param noCuenta the noCuenta to set
	 */
	// public void setNoCuenta(String noCuenta) {
	// 	this.noCuenta = noCuenta;
	// }

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the montoRestante
	 */
	public String getMontoRestante() {
		return montoRestante;
	}

	/**
	 * @param montoRestante the montoRestante to set
	 */
	public void setMontoRestante(String montoRestante) {
		this.montoRestante = montoRestante;
	}

	/**
	 * @return the activo
	 */
	public int getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	
	
    
    
}
