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

import biblioteca.entity.Bibliotecario;
import biblioteca.entity.Reserva;
import biblioteca.entity.Usuario;
import biblioteca.repository.BibliotecarioRepository;
import biblioteca.repository.UsuarioRepository;


public class ReservaController {
	@Autowired
    private biblioteca.repository.ReservaRepository reservaRepository;
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;

    @PostMapping("/reserva")
    public Reserva criarReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }
    
 // Listar todas as reservas
    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
    
 // Buscas por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReserva(@PathVariable Long id) {
        return reservaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
 // Atualiza dados existentes
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva novaReserva) {
        Optional<Usuario> usuario = usuarioRepository.findById(novaReserva.getUsuario().getId());
        Optional<Bibliotecario> bibliotecario = bibliotecarioRepository.findById(novaReserva.getBibliotecarioReserva().getId());

        if (usuario.isEmpty() || bibliotecario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return reservaRepository.findById(id).map(reserva -> {
            reserva.setData(novaReserva.getData());
            reserva.setUsuario(usuario.get());
            reserva.setBibliotecarioReserva(bibliotecario.get());
            return ResponseEntity.ok(reservaRepository.save(reserva));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    
 // Deleta reservas existentes
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
