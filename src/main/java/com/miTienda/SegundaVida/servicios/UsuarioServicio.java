package com.miTienda.SegundaVida.servicios;

import com.miTienda.SegundaVida.modelo.Usuario;
import com.miTienda.SegundaVida.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    BCryptPasswordEncoder passEncoder;

    public Usuario registrar(Usuario u) {
        u.setPassword(passEncoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public Usuario findById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Usuario findByEmail(String email) {
        return repo.findFirstByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repo.findFirstByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
