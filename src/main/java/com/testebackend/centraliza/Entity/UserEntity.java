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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)//Aprendizado durante o desenvolvimento
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include //Aprendizado durante o desenvolvimento
    @Column(name = "id_user")
    private Long id;

    @Column(name = "name_user")
    private String nameUser;

    @ManyToMany(mappedBy = "participantsConversation")
    @Setter(AccessLevel.NONE)
    private Set<ConversationEntity> conversations = new HashSet<>();
}
