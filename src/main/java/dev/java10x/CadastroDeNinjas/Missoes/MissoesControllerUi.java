package dev.java10x.CadastroDeNinjas.Missoes;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }


    @GetMapping("/listar")
    public String  listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes",missoes);
        return "listarMissoes";
    }

    @GetMapping("/adicionar")
    public String adicionarNinjaForm(Model model) {
        model.addAttribute("missoes",new MissoesDTO());

        return "adicionarMissao";
    }


    @PostMapping("/adicionar")
    public String criarMissao(@ModelAttribute MissoesDTO missoesDTO) {
        missoesService.criarMissoes(missoesDTO);

        return "redirect:/missoes/ui/listar";
    }
}
