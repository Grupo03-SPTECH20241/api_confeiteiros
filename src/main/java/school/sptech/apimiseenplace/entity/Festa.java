package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Festa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFesta;


    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Endereco endereco;
}
