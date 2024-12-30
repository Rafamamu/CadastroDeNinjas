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
    public MissoesDTO criarMissoes(MissoesDTO missaoDTO) {
       MissoesModel novaMissao = missoesMapper.map(missaoDTO);
       novaMissao = missoesRepository.save(novaMissao);
       return missoesMapper.map(novaMissao);
   }

   //Listar Missoes por Id
    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    // Atualizar missoes
    public MissoesDTO atualizarMissoes(Long id, MissoesDTO missoesDTO) {
       Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);

       if (missaoExistente.isPresent()) {
           MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
           missaoAtualizada.setId(id);
           MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
           return missoesMapper.map(missaoSalva);
       }
       return  null;

    }



}
