package ABMProyecto.config;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticacionConfiguration)
        throws Exception{
            return authenticacionConfiguration.getAuthenticationManager();
        }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
         public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.headers(
            headersConfigurer -> headersConfigurer
                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
    http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/public/**","/producto","/idioma").permitAll()
            .requestMatchers("/contraseña**", "/contraseña/editar").authenticated()
            .requestMatchers("/producto/nuevo").hasAnyRole("ADMINISTRADOR", "MANAGER")
            .requestMatchers("/producto/editar/**").hasAnyRole("ADMINISTRADOR","MANAGER")
            .requestMatchers("/producto/borrar/**").hasRole("ADMINISTRADOR") // configurarpermisosreales
            .requestMatchers("/cliente/nuevo").hasRole("ADMINISTRADOR")//solo lo crea el administrador//
            .requestMatchers("/cliente/editar/**").hasRole("ADMINISTRADOR")
            .requestMatchers("/cliente/borrar/**").hasRole("ADMINISTRADOR") // configurarpermisosreales
            .requestMatchers("/proveedor/nuevo").hasRole("ADMINISTRADOR")
            .requestMatchers("/proveedor/editar/**").hasRole("ADMINISTRADOR")
            .requestMatchers("/proveedor/borrar/**").hasRole("ADMINISTRAOR")
            .requestMatchers("/tipo/nuevo").hasRole("ADMINISTRADOR")
            .requestMatchers("/tipo/editar/**").hasRole("ADMINISTRADOR")
            .requestMatchers("/tipo/borrar/**").hasRole("ADMINISTRAOR")
            // .requestMatchers("/perfil**","/perfil/editar").authenticated()
            // .requestMatchers("/usuarios/nuevo**", "/usuarios/editar/**").hasAnyRole("ADMINISTRADOR") // configurarpermisosreales
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
            .permitAll() // para rutas: /css, /js /images
            .anyRequest().permitAll())
            .formLogin(HttpSecurityFormLoginConfigurer -> HttpSecurityFormLoginConfigurer
                    .loginPage("/signin")
                    .loginProcessingUrl("/login")
                    .failureUrl("/signin")
                    .defaultSuccessUrl("/", true)
                    .permitAll())
            .logout((logout) -> logout
                    .logoutSuccessUrl("/")
                    .permitAll())
            // .csrf(csrf -> csrf.disable())
            .rememberMe(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
    http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/"));
    return http.build();
}
    }
    
