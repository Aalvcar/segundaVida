package com.miTienda.SegundaVida.repositorios;

import com.miTienda.SegundaVida.modelo.Compra;
import com.miTienda.SegundaVida.modelo.Producto;
import com.miTienda.SegundaVida.modelo.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Antonio Álvarez
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByPropietario(Usuario propietario);

    List<Producto> findByCompra(Compra compra);

    List<Producto> findByCompraIsNull();

    List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull(String nombre);

    List<Producto> findByNombreContainsIgnoreCaseAndPropietario(String nombre, Usuario propietario);
}
