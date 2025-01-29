package ABMProyecto.services;
import java.util.List;

import ABMProyecto.domain.Cliente;
public interface ClienteService {
    Cliente añadir(Cliente cliente); 
    List<Cliente> obtenerTodos(); 
    Cliente obtenerporId(long id); 
    Cliente editar(Cliente cliente);
     void borrar(long id);
    }
   
    
    
    
    

