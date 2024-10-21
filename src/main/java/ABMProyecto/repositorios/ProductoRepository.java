package ABMProyecto.repositorios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ABMProyecto.domain.Cliente;
import ABMProyecto.domain.Producto;
import ABMProyecto.domain.Proveedor;
import ABMProyecto.domain.Tipo;
public interface ProductoRepository extends JpaRepository <Producto,Long>{
    public List<Producto>findByCliente(Cliente cliente);
    public List<Producto>findByProveedor(Proveedor proveedor);
    public List<Producto>findByTipo(Tipo tipo);
}
