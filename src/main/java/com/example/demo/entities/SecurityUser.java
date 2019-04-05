// CLASE QUE CONTIENE LOS ATRIBUTOS PARA EL MANEJO DE USUARIOS
//
//
// ***************************************************************************/
// NOTAS:
//
//
// ***************************************************************************/
//

package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="security_user")
public class SecurityUser implements Serializable {

    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length=30 , unique=true)
    private String username;

    @Column(name="firts_name")
    private String firtsName;

    @Column(name="last_name")
    private String lastName;

    private String password;
    
    @Column(length=60)
    private String passwordEncoder;

    private String contacto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id") )
    private List<SecurityRole> roles;
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecurityRole> roles) {
        this.roles = roles;
    }

    /**
     * @return the passwordEncoder
     */
    public String getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * @param passwordEncoder the passwordEncoder to set
     */
    public void setPasswordEncoder(String passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    
}
