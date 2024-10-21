package ABMProyecto.services;
import java.util.List;
import ABMProyecto.domain.Producto;
public interface ProductoService {
     Producto añadir(Producto producto); 
     List<Producto> obtenerTodos(); 
     Producto obtenerporId(long id); 
     Producto editar(Producto producto);
     void borrar(long id);
     List<Producto>obtenerPorCliente(Long idCliente);
     List<Producto>obtenerPorProveedor(Long idProveedor);
     List<Producto>obtenerPorTipo(Long idTipo);
    }
   
    
    
    
    

