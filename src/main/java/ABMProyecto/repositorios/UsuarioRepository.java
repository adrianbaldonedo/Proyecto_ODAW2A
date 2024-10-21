package ABMProyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ABMProyecto.domain.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
     Usuario findByNombre(String nombre);
    //  Usuario findByFechaRegistro(LocalDate fecharegistro);
} 
    

