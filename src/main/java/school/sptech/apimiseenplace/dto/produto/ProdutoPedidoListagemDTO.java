package school.sptech.apimiseenplace.dto.produto;

import java.time.LocalDate;

public class ProdutoPedidoListagemDTO {
    private Integer id;
    private String cliente;
    private LocalDate dataEntrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
