package dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String adicionarNinjaForm(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/adicionar")
    public String adicionarNinja(@ModelAttribute NinjaDTO ninja) {
        ninjaService.criarNinja(ninja);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String alterarNinjaForm(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "adicionarNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }

    @PostMapping("/alterar")
    public String alterarNinja(@ModelAttribute NinjaDTO ninja) {
        ninjaService.criarNinja(ninja);
        return "redirect:/ninjas/ui/listar";
    }
}
