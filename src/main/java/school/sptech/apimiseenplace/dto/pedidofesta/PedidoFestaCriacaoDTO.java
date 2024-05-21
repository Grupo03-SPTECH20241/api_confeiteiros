package school.sptech.apimiseenplace.dto.pedidofesta;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PedidoFestaCriacaoDTO {
    @Size(min = 3, max = 100)
    private String cliente;
    @NotNull
    @FutureOrPresent
    private LocalDate dataEntrega;
    @NotNull
    @Size(min = 8)
    private String cep;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
