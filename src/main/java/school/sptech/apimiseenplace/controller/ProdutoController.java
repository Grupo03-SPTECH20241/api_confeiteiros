package school.sptech.apimiseenplace.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apimiseenplace.dto.produto.ProdutoCriacaoDTO;
import school.sptech.apimiseenplace.dto.produto.ProdutoListagemDTO;
import school.sptech.apimiseenplace.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolve o produto cadastrado no momento juntamente ao seu respectivo pedido"),
            @ApiResponse(responseCode = "400", description = "Pedido cadastrado está vazio", content = @Content),
    })
    @PostMapping
    public ResponseEntity<ProdutoListagemDTO> cadastrarProduto(
            @RequestBody @Valid ProdutoCriacaoDTO produtoCriacaoDTO
    ){
        return ResponseEntity.created(null).body(service.cadastrarProduto(produtoCriacaoDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso!"),
            @ApiResponse(responseCode = "204", description = "Não há pedidos cadastrados", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<ProdutoListagemDTO>> listarProdutos(){
        List<ProdutoListagemDTO> produtos = service.listarProdutos();
        if(produtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    @DeleteMapping("deletar-produto-por-id/{idProduto}")
    public ResponseEntity<Void> deletarProdutoPorId(
            @PathVariable int idProduto
    ){
       service.deletarProdutoPorId(idProduto);
       return ResponseEntity.noContent().build();
    }
}
