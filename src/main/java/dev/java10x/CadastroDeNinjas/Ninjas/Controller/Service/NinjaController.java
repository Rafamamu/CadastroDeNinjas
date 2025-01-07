package dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota manda uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = " Rota para Criar um novo ninja na tabela Cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Novo ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar o ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + "(ID): " + novoNinja.getId());
    }


    // Mostrar todos os Ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = " Rota Lista todos os ninjas sa tabela cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ninjas);
    }

    // Mostrar Ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com (ID): " + id + ", não existe nos nossos registros. ");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera Ninja por id", description = "Rota para alterar ninja por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi possivel alterar o ninja")
    })
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuário manda id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário Manda o corpo atualizado como caminho da requisição")
            @RequestBody NinjaDTO ninjaAtualizado) {
        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com (ID): " + id + ", não foi encontrado!");
        }
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {

        NinjaDTO ninjaExistente = ninjaService.listarNinjasPorId(id);

        if (ninjaExistente != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com Id: " + id + " deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com ID: " + id + " não foi encontrado!");
        }
    }
}
