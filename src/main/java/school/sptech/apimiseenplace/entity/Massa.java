package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FetchProfile;

import java.util.List;

@Entity
@Getter
@Setter
public class Massa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMassa;
    private String nome;

    @OneToMany(mappedBy = "massa")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "massa")
    private List<Personalizacao> personalizacoes;



}
