package ABMProyecto.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.repositorios.ProveedorRepository;
@Service
public class ProveedorServiceImplMem implements ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;
    public Proveedor añadir(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return proveedor;
    }
    public List<Proveedor> obtenerTodos() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerporId(long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor editar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void borrar(long id) {
            proveedorRepository.deleteById(id);
        }
    }
    

