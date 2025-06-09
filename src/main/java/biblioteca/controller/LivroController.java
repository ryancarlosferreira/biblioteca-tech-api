package biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import biblioteca.entity.Livro;

@RestController
public class LivroController {
	@Autowired
	private biblioteca.repository.LivroRepository LivroRepository;

	//Cria Novos Livros
	@PostMapping("/livro")
	public String saveLivro(@RequestBody Livro Livro) {
		LivroRepository.save(Livro);
		return "Livro adicionado com sucesso";
	}
	
	//Lista todos os Livros
	@GetMapping
    public List<Livro> listarLivros() {
        return LivroRepository.findAll();
    }
	
	//Realiza buscas por ID
	@GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Long id) {
        Optional<Livro> livro = LivroRepository.findById(id);
        return livro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
	
	//Atualiza um livro existente
	@PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro novoLivro) {
        return LivroRepository.findById(id)
                .map(livro -> {
                    livro.setAutor(novoLivro.getAutor());
                    livro.setDescricao(novoLivro.getDescricao());
                    livro.setReserva(novoLivro.getReserva());
                    return ResponseEntity.ok(LivroRepository.save(livro));
                })
                .orElse(ResponseEntity.notFound().build());
    }
	
	//Deleta um livro existente
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        if (LivroRepository.existsById(id)) {
            LivroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
	
}