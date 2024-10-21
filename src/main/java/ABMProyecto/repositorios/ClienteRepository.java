package ABMProyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import ABMProyecto.domain.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente,Long> {
}
