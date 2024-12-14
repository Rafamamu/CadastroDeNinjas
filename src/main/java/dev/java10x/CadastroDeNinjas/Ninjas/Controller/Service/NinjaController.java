package dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa é a minha primeira mensagem nessa rota!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Mostrar Ninja";
    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas() {
        return "Mostrar Ninja";
    }

    // Mostrar Ninja por ID (READ)
    @GetMapping("/todosId")
    public String mostrarTodosOsNinjasPorId() {
        return "Mostrar Ninja por Id";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar")
    public String atualizarNinja() {
        return "Ninja atualizado";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar")
    public String deletarNinja() {
        return "Ninja deletado";
    }
}
