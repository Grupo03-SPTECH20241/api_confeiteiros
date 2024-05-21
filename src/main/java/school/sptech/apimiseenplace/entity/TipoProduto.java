package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoProduto;
    private String tipo;

    @OneToMany(mappedBy = "tipoProduto")
    private List<Produto> produtos;
}
