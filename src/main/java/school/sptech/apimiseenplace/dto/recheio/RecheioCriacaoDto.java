package school.sptech.apimiseenplace.dto.recheio;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RecheioCriacaoDto {

    @NotBlank
    @Size(min=11, max = 45)
    private String nome;
    @Positive
    @DecimalMin("0.0")
    @DecimalMax("9999.99")
    private Double preco;
}
