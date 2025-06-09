package biblioteca.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.entity.Bibliotecario;

public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Long> {
	
}