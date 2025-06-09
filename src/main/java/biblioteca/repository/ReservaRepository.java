package biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
