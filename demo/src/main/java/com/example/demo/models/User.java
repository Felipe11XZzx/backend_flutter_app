package com.example.demo.models;
import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name= "contrasena")
    private String contrasena;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "administrador")
    private Boolean administrador = false;

    @Column(name = "bloqueado")
    private Boolean bloqueado = false;

    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getAdministrador() {
        return administrador != null ? administrador : false;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador != null ? administrador : false;
    }

    public Boolean getBloqueado() {
        return bloqueado != null ? bloqueado : false;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado != null ? bloqueado : false;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((contrasena == null) ? 0 : contrasena.hashCode());
        result = prime * result + ((edad == null) ? 0 : edad.hashCode());
        result = prime * result + ((administrador == null) ? 0 : administrador.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (contrasena == null) {
            if (other.contrasena != null)
                return false;
        } else if (!contrasena.equals(other.contrasena))
            return false;
        if (edad == null) {
            if (other.edad != null)
                return false;
        } else if (!edad.equals(other.edad))
            return false;
        if (administrador == null) {
            if (other.administrador != null)
                return false;
        } else if (!administrador.equals(other.administrador))
            return false;
        return true;
    }
}
