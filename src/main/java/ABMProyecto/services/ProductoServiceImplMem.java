package ABMProyecto.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ABMProyecto.domain.Cliente;
import ABMProyecto.domain.Producto;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.domain.Tipo;
import ABMProyecto.repositorios.ClienteRepository;
import ABMProyecto.repositorios.ProductoRepository;
import ABMProyecto.repositorios.ProveedorRepository;
import ABMProyecto.repositorios.TipoRepository;
@Service
public class ProductoServiceImplMem implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    TipoRepository tipoRepository;
    public Producto añadir(Producto producto) {
        productoRepository.save(producto);
        return producto;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerporId(long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto editar(Producto producto) {

        Producto productoExistente = productoRepository.findById(producto.getId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());

        return productoRepository.save(productoExistente);
    }

    public void borrar(long id) {
      productoRepository.deleteById(id);
    }
    public void borrarPorCliente(Long idCliente) {
        Cliente cliente=clienteRepository.findById(idCliente).orElse(null);

        if (cliente != null) {
            List<Producto> productos = productoRepository.findByCliente(cliente);

            for (Producto producto : productos) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }
    public List<Producto>obtenerPorCliente(Long idCliente){
        Cliente cliente=clienteRepository.findById(idCliente).orElse(null);
        if(cliente !=null)
        return productoRepository.findByCliente(cliente);
        return null;
    }
    public List<Producto>obtenerPorProveedor(Long idProveedor){
        Proveedor proveedor=proveedorRepository.findById(idProveedor).orElse(null);
        if(proveedor !=null)
        return productoRepository.findByProveedor(proveedor);
        return null;
    }
    public List<Producto>obtenerPorTipo(Long idTipo){
        Tipo tipo=tipoRepository.findById(idTipo).orElse(null);
        if(tipo !=null)
        return productoRepository.findByTipo(tipo);
        return null;
    }
}
    
  
    

