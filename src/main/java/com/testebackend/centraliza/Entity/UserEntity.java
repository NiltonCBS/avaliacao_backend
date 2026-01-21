package com.testebackend.centraliza.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//Para o Lombok ignorar todos os campos da classe, ele só irá analisar no campo onde tem o @EqualsAndHashCode.Include
//Estudo durante o desenvolvimento da API
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_user")
    private Long id;

    @Column(name = "name_user", nullable = false)
    private String nameUser;

    @ManyToMany(mappedBy = "participantsConversation")
    //Faz com que ninguém consiga acessar a lista de fora e, por exemplo, limpar ela
    //ninguém consegue dar um comando userEntity.conversations(novalista)
    @Setter(AccessLevel.NONE)
    //Set faz com que nenhum dado se repita, por exemplo, não tem porque o usuário entrar duas vezes na mesma conversa.
    //HashSet cria a lista para não começar como nula e dar algum erro.
    private Set<ConversationEntity> conversations = new HashSet<>();
}
