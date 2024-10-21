package ABMProyecto.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ABMProyecto.domain.Cliente;
import ABMProyecto.repositorios.ClienteRepository;
@Service
public class ClienteServiceImplmen implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    public Cliente añadir(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerporId(long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente editar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void borrar(long id) {
            clienteRepository.deleteById(id);
        }
    }
    

