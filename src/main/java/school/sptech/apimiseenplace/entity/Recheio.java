package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Recheio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecheio;
    private String nome;
    private Double preco;

    @OneToMany(mappedBy = "recheio")
    private List<Produto> produtos;
    @OneToMany(mappedBy = "recheio")
    private List<Personalizacao> personalizacao;

}
