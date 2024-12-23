package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service.NinjaModel;
import dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service.NinjaService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

   public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
       this.missoesRepository = missoesRepository;
       this.missoesMapper = missoesMapper;

   }

    // Listar todas as Missoes
    public List<MissoesDTO> listarMissoes() {
       List<MissoesModel> missoes = missoesRepository.findAll();
       return missoes.stream()
               .map(missoesMapper::map)
               .collect(Collectors.toList());
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
