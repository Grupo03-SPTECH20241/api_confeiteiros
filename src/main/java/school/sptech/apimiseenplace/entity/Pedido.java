package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    private LocalDate dtPedido;
    private Double vlPedido;
    private char status;
    private Double valorSinal;

    @ManyToOne
    private FormaEntrega formaEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> produtoPedidos;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<Festa> festas;

    @ManyToOne
    private FormaPagamento formaPagamento;
}
