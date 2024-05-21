package school.sptech.apimiseenplace.dto.produto;

public class ProdutoListagemDTO {
    private Integer id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private ProdutoPedidoListagemDTO pedido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public ProdutoPedidoListagemDTO getPedido() {
        return pedido;
    }

    public void setPedido(ProdutoPedidoListagemDTO pedido) {
        this.pedido = pedido;
    }
}
