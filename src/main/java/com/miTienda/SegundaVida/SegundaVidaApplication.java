package com.miTienda.SegundaVida;

import com.miTienda.SegundaVida.modelo.Producto;
import com.miTienda.SegundaVida.modelo.Usuario;
import com.miTienda.SegundaVida.repositorios.ProductoRepository;
import com.miTienda.SegundaVida.repositorios.UsuarioRepository;

import java.util.Arrays;
import java.util.List;

import com.miTienda.SegundaVida.servicios.ProductoServicio;
import com.miTienda.SegundaVida.servicios.UsuarioServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SegundaVidaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SegundaVidaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
        return args -> {

            Usuario usuario = new Usuario("Antonio", "Álvarez Cárdenas", null, "aac1@correo.com", "1234");
            usuario = usuarioServicio.registrar(usuario);

            Usuario usuario2 = new Usuario("Juan", "Melón Cárdenas", null, "aac2@correo.com", "1234");
            usuario2 = usuarioServicio.registrar(usuario2);

            List<Producto> listado = Arrays.asList(
                    new Producto("Bicicleta de montaña", 1325.49f,
                            "https://bike-ocasion.com/255058-superlarge_default/specialized-epic-ht-tm.jpg", usuario2),
                    new Producto("Golf GTI Serie 2", 2500.0f,
                            "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg",
                            usuario),
                    new Producto("Raqueta de tenis", 10.5f, "https://adubasport.com/wp-content/uploads/2025/01/WR168510U_0_ROLAND_GARROS_EQUIPE_HP_Navy_Orange.png.cq5dam.web_.1000.1000.jpeg", usuario),
                    new Producto("Xbox One X", 425.0f, "https://m.media-amazon.com/images/I/61igALxr2pL._AC_SY741_.jpg", usuario2),
                    new Producto("Tripode flexible", 15.95f, "https://m.media-amazon.com/images/I/61TeXDokAfL._AC_SX679_.jpg", usuario),
                    new Producto("Iphone 13 128 GB", 850.50f, "https://m.media-amazon.com/images/I/5135ysvcVEL._AC_SX679_.jpg", usuario2));

            listado.forEach(productoServicio::insertar);
        };
    }
}
