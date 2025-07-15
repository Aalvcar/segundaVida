package com.miTienda.SegundaVida.repositorios;

import com.miTienda.SegundaVida.modelo.Compra;
import com.miTienda.SegundaVida.modelo.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antonio Álvarez
 */
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List <Compra> findByPropietario(Usuario propietario);
    
}
