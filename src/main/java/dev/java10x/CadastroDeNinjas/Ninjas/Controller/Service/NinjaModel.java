package dev.java10x.CadastroDeNinjas.Ninjas.Controller.Service;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


// Entity transforma uma classe em uma entidade  do BD
@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "idade")
    private int idade;

    @Column(name= "rank")
    private String rank;

    //@ManyToOne  um ninja só tem uma missão
    @ManyToOne
    @JoinColumn(name = "missoes_id") // foreing key ou chave estrangeira.
    private MissoesModel missoes;


}

