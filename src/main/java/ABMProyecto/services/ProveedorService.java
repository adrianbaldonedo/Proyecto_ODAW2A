package ABMProyecto.services;
import java.util.List;
import ABMProyecto.domain.Proveedor;
public interface ProveedorService {
    Proveedor añadir(Proveedor Proveedor); 
    List<Proveedor> obtenerTodos(); 
    Proveedor obtenerporId(long id); 
    Proveedor editar(Proveedor Proveedor);
     void borrar(long id);
    }
   
    
    
    
    

