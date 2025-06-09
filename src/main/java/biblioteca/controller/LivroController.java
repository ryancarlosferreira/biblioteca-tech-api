package biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.entity.Livro;

@RestController
public class LivroController {
	@Autowired
	private biblioteca.repository.LivroRepository LivroRepository;

	@PostMapping("/livro")
	public String saveLivro(@RequestBody Livro Livro) {
		LivroRepository.save(Livro);
		return "Livro adicionado com sucesso";
	}
	
}