package com.example.demo.models;
import jakarta.persistence.*;

@Entity
@Table(name="pedidos")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numeroPedido")
    private int numeroPedido;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="precio")
    private Double precio;

    @Column(name="estado")
    private String estado;

    @Column(name="comprador")
    private String comprador;

    @Column(name="detalleProductos")
    private String detalleProductos;

    public Long getId() {
        return id;
    }

    public Orders() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(String detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + numeroPedido;
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((comprador == null) ? 0 : comprador.hashCode());
        result = prime * result + ((detalleProductos == null) ? 0 : detalleProductos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Orders other = (Orders) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (numeroPedido != other.numeroPedido) return false;
        if (descripcion == null) {
            if (other.descripcion != null) return false;
        } else if (!descripcion.equals(other.descripcion)) return false;
        if (precio == null) {
            if (other.precio != null) return false;
        } else if (!precio.equals(other.precio)) return false;
        if (estado == null) {
            if (other.estado != null) return false;
        } else if (!estado.equals(other.estado)) return false;
        if (comprador == null) {
            if (other.comprador != null) return false;
        } else if (!comprador.equals(other.comprador)) return false;
        if (detalleProductos == null) {
            if (other.detalleProductos != null) return false;
        } else if (!detalleProductos.equals(other.detalleProductos)) return false;
        return true;
    }
}