package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class FormaEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormaEntrega;
    private String formaEntrega;

    @OneToMany(mappedBy = "formaEntrega")
    private List<Pedido> pedidos;

    public FormaEntrega() {
    }

    public FormaEntrega(Integer idFormaEntrega, String formaEntrega, List<Pedido> pedidos) {
        this.idFormaEntrega = idFormaEntrega;
        this.formaEntrega = formaEntrega;
        this.pedidos = pedidos;
    }
}
