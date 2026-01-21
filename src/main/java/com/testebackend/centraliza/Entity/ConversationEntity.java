package com.testebackend.centraliza.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="conversation")
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_conversation")
    private Long id;
    
    private Instant createdAt = Instant.now();

    @ManyToMany
    @Setter(AccessLevel.NONE)
    @JoinTable(
            name = "conversation_participants",
            joinColumns = @JoinColumn(name = "id_conversation"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
    private Set<UserEntity> participantsConversation = new HashSet<>();

    //Crie um método auxiliar só para verificar o número de participantes na conversa
    //Aprendi no desenvolvimento a importância do método auxiliar, para manter a integridade e segurança
    //Na faculdade esse tipo de método não é muito visto, a gente vê mais tratamento de erro e segurança na service
    public void addParticipant(UserEntity user){
        if (this.participantsConversation.size() >= 2){
            throw new IllegalStateException("Uma conversa pode ter no máximo 2 participantes");
        }
        this.participantsConversation.add(user);

        user.getConversations().add(this);
    }
}
