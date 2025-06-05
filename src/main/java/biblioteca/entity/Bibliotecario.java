package biblioteca.entity;

import java.util.HashSet; 
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "bibliotecario")
public class Bibliotecario { // DICA: USA A BIBLIOTECA "LOMBOK", PESQUISA SOBRE...)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "bibliotecarioReserva")
    private Set<Reserva> reservasBibliotecario = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Reserva> getReservasBibliotecario() {
        return reservasBibliotecario;
    }

    public void setReservasBibliotecario(Set<Reserva> reservasBibliotecario) {
        this.reservasBibliotecario = reservasBibliotecario;
    }
}
