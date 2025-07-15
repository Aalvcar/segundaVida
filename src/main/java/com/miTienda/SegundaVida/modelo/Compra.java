package com.miTienda.SegundaVida.modelo;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Antonio Álvarez
 */
@Entity
@Table(name = "compra")
@EntityListeners(AuditingEntityListener.class)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;

    @ManyToOne
    private Usuario propietario;

    public Compra() {
    }

    public Compra(Usuario propietario) {
        this.propietario = propietario;
    }

    public long getId() {
        return id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.fechaCompra);
        hash = 41 * hash + Objects.hashCode(this.propietario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compra other = (Compra) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fechaCompra, other.fechaCompra)) {
            return false;
        }
        return Objects.equals(this.propietario, other.propietario);
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", fechaCompra=" + fechaCompra + ", propietario=" + propietario + '}';
    }

    
}
