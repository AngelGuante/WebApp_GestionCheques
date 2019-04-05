package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="pagos")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	private String montoPagado;
	private String usuario;

	//	USUARIO QUE CREO EL PAGO
	private String CreadaPor;

	//	CHEQUE AL QUE PERTENECE EL PAGO
	private String cheeque;

//	NO SE ESTA UTILIZANDO POR EL MOMENTO
	// @OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	// @JoinTable(name="cheque_pago", joinColumns=@JoinColumn(name="cheque_id"),
	// inverseJoinColumns =@JoinColumn(name="pago_id"))
	// private Cheque cheque;

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
	 * @return the createAt
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * @param createAt the createAt to set
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * @return the montoPagado
	 */
	public String getMontoPagado() {
		return montoPagado;
	}

	/**
	 * @param montoPagado the montoPagado to set
	 */
	public void setMontoPagado(String montoPagado) {
		this.montoPagado = montoPagado;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the cheeque
	 */
	public String getCheeque() {
		return cheeque;
	}

	/**
	 * @param cheeque the cheeque to set
	 */
	public void setCheeque(String cheeque) {
		this.cheeque = cheeque;
	}

	/**
	 * @return the creadaPor
	 */
	public String getCreadaPor() {
		return CreadaPor;
	}

	/**
	 * @param creadaPor the creadaPor to set
	 */
	public void setCreadaPor(String creadaPor) {
		CreadaPor = creadaPor;
	}

	/**
	 * @return the cheque
	 */
	// public Cheque getCheque() {
	// 	return cheque;
	// }

	/**
	 * @param cheque the cheque to set
	 */
	// public void setCheque(Cheque cheque) {
	// 	this.cheque = cheque;
	// }
	


}
