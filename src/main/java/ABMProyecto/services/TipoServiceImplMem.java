package ABMProyecto.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ABMProyecto.domain.Tipo;
import ABMProyecto.repositorios.TipoRepository;
@Service
public class TipoServiceImplMem implements TipoService {
    @Autowired
    TipoRepository tipoRepository;
    public Tipo añadir(Tipo tipo) {
        tipoRepository.save(tipo);
        return tipo;
    }
    public List<Tipo> obtenerTodos() {
        return tipoRepository.findAll();
    }

    public Tipo obtenerporId(long id) {
        return tipoRepository.findById(id).orElse(null);
    }

    public Tipo editar(Tipo Tipo) {
        return tipoRepository.save(Tipo);
    }

    public void borrar(long id) {
            tipoRepository.deleteById(id);
        }
    }
    

