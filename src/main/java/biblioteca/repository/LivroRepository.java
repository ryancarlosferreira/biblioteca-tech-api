package biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
