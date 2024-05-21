package school.sptech.apimiseenplace.dto.pedidofesta;

import school.sptech.apimiseenplace.entity.CEP;
import school.sptech.apimiseenplace.entity.Produto;

import java.time.LocalDate;
import java.util.List;

public class PedidoFestaListagemDTO {
    private int id;
    private String cliente;
    private List<Produto> produtos;
    private LocalDate dataEntrega;
    private double valorTotal;
    private CEP cep;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public double getValorTotal() {
        double valorTotal = 0.0;
        for (Produto produto : this.produtos){
            valorTotal += produto.getPreco();
        }
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }
}
