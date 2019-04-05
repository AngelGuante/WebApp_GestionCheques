// CLASE QUE CONTIENE LOS ATRIBUTOS PARA EL MANEJO DE ROLES
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

@Data
@Entity
@Table(name="security_role")
public class SecurityRole implements Serializable {

    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String rol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "SecurityRole{" +
                "id=" + id +
                ", rol='" + rol + '\'' +
                '}';
    }
}
