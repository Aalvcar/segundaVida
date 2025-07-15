package com.miTienda.SegundaVida.servicios;

import com.miTienda.SegundaVida.modelo.Compra;
import com.miTienda.SegundaVida.modelo.Producto;
import com.miTienda.SegundaVida.modelo.Usuario;
import com.miTienda.SegundaVida.repositorios.ProductoRepository;
import com.miTienda.SegundaVida.upload.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    ProductoRepository repo;

    @Autowired
    StorageService storageService;

    public void borrar(long id) {
        Producto producto = repo.getById(id);
        if (producto.getImagen() != null)
            storageService.delete(producto.getImagen());
        repo.deleteById(id);
    }

    public void borrar(Producto p) {
        if (!p.getImagen().isEmpty())
            storageService.delete(p.getImagen());
        repo.delete(p);
    }

    public Producto editar(Producto p) {
        return repo.save(p);
    }

    public Producto findById(long id) {
        return repo.findById(id).orElse(null);
    }

    public Producto insertar(Producto p) {
        return repo.save(p);
    }

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public List<Producto> productosDeUnPropietario(Usuario u) {
        return repo.findByPropietario(u);
    }

    public List<Producto> productosDeUnaCompra(Compra c) {
        return repo.findByCompra(c);
    }

    public List<Producto> productosSinVender() {
        return repo.findByCompraIsNull();
    }

    public List<Producto> buscar(String query) {
        return repo.findByNombreContainsIgnoreCaseAndCompraIsNull(query);
    }

    public List<Producto> buscarMisProductos(String query, Usuario u) {
        return repo.findByNombreContainsIgnoreCaseAndPropietario(query, u);
    }

    public List<Producto> variosPorId(List<Long> ids) {
        return repo.findAllById(ids);
    }

}
