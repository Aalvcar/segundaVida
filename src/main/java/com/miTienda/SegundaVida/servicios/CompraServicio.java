package com.miTienda.SegundaVida.servicios;

import com.miTienda.SegundaVida.modelo.Compra;
import com.miTienda.SegundaVida.modelo.Producto;
import com.miTienda.SegundaVida.modelo.Usuario;
import com.miTienda.SegundaVida.repositorios.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicio {

    @Autowired
    CompraRepository repo;

    @Autowired
    ProductoServicio prodServicio;

    public Compra insertar(Compra c, Usuario u) {
        c.setPropietario(u);
        return repo.save(c);
    }

    public Compra insertar(Compra c) {
        return repo.save(c);
    }

    public Producto addProductoCompra(Producto p, Compra c) {
        p.setCompra(c);
        return prodServicio.editar(p);
    }

    public Compra buscarPorId(long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Compra> todas() {
        return repo.findAll();
    }

    public List<Compra> porPropietario(Usuario u){
        return repo.findByPropietario(u);
    }


}
