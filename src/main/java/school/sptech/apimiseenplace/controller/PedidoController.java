package school.sptech.apimiseenplace.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.apimiseenplace.dto.pedido.PedidoCriacaoDTO;
import school.sptech.apimiseenplace.dto.pedido.PedidoListagemDTO;
import school.sptech.apimiseenplace.dto.pedido.PedidoMapper;
import school.sptech.apimiseenplace.dto.produto.ProdutoMapper;
import school.sptech.apimiseenplace.entity.Pedido;
import school.sptech.apimiseenplace.exception.BadRequestException;
import school.sptech.apimiseenplace.exception.NoContentException;
import school.sptech.apimiseenplace.repository.PedidoRepository;
import school.sptech.apimiseenplace.repository.ProdutoRepository;
import school.sptech.apimiseenplace.service.PedidoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolve o pedido, a lista de produtos e o produto cadastrado no momento"),
            @ApiResponse(responseCode = "400", description = "Pedido cadastrado está vazio", content = @Content),
    })
    @PostMapping("/cadastrar-novo-pedido")
    public ResponseEntity<PedidoListagemDTO> cadastrarNovoPedido(
            @RequestBody PedidoCriacaoDTO pedidoCriacaoDTO
    ){
        Pedido pedidoSalvo = service.cadastrarPedido(pedidoCriacaoDTO);
        return ResponseEntity.status(200).body(PedidoMapper.toDto(pedidoSalvo));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso!"),
            @ApiResponse(responseCode = "204", description = "Não há pedidos cadastrados", content = @Content),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<PedidoListagemDTO>> listarTodosOsPedidos(){
        List<Pedido> pedidos = service.listarPedidos();
        if (pedidos.isEmpty()) throw new NoContentException("Pedidos");
        return ResponseEntity.ok(PedidoMapper.toDto(pedidos));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido listado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pedido com o id indicado está vazio", content = @Content),
    })
    @GetMapping("/listar-por-id/{id}")
    public ResponseEntity<PedidoListagemDTO> buscarPedidoPorId(
            @PathVariable int id
    ){
        Pedido pedidoEncontrado = service.encontrarPorId(id);
        if (pedidoEncontrado == null) throw new BadRequestException("Pedido");

        return ResponseEntity.ok(PedidoMapper.toDto(pedidoEncontrado));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pedido com o id indicado está vazio", content = @Content),
    })
    @DeleteMapping("/deletar-por-id/{id}")
    public ResponseEntity<PedidoListagemDTO> deletarPedidoPorId(
            @PathVariable int id
    ){
        service.deletarPedidoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o indice do nome do cliente!"),
    })
    @GetMapping("/pesquisa-binaria-cliente")
    public ResponseEntity<Pedido> iniciarPesquisaBinariaCliente(
            @RequestParam String nomeCliente
    ) {
        Pedido[] pedidos = service.getVectorPedido();
        Pedido[] pedidosOrganizados = this.quicksortPedidosCliente(pedidos, 0, pedidos.length);
        return this.pesquisaBinariaCliente(pedidosOrganizados, nomeCliente);
    }

    public Pedido[] quicksortPedidosCliente(
            Pedido[] v,
            int indInicio,
            int indFim
    ) {
        int i, j;
        String pivo;

        i = indInicio;
        j = indFim - 1;
        pivo = v[(indInicio + indFim) / 2].getCliente();

        while (i <= j) {
            while (i < indFim && v[i].getCliente().compareTo(pivo) < 0) {
                i = i + 1;
            }
            while (j > indInicio && v[j].getCliente().compareTo(pivo) > 0) {
                j = j - 1;
            }
            if (i <= j) {
                Pedido temp = v[i];
                v[i] = v[j];
                v[j] = temp;
                i = i + 1;
                j = j - 1;
            }
        }

        if (indInicio < j + 1) {
            quicksortPedidosCliente(v, indInicio, j + 1);
        }
        if (i < indFim) {
            quicksortPedidosCliente(v, i, indFim);
        }

        return v;
    }

    public ResponseEntity<Pedido> pesquisaBinariaCliente(Pedido[] v, String nomeCliente) {
        int indInicio = 0;
        int indFim = v.length;
        while (indInicio <= indFim) {
            int indMeio = (indInicio + indFim) / 2;
            if (nomeCliente.equals(v[indMeio].getCliente())) {
                return ResponseEntity.ok(v[indMeio]);
            } else if (nomeCliente.compareTo(v[indMeio].getCliente()) > 0) {
                indInicio = indMeio + 1;
            } else {
                indFim = indMeio - 1;
            }
        }

        return ResponseEntity.noContent().build();
    }


}
