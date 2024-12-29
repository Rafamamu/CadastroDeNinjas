package dev.java10x.CadastroDeNinjas.Missoes;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {


    public MissoesService missoesService;
    public MissoesMapper missoesMapper;

    public MissoesController(MissoesService missoesService, MissoesMapper misssoesMapper) {
        this.missoesService = missoesService;
        this.missoesMapper = missoesMapper;
    }



    // GET: Mandar uma requisição para mostrar para o usuário
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes();

    }

    //POST: Mandar uma requisição para criar as missões
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoes) {
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Nova Missão criada com sucesso: "+novaMissao.getNome()+" do (ID): "+ missoes.getId());

    }

    //PUT: Mandar uma requisição para alterar nossas missões
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id,
                                           @RequestBody MissoesDTO missaoDTO) {

        MissoesDTO missao = missoesService.atualizarMissoes(id, missaoDTO);

        if (missao != null) {
            return  ResponseEntity.ok(missao);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A Missão com (ID): " + id + " , não foi encontrado! ");
        }

    }

    // DELETE: Manda uma requisição para deletar missões
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missao Deletada com sucesso";
    }


}
