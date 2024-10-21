package ABMProyecto.services;
import java.util.List;
import ABMProyecto.domain.Usuario;
import ABMProyecto.dto.UsuarioDto;
public interface UsuarioService {
    Usuario añadir(Usuario usuario);
    List<Usuario> obtenerTodos();
    Usuario obtenerporId(long id);
    Usuario editar(Usuario usuario);
    void borrar(long id);
    void cambiarContraseña(UsuarioDto usuariodDto);
    void editarperfil(UsuarioDto usuarioDto);
    Usuario obtenerUsuarioConectadio();
}
