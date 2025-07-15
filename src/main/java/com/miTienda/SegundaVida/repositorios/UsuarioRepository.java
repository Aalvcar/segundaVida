package com.miTienda.SegundaVida.repositorios;

import com.miTienda.SegundaVida.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Antonio Álvarez
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findFirstByEmail(String email);
}
