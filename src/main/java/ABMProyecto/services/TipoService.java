package ABMProyecto.services;
import java.util.List;
import ABMProyecto.domain.Tipo;
public interface TipoService {
    Tipo añadir(Tipo Tipo); 
    List<Tipo> obtenerTodos(); 
    Tipo obtenerporId(long id); 
    Tipo editar(Tipo Tipo);
    void borrar(long id);
    }
   
    
    
    
    

