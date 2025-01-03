package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO) {

        MissoesModel missoesModel = new MissoesModel();

        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNome(missoesDTO.getNome());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel) {

        MissoesDTO missoesDTO = new MissoesDTO();

        missoesDTO.setId(missoesDTO.getId());
        missoesDTO.setNome(missoesDTO.getNome());
        missoesDTO.setDificuldade(missoesDTO.getDificuldade());

        return missoesDTO;
    }


}
