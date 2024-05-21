package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProdutoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutoPedido;
    private int qtProduto;
    private String observacoes;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Personalizacao personalizacao;

    @ManyToOne
    private Pedido pedido;
}
