package ABMProyecto.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ABMProyecto.domain.Usuario;
import ABMProyecto.dto.UsuarioDto;
import ABMProyecto.repositorios.UsuarioRepository;
@Service
public class UsuarioServiceImplMem implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Usuario añadir(Usuario usuario){
        if (usuario.getPassword().length() < 4) {
            throw new RuntimeException("contraseña incorrecta");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
        }
        public List<Usuario> obtenerTodos(){
            return usuarioRepository.findAll();
        }
          public Usuario obtenerporId(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario editar(Usuario usuario) {
        if (usuario.getPassword().length() < 4) {
            throw new RuntimeException("conraseña incorrecta");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public void borrar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void cambiarContraseña(UsuarioDto usuariodDto) {
        Usuario usuario=usuarioRepository.findById(usuariodDto.getId()).orElse(null);
        usuario.setPassword(usuariodDto.getPassword());
        usuarioRepository.save(usuario);
   }

   public void editarperfil(UsuarioDto usuarioDto) {
       Usuario usuario = usuarioRepository.findByNombre(usuarioDto.getNombre());
       usuario.setNombre(usuarioDto.getNombre());
       usuarioRepository.save(usuario);
   }

   public Usuario obtenerUsuarioConectadio() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (!(authentication instanceof AnonymousAuthenticationToken)) {
           String nombreUsuarioCoenctado = authentication.getName();
           return usuarioRepository.findByNombre(nombreUsuarioCoenctado);
       }
       return null;
    }
@Override
public void borrar(long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'borrar'");
}
    
    }

