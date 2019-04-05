package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="clientes")
@AllArgsConstructor
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String cedula;
	private String nombre;
    private String apellido;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date Nacimiento;

    private String idiomas;
    private String Contacto;
    private String NivelAcademico;
    private String direccion;
    private String Conyuge;
    private String NombreMadre;
    private String CedulaMadre;
    private String NombrePadre;
    private String CedulaPadre;
    private String Hijos;
    
   /* @Column(name="fecha_de_nacimiento")
    private Date fechaDeNacimiento;
    
    @Column(name="nombre_del_conyugue")
    private String nombreDelConyuge;
    
    @Column(name="area_de_trabajo")
    private String areaDeTrabajo;
    
    @Column(name="nombre_de_la_madre")
    private String nombreDeLaMadre;
    
    @Column(name="cedula_de_la_madre")
    private String cedulaMadre;
    
    @Column(name="nombre_del_padre")
    private String nombreDelPadre;
    
    @Column(name="cedula_del_padre")
    private String cedulaPadre;*/
    
    public Cliente() {}

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the nacimiento
     */
    public Date getNacimiento() {
        return Nacimiento;
    }

    /**
     * @param nacimiento the nacimiento to set
     */
    public void setNacimiento(Date nacimiento) {
        Nacimiento = nacimiento;
    }

    /**
     * @return the contacto
     */
    public String getContacto() {
        return Contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    /**
     * @return the nivelAcademico
     */
    public String getNivelAcademico() {
        return NivelAcademico;
    }

    /**
     * @param nivelAcademico the nivelAcademico to set
     */
    public void setNivelAcademico(String nivelAcademico) {
        NivelAcademico = nivelAcademico;
    }

    /**
     * @return the conyuge
     */
    public String getConyuge() {
        return Conyuge;
    }

    /**
     * @param conyuge the conyuge to set
     */
    public void setConyuge(String conyuge) {
        Conyuge = conyuge;
    }

    /**
     * @return the nombreMadre
     */
    public String getNombreMadre() {
        return NombreMadre;
    }

    /**
     * @param nombreMadre the nombreMadre to set
     */
    public void setNombreMadre(String nombreMadre) {
        NombreMadre = nombreMadre;
    }

    /**
     * @return the cedulaMadre
     */
    public String getCedulaMadre() {
        return CedulaMadre;
    }

    /**
     * @param cedulaMadre the cedulaMadre to set
     */
    public void setCedulaMadre(String cedulaMadre) {
        CedulaMadre = cedulaMadre;
    }

    /**
     * @return the nombrePadre
     */
    public String getNombrePadre() {
        return NombrePadre;
    }

    /**
     * @param nombrePadre the nombrePadre to set
     */
    public void setNombrePadre(String nombrePadre) {
        NombrePadre = nombrePadre;
    }

    /**
     * @return the cedulaPadre
     */
    public String getCedulaPadre() {
        return CedulaPadre;
    }

    /**
     * @param cedulaPadre the cedulaPadre to set
     */
    public void setCedulaPadre(String cedulaPadre) {
        CedulaPadre = cedulaPadre;
    }

    /**
     * @return the idiomas
     */
    public String getIdiomas() {
        return idiomas;
    }

    /**
     * @param idiomas the idiomas to set
     */
    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    /**
     * @return the hijos
     */
    public String getHijos() {
        return Hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(String hijos) {
        Hijos = hijos;
    }
}
