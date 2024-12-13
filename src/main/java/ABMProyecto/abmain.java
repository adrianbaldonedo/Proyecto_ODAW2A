package ABMProyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ABMProyecto.domain.Cliente;
import ABMProyecto.domain.Producto;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.domain.Tipo;
import ABMProyecto.domain.Usuario;
import ABMProyecto.domain.Rol;
import ABMProyecto.services.ClienteService;
import ABMProyecto.services.ProductoService;
import ABMProyecto.services.ProveedorService;
import ABMProyecto.services.TipoService;
import ABMProyecto.services.UsuarioService;

@SpringBootApplication
public class abmain {

	public static void main(String[] args) {
		SpringApplication.run(abmain.class, args);
	}
	@Bean
	CommandLineRunner initData(ProductoService productoService,ClienteService clienteservice,ProveedorService proveedorService,TipoService tipoService,UsuarioService usuarioService ){
		return args -> {
			Usuario us1=usuarioService.añadir(
					new Usuario(1L, "Adrian", "1234", Rol.ADMINISTRADOR));
			Usuario us2=usuarioService.añadir(
					new Usuario(2L, "user", "1234", Rol.USUARIO));
			Cliente clien1=clienteservice.añadir(new Cliente(1L,"Patricia"));
			Cliente client2=clienteservice.añadir(new Cliente(2L,"Juan"));
			Cliente client3=clienteservice.añadir(new Cliente(3L,"Monica"));
			Proveedor proveed1=proveedorService.añadir(new Proveedor(1L,"Martinez"));
			Proveedor proveed2=proveedorService.añadir(new Proveedor(2L,"Ochoa"));
			Tipo tip1=tipoService.añadir(new Tipo(1L,"Pan"));
			Tipo tip2=tipoService.añadir(new Tipo(2L,"Pasteles"));
			productoService.añadir(new Producto(1L,"Barra",5,clien1,proveed1,tip1));
			productoService.añadir(new Producto(2L,"Tarta",50,client2,proveed2,tip2));
		};
	}

}
