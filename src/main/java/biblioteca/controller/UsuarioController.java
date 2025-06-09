package biblioteca.controller;

import java.util.Optional; 

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

import biblioteca.entity.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private biblioteca.repository.UsuarioRepository UsuarioRepository;
 // Cria um novo usuario
	@PostMapping("/usuario")
	public String saveUsuario(@RequestBody Usuario Usuario) {
		UsuarioRepository.save(Usuario);
		return "Usuário criado com sucesso";
	}
	
 // 	
	@GetMapping("/usuario")
	public ResponseEntity<String> login(
	        @RequestParam String usuario,
	        @RequestParam String senha) {

	    Optional<Usuario> usuarioEncontrado = UsuarioRepository.findByUsuarioAndSenha(usuario, senha);

	    if (usuarioEncontrado.isPresent()) {
	        return ResponseEntity.ok("Login válido");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
	    }
	}
	
 // Atualiza usuario existente
	@PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
        return UsuarioRepository.findById(id).map(usuario -> {
            usuario.setUsuario(novoUsuario.getUsuario());
            usuario.setSenha(novoUsuario.getSenha());
            return ResponseEntity.ok(UsuarioRepository.save(usuario));
        } 
        		).orElse(ResponseEntity.notFound().build());
    }
	
	 // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (UsuarioRepository.existsById(id)) {
            UsuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}