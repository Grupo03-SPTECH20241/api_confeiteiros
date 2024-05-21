package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cobertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCobertura;
    private String nome;

    @OneToMany(mappedBy = "cobertura")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "cobertura")
    private List<Personalizacao> personalizacoes;
}
