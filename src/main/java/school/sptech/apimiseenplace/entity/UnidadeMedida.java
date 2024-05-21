package school.sptech.apimiseenplace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.color.ProfileDataException;
import java.util.List;

@Entity
@Getter
@Setter
public class UnidadeMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnidadeMedida;
    private String unidadeMedida;

    @OneToMany(mappedBy = "unidadeMedida")
    private List<Produto> produtos;
}
