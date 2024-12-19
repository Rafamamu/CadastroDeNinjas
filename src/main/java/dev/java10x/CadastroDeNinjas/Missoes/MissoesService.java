package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service.NinjaService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Listar todas as Missoes
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    // Criar Missoes
    public MissoesModel criarMissoes(MissoesModel missao) {
        return missoesRepository.save(missao);

    }
    //Listar Missoes por Id
    public MissoesModel listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.orElse(null);

    }
    // Atualizar missoes
    public MissoesModel atualizarMissoes(Long id, MissoesModel missaoAtualizada) {
        if (missoesRepository.existsById(id)) {
            missaoAtualizada.setId(id);
            return missoesRepository.save(missaoAtualizada);
        }
        return null;
    }



}
